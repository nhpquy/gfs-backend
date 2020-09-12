package com.gfs.services.impl;

import com.beowulfchain.beowulfj.configuration.BeowulfJConfig;
import com.beowulfchain.beowulfj.enums.PrivateKeyType;
import com.beowulfchain.beowulfj.exceptions.BeowulfCommunicationException;
import com.beowulfchain.beowulfj.exceptions.BeowulfResponseException;
import com.beowulfchain.beowulfj.plugins.apis.condenser.models.ExtendedAccount;
import com.beowulfchain.beowulfj.plugins.apis.network.broadcast.models.BroadcastTransactionSynchronousReturn;
import com.beowulfchain.beowulfj.protocol.*;
import com.beowulfchain.beowulfj.protocol.enums.AssetSymbolType;
import com.beowulfchain.beowulfj.protocol.extensions.JsonExtension;
import com.beowulfchain.beowulfj.protocol.operations.AccountCreateOperation;
import com.beowulfchain.beowulfj.protocol.operations.Operation;
import com.beowulfchain.beowulfj.protocol.operations.TransferOperation;
import com.beowulfchain.beowulfj.util.BeowulfJUtils;
import com.gfs.domain.component.BWFClient;
import com.gfs.domain.component.RedisKeyExpireHandler;
import com.gfs.domain.constant.BeowulfConstant;
import com.gfs.domain.constant.RedisConstant;
import com.gfs.domain.document.BeowulfWallet;
import com.gfs.domain.document.Organization;
import com.gfs.domain.handler.BeowulfWalletKeyExpiredHandlerCallback;
import com.gfs.domain.model.CurrentTutorLogin;
import com.gfs.domain.model.bwfdata.AuthorityData;
import com.gfs.domain.repository.inf.BeowulfWalletRepository;
import com.gfs.domain.repository.inf.OrganizationRepository;
import com.gfs.domain.request.CreateBeowulfWalletRequest;
import com.gfs.domain.response.BeowulfWalletResponse;
import com.gfs.domain.utils.AESEncryptor;
import com.gfs.domain.utils.GsonSingleton;
import com.gfs.domain.utils.RedisUtils;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.services.inf.BeowulfWalletService;
import eu.bittrade.crypto.core.ECKey;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BeowulfWalletServiceImpl implements BeowulfWalletService {

    private final Logger logger = LoggerFactory.getLogger(BeowulfWalletService.class);

    private static final String TAG_BUILDER = BeowulfWalletService.class.getSimpleName();

    @Autowired
    OrganizationRepository organizationRepository;

    @Autowired
    BeowulfWalletRepository beowulfWalletRepository;

    @Autowired
    RedisKeyExpireHandler redisKeyExpireHandler;

    @Autowired
    BWFClient bwfClient;

    private static final BeowulfWalletKeyExpiredHandlerCallback callback = new BeowulfWalletKeyExpiredHandlerCallback();

    @Override
    public BeowulfWalletResponse createBeowulfWallet(CreateBeowulfWalletRequest request, CurrentTutorLogin currentTutorLogin) {

        String orgId = currentTutorLogin.getJoinedOrg().getOrg_id();
        Organization currentOrg = organizationRepository.findByOrg_id(orgId);

        if (currentOrg.getBwf_wallet_count() >= BeowulfConstant.MAXIMUM_WALLETS ||
                beowulfWalletRepository.findByOrg_id(orgId).size() >= BeowulfConstant.MAXIMUM_WALLETS) {
            throw ServiceExceptionUtils.maximumBwfAccountCreated();
        }

        checkAccountName(request.getName());

        // Increase number of bwf accounts
        organizationRepository.increaseWalletBwfNumber(orgId);

        // Create new bwf account
        BeowulfWallet newWallet = createAccount(orgId, request);
        newWallet = beowulfWalletRepository.save(newWallet);

        return new BeowulfWalletResponse(newWallet);
    }

    @Override
    public boolean checkAccountName(String accountName) {
        // step 1: check syntax account_name
        AccountName accountNameNew = new AccountName(accountName);
        // step 2: Check account_name in pending account
        if (beowulfWalletRepository.findByName(accountName) != null) {
            throw ServiceExceptionUtils.bwfWalletExisted();
        }
        // step 3: check account_name available
        List<ExtendedAccount> accountDetail = new ArrayList<>();
        try {
            accountDetail = bwfClient.getClient().getAccounts(Collections.singletonList(accountNameNew));
        } catch (NullPointerException ignored) {

        } catch (BeowulfCommunicationException | BeowulfResponseException e) {
            String msgErrorProcess = String.format("Can not connect to BWF endpoint with Exception: %s", e.getMessage());
            logger.error(msgErrorProcess);
            e.printStackTrace();
            throw ServiceExceptionUtils.internalServerError();
        }

        if (!accountDetail.isEmpty()) {
            throw ServiceExceptionUtils.bwfWalletExisted();
        }
        return true;
    }

    @Override
    public List<BeowulfWalletResponse> getListBeowulfWallets(CurrentTutorLogin currentTutorLogin) {
        List<BeowulfWallet> wallets = beowulfWalletRepository.findByOrg_id(currentTutorLogin.getJoinedOrg().getOrg_id());
        return wallets.stream().map(BeowulfWalletResponse::new).collect(Collectors.toList());
    }

    @Override
    public String pushTransaction(String from, String to, Object extendData) {
        BeowulfWallet fromWallet = beowulfWalletRepository.findByName(from);
        if (fromWallet == null) {
            throw ServiceExceptionUtils.walletNotFound();
        }

        try {
            AccountName fromAccount = new AccountName(from);
            AccountName toAccount = new AccountName(to);
            Asset transferAmount = new Asset(1L, AssetSymbolType.BWF);
            TransferOperation transferOperation = bwfClient.getClient().transfer(fromAccount, toAccount, transferAmount, bwfClient.getNetwork().getTransactionFee(), "");

            ExtensionValue value = new ExtensionValue(GsonSingleton.getInstance().toJson(extendData));
            JsonExtension extension = new JsonExtension(value);

            registerBeowulfWalletStorage(fromWallet);

            TransactionId transactionId = bwfClient.getClient().signAndBroadcastWithExtension(Collections.singletonList(transferOperation), Collections.singletonList(extension));

            return transactionId.toString();
        } catch (BeowulfResponseException e) {
            String msgErrorProcess = String.format("Can not push transaction for account: %s because of Exception: %s", from, e.getMessage());
            logger.error(msgErrorProcess);

            if (e.getData().get("code").asInt() == 10) {
                throw ServiceExceptionUtils.notSufficientFund();
            }
            throw ServiceExceptionUtils.internalServerError();
        } catch (Exception e) {
            String msgErrorProcess = String.format("Can not push transaction for account: %s because of Exception: %s", from, e.getMessage());
            logger.error(msgErrorProcess);
            e.printStackTrace();
            throw ServiceExceptionUtils.internalServerError();
        }
    }

    private BeowulfWallet createAccount(String orgId, CreateBeowulfWalletRequest request) {
        boolean isSuccess = false;
        try {
            AccountName accountName = new AccountName(request.getName());
            // create new key-pair
            ECKey ecKey = new ECKey().decompress();
            // get public key
            PublicKey publicKey = new PublicKey(ecKey);

            // Create permissions property
            Authority owner = new Authority();
            Map<PublicKey, Integer> keyAuths = new HashMap<>();
            keyAuths.put(publicKey, 1);
            owner.setKeyAuths(keyAuths);
            owner.setWeightThreshold(1);
            String jsonMetadataTemplate = "{}";

            AccountCreateOperation accountOperation = bwfClient.getClient().createAccount(
                    bwfClient.getCredential_account_beowulf_default(),
                    bwfClient.getNetwork().getAccountCreationFee(),
                    accountName,
                    owner,
                    jsonMetadataTemplate
            );

            Asset transferBWF = new Asset(10000L, AssetSymbolType.BWF);
            Asset transferW = new Asset(10000L, AssetSymbolType.W);

            TransferOperation transferBWFOperation = bwfClient.getClient().transfer(bwfClient.getCredential_account_beowulf_default(), accountName, transferBWF, bwfClient.getNetwork().getTransactionFee(), "");
            TransferOperation transferWOperation = bwfClient.getClient().transfer(bwfClient.getCredential_account_beowulf_default(), accountName, transferW, bwfClient.getNetwork().getTransactionFee(), "");

            List<Operation> operations = new ArrayList<>();
            operations.add(accountOperation);
            operations.add(transferBWFOperation);
            operations.add(transferWOperation);

            BroadcastTransactionSynchronousReturn response = bwfClient.getClient().signAndBroadcastSynchronous(operations);

            // get encrypted private key
            String encryptedPrivateKey = AESEncryptor.encrypt(
                    bwfClient.getCrypto_aes_secretkey(),
                    bwfClient.getCrypto_aes_initvector(),
                    BeowulfJUtils.fromEckeyToWif(ecKey));

            BeowulfWallet newAccount = BeowulfWallet.builder()
                    .name(request.getName())
                    .permissions(new AuthorityData(owner))
                    .multisig(false)
                    .creator(bwfClient.getCredential_account_beowulf_default().getName())
                    .created_txn_id(response.getId().toString())
                    .created_time(response.getCreatedTime())
                    .org_id(orgId)
                    .description(request.getDescription())
                    .encrypted_key(encryptedPrivateKey)
                    .build();

            String msgSuccessProcess = String.format("Create account: %s for organization: %s in transaction id: %s", newAccount.getName(), newAccount.getOrg_id(), newAccount.getCreated_txn_id());
            logger.info(msgSuccessProcess);

            isSuccess = true;
            return newAccount;
        } catch (BeowulfResponseException e) {
            String msgErrorProcess = String.format("Can not create account: %s for organization: %s because of Exception: %s", request.getName(), orgId, e.getMessage());
            logger.error(msgErrorProcess);

            if (e.getData().get("code").asInt() == 13) {
                throw ServiceExceptionUtils.bwfWalletExisted();
            }
            throw ServiceExceptionUtils.internalServerError();
        } catch (Exception e) {
            String msgErrorProcess = String.format("Can not create account: %s for owner: %s because of Exception: %s", request.getName(), orgId, e.getMessage());
            logger.error(msgErrorProcess);
            e.printStackTrace();
            throw ServiceExceptionUtils.internalServerError();
        } finally {
            if (!isSuccess)
                organizationRepository.decreaseWalletBwfNumber(orgId);
        }
    }

    private void registerBeowulfWalletStorage(BeowulfWallet wallet) {
        AccountName walletName = new AccountName(wallet.getName());
        boolean isAvailable = isAvailableKey(walletName);
        try {
            // Check if private key is not in Beowulf config
            if (!isAvailable) {
                List<ImmutablePair<PrivateKeyType, String>> fromKey = new ArrayList<>();
                fromKey.add(new ImmutablePair<>(PrivateKeyType.OWNER,
                        AESEncryptor.decrypt(bwfClient.getCrypto_aes_secretkey(),
                                bwfClient.getCrypto_aes_initvector(),
                                wallet.getEncrypted_key())));
                BeowulfJConfig.getInstance().getPrivateKeyStorage().addAccount(walletName, fromKey);

                // Add new key for topic wallet_expire => wallet in Beowulf config will be remove if this key is expired (Time expire - 5 minutes)
                String expireWalletKey = String.format("%s:%s", RedisConstant.PREFIX_WALLET_EXPIRE, wallet.getName());
                RedisUtils.setWithExpire(expireWalletKey, RedisConstant.DEFAULT_VALUE_EXPIRED_TOPIC, 300);

                // Register callback remove private key in config when key is expired
                RedisKeyExpireHandler.registerCallback(expireWalletKey, callback);

                logger.info(String.format("SUCCESSFUL register key expired callback for wallet %s", wallet.getName()));
            }
        } catch (Exception e) {
            String msgErrorProcess = String.format("Can not register wallet storage for account: %s because of Exception: %s", wallet.getName(), e.getMessage());
            logger.error(msgErrorProcess);
            e.printStackTrace();
        }
    }

    private boolean isAvailableKey(AccountName walletName) {
        try {
            if (BeowulfJConfig.getInstance().getPrivateKeyStorage().getKeyForAccount(PrivateKeyType.OWNER, walletName) != null)
                return true;
        } catch (Exception ignored) {
        }
        return false;
    }
}
