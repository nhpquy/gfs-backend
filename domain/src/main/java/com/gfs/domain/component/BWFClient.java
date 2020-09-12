package com.gfs.domain.component;

import com.beowulfchain.beowulfj.BeowulfJ;
import com.beowulfchain.beowulfj.chain.CompletedTransaction;
import com.beowulfchain.beowulfj.chain.NetworkProperties;
import com.beowulfchain.beowulfj.chain.network.Mainnet;
import com.beowulfchain.beowulfj.chain.network.Testnet;
import com.beowulfchain.beowulfj.configuration.BeowulfJConfig;
import com.beowulfchain.beowulfj.enums.PrivateKeyType;
import com.beowulfchain.beowulfj.exceptions.BeowulfCommunicationException;
import com.beowulfchain.beowulfj.exceptions.BeowulfResponseException;
import com.beowulfchain.beowulfj.protocol.AccountName;
import com.gfs.domain.config.model.BWFServerConfig;
import com.gfs.domain.exception.ConnectionException;
import com.gfs.domain.repository.inf.ConfigurationRepository;
import com.gfs.domain.utils.AESEncryptor;
import com.gfs.domain.utils.LoggerUtil;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@EnableScheduling
public class BWFClient {
    private static final String TAG = BWFClient.class.getName();

    /**
     * BWF Config
     */
    @Value("${bwf.endpoint}")
    private String endpoint;
    @Value("${bwf.testnet}")
    private boolean testnet;
    @Value("${crypto.aes.secretkey:1234567812345678}")
    private String crypto_aes_secretkey;
    @Value("${crypto.aes.initvector:1234567812345678}")
    private String crypto_aes_initvector;
    @Value("${credential.account.beowulf.default}")
    private String credential_account_beowulf_default;
    @Value("${credential.account.beowulf.cipher_privatekey}")
    private String credential_account_beowulf_cipher_privatekey;

    private BWFServerConfig config;

    private BeowulfJ beowulfJ;
    private NetworkProperties network;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        try {
            LoggerUtil.i(TAG, "Init BWF Client");
            config = configurationRepository.findByKey(BWFServerConfig.BWF_CONFIG_KEY, BWFServerConfig.class);
            if (config == null) {
                config = new BWFServerConfig(endpoint, testnet);
                configurationRepository.save(config);
            }
            if (config.isTestnet()) {
                network = new Testnet();
            } else {
                network = new Mainnet();
            }
            BeowulfJConfig instanceConfig = BeowulfJConfig.getNewInstance();
            instanceConfig.setResponseTimeout(config.getTimeout());
            instanceConfig.setDefaultBeowulfApiUri(config.getEndpoint());
            instanceConfig.setNetwork(network);
            List<ImmutablePair<PrivateKeyType, String>> privateKeys = new ArrayList<>();
            privateKeys.add(new ImmutablePair<>(PrivateKeyType.OWNER,
                    AESEncryptor.decrypt(getCrypto_aes_secretkey(),
                            getCrypto_aes_initvector(),
                            getCredential_account_beowulf_cipher_privatekey())));
            instanceConfig.setDefaultAccount(getCredential_account_beowulf_default());
            instanceConfig.getPrivateKeyStorage().addAccount(getCredential_account_beowulf_default(), privateKeys);
            beowulfJ = BeowulfJ.getNewInstance();
            LoggerUtil.i(TAG, String.format("Init BWF Client successfully with [endpoint: %s]", config.getEndpoint()));

        } catch (Exception e) {
            String msg = String.format("Error while connecting to BWF with [endpoint: %s]", config.getEndpoint());
            LoggerUtil.e(TAG, msg);
        }
    }

    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void refresh() {
        LoggerUtil.d(this, "Check BWF health ...");
        boolean isRunning = true;
        try {
            beowulfJ.getDynamicGlobalProperties();
            LoggerUtil.d(TAG, "BWF is still running ...");
        } catch (Exception e) {
            isRunning = false;
        } finally {
            if (!isRunning)
                restart();
        }
    }

    public void restart() {
        LoggerUtil.i(TAG, "BWF is restarting ...");
        init();
    }

    public BeowulfJ getClient() {
        if (beowulfJ == null) {
            init();
        }
        return beowulfJ;
    }

    public NetworkProperties getNetwork() {
        if (network == null) {
            init();
        }
        return network;
    }


    public CompletedTransaction getTransaction(String transactionId) {

        LoggerUtil.d(TAG, "Get transaction detail in BWF ...");
        // Validation
        if (!StringUtils.hasText(transactionId)) throw new IllegalArgumentException("Transaction id is null");

        try {
            return getClient().getTransactionDetail(transactionId);

        } catch (BeowulfResponseException e) {
            LoggerUtil.e(TAG, e.getMessage());
            throw new IllegalArgumentException(String.format("Transaction not found [tx_id: %s]", transactionId));

        } catch (BeowulfCommunicationException e) {
            String msg = String.format("Timeout Exception while getting transaction detail in BWF [tx_id: %s]", transactionId);
            LoggerUtil.e(TAG, msg);
            throw new ConnectionException(msg);

        } catch (Exception ex) {
            LoggerUtil.exception(TAG, ex, true);
            throw ServiceExceptionUtils.internalServerError();
        }
    }

    public String getCrypto_aes_secretkey() {
        return crypto_aes_secretkey;
    }

    public String getCrypto_aes_initvector() {
        return crypto_aes_initvector;
    }

    public AccountName getCredential_account_beowulf_default() {
        return new AccountName(credential_account_beowulf_default);
    }

    public String getCredential_account_beowulf_cipher_privatekey() {
        return credential_account_beowulf_cipher_privatekey;
    }
}
