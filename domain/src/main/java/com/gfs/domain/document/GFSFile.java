package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.enums.GFSFileStatus;
import com.gfs.domain.request.UploadFileRequest;
import com.gfs.domain.utils.StringUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = CollectionName.GFS_FILE)
@CompoundIndex(def = "{'hash_code': 1, 'owner_id': 1}", unique = true)
public class GFSFile extends ObjectIdDocument {

    /**
     * Authorized Info
     */
    @Indexed
    private String hash_code;
    @Indexed
    private String owner_id;


    /**
     * General Info
     */
    private String absolute_path;
    private String file_name;
    private long size;
    private String content_type;
    private String description;
    private String password;
    private boolean encrypted;

    /**
     * Other Info
     */
    private String storage_address;
    private List<String> links;
    @Indexed
    private GFSFileStatus status;


    public GFSFile(UploadFileRequest request, String owner_id) {
        super();
        this.setOwner_id(owner_id);
        this.setFile_name(request.getName());
        this.setDescription(request.getDescription());
        if (StringUtils.hasText(request.getEncrypted_pwd())) {
            this.setPassword(request.getEncrypted_pwd());
            this.setEncrypted(true);
        }
        if (request.isPublished()) {
            this.setStatus(GFSFileStatus.published);
        } else {
            this.setStatus(GFSFileStatus.unpublished);
        }
    }
}