package com.gfs.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadFileRequest {
    private String name;
    private String description;
    private String encrypted_pwd;
    private boolean published;
}
