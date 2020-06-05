package com.gfs.domain.component;

import com.beowulfchain.beowulfj.BeowulfJ;
import com.beowulfchain.beowulfj.chain.CompletedTransaction;
import com.beowulfchain.beowulfj.chain.NetworkProperties;
import com.beowulfchain.beowulfj.chain.network.Mainnet;
import com.beowulfchain.beowulfj.chain.network.Testnet;
import com.beowulfchain.beowulfj.configuration.BeowulfJConfig;
import com.beowulfchain.beowulfj.exceptions.BeowulfCommunicationException;
import com.beowulfchain.beowulfj.exceptions.BeowulfResponseException;
import com.beowulfchain.beowulfj.protocol.AccountName;
import com.gfs.domain.config.model.BWFServerConfig;
import com.gfs.domain.exception.ConnectionException;
import com.gfs.domain.repository.inf.ConfigurationRepository;
import com.gfs.domain.utils.LoggerUtil;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
    private BWFServerConfig config;

    private BeowulfJ beowulfJ;
    private NetworkProperties network;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @PostConstruct
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
            instanceConfig.setDefaultAccount(new AccountName("beowulf"));
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
}
