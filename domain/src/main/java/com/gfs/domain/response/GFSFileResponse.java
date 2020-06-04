package com.gfs.domain.response;

import com.gfs.domain.document.GFSFile;
import com.gfs.domain.enums.GFSFileStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GFSFileResponse extends DocumentResponse {
    private String hash_code;
    private String owner_id;
    private String absolute_path;
    private String file_name;
    private long size;
    private String content_type;
    private String description;
    private String password;
    private boolean secure_pwd;
    private String storage_address;
    private List<String> links;
    private GFSFileStatus status;

    public GFSFileResponse(GFSFile file) {
        super(file);
        this.setHash_code(file.getHash_code());
        this.setOwner_id(file.getOwner_id());
        this.setAbsolute_path(file.getAbsolute_path());
        this.setFile_name(file.getFile_name());
        this.setSize(file.getSize());
        this.setContent_type(file.getContent_type());
        this.setDescription(file.getDescription());
        this.setPassword(file.getPassword());
        this.setSecure_pwd(file.isEncrypted());
        this.setStorage_address(file.getStorage_address());
        this.setLinks(file.getLinks());
        this.setStatus(file.getStatus());
    }
}
