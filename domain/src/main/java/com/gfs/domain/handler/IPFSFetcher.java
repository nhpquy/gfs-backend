package com.gfs.domain.handler;

import com.gfs.domain.utils.ServiceExceptionUtils;
import io.ipfs.api.IPFS;
import io.ipfs.multihash.Multihash;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.Callable;

@Slf4j
public class IPFSFetcher implements Callable<byte[]> {

    private final IPFS ipfs;
    private final Multihash multihash;

    public IPFSFetcher(IPFS ipfs, Multihash multihash) {
        this.ipfs = ipfs;
        this.multihash = multihash;
    }

    @Override
    public byte[] call() {

        try {
            return this.ipfs.cat(multihash);

        } catch (IOException ex) {
            log.error("Exception while getting file in IPFS [hash: {}]", multihash, ex);
            throw ServiceExceptionUtils.internalServerError();
        }
    }
}


