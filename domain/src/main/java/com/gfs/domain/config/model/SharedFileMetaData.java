package com.gfs.domain.config.model;

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
}
