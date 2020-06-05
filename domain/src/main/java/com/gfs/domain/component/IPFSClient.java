package com.gfs.domain.component;

import com.gfs.domain.config.model.IPFSServerConfig;
import com.gfs.domain.exception.ConnectionException;
import com.gfs.domain.handler.IPFSFetcher;
import com.gfs.domain.repository.inf.ConfigurationRepository;
import com.gfs.domain.utils.LoggerUtil;
import com.gfs.domain.utils.ServiceExceptionUtils;
import com.gfs.domain.utils.StringUtils;
import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Component
@EnableScheduling
public class IPFSClient {
    private static final String TAG = IPFSClient.class.getName();

    /**
     * IPFS Config
     */
    @Value("${ipfs.host}")
    private String host;
    @Value("${ipfs.port}")
    private Integer port;
    private IPFSServerConfig config;

    private IPFS ipfs;
    private ExecutorService pool;

    @Autowired
    private ConfigurationRepository configurationRepository;

    @PostConstruct
    public void init() {
        try {
            LoggerUtil.i(TAG, "Init IPFS Client");
            config = configurationRepository.findByKey(IPFSServerConfig.IPFS_CONFIG_KEY, IPFSServerConfig.class);
            if (config == null) {
                config = new IPFSServerConfig(host, port);
                configurationRepository.save(config);
            }
            ipfs = new IPFS(config.getHost(), config.getPort());
            LoggerUtil.i(TAG, String.format("Init IPFS Client successfully with [host: %s, port: %d]", ipfs.host, ipfs.port));

        } catch (Exception e) {
            String msg = String.format("Error while connecting to IPFS with [host: %s, port: %d]", config.getHost(), config.getPort());
            LoggerUtil.e(TAG, msg);
        }
    }

    @Scheduled(initialDelay = 60000, fixedDelay = 60000)
    public void refresh() {
        LoggerUtil.d(this, "Check IPFS health ...");
        boolean isRunning = true;
        try {
            ipfs.version();
            LoggerUtil.d(TAG, "IPFS is still running ...");
        } catch (Exception e) {
            isRunning = false;
        } finally {
            if (!isRunning)
                restart();
        }
    }

    public void restart() {
        LoggerUtil.i(TAG, "IPFS is restarting ...");
        init();
    }

    public IPFS getClient() {
        if (ipfs == null) {
            init();
        }
        return ipfs;
    }

    public MerkleNode createContent(byte[] content) {

        LoggerUtil.d(TAG, "Store file in IPFS ...");
        // Validation
        if (content == null) throw new IllegalArgumentException("content is null");

        try {
            NamedStreamable.ByteArrayWrapper requestFile = new NamedStreamable.ByteArrayWrapper(content);
            MerkleNode addResult = getClient().add(requestFile).get(0);

            LoggerUtil.d(TAG, String.format("File created in IPFS: hash=%s ", addResult.hash.toString()));

            return addResult;

        } catch (IOException ex) {
            LoggerUtil.e(TAG, "Exception while storing file in IPFS");
            throw ServiceExceptionUtils.internalServerError();
        } catch (Exception ex) {
            LoggerUtil.exception(TAG, ex, true);
            throw ServiceExceptionUtils.internalServerError();
        }
    }

    public byte[] getContent(String hash) {

        LoggerUtil.d(TAG, String.format("Get file in IPFS [hash: %s] ", hash));

        // Validation
        if (StringUtils.isEmpty(hash)) throw new IllegalArgumentException("hash is null");

        try {
            Multihash filePointer = Multihash.fromBase58(hash);

            Future<byte[]> ipfsFetcherResult = pool.submit(new IPFSFetcher(ipfs, filePointer));

            byte[] content = ipfsFetcherResult.get(config.getTime_out(), TimeUnit.MILLISECONDS);

            LoggerUtil.d(TAG, String.format("Get file in IPFS [hash: %s]", hash));

            return content;

        } catch (TimeoutException ex) {
            String msg = String.format("Timeout Exception while getting file in IPFS [hash: %s]", hash);
            LoggerUtil.e(TAG, msg);
            throw new ConnectionException(msg);

        } catch (InterruptedException ex) {
            String msg = String.format("Interrupted Exception while getting file in IPFS [hash: %s]", hash);
            LoggerUtil.e(TAG, msg);
            throw new ConnectionException(msg);

        } catch (Exception ex) {
            LoggerUtil.exception(TAG, ex, true);
            throw ServiceExceptionUtils.internalServerError();
        }
    }
}
