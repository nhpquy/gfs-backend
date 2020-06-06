package com.gfs.domain.config.model;

import com.beowulfchain.beowulfj.chain.CompletedTransaction;
import com.gfs.domain.utils.GsonSingleton;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SharedFileMetaData {
    private String file_name;
    private String hash_code;
    private long size;
    private String content_type;
    private String password;
    private boolean encrypted;
    private String raw_checksum;

    // TODO: 6/5/20
    public static SharedFileMetaData parseDataFromTx(CompletedTransaction transaction) throws IllegalArgumentException {
        try {
            return GsonSingleton.getInstance().fromJson(transaction.toString(), SharedFileMetaData.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid properties of Shared file meta-data");
        }
    }
}
