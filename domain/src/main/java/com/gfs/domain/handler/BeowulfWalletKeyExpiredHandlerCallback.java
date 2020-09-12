package com.gfs.domain.handler;


import com.beowulfchain.beowulfj.configuration.BeowulfJConfig;
import com.beowulfchain.beowulfj.enums.PrivateKeyType;
import com.beowulfchain.beowulfj.protocol.AccountName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeowulfWalletKeyExpiredHandlerCallback implements RedisKeyExpiredHandlerCallback {

    private final Logger logger = LoggerFactory.getLogger(BeowulfWalletKeyExpiredHandlerCallback.class);

    @Override
    public void onKeyExpired(String wallet) {
        AccountName accountName = new AccountName(wallet);
        boolean isAvailable = isAvailableKey(accountName);
        try {
            if (isAvailable) {
                BeowulfJConfig.getInstance().getPrivateKeyStorage().removeAccount(accountName);
                logger.info(String.format("SUCCESSFUL handle key expired callback for wallet %s", wallet));
            }
        } catch (Exception e) {
            String msgErrorProcess = String.format("Can not handle process after BWF wallet key expired callback with Exception: %s", e.getMessage());
            logger.error(msgErrorProcess);
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
