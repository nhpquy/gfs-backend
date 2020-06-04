package com.gfs.domain.document;

import com.gfs.domain.config.model.SharedFileMetaData;
import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.request.ShareFileRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@NoArgsConstructor
@Document(collection = CollectionName.GFS_SHARED_FILE)
public class GFSSharedFile extends ObjectIdDocument {

    /**
     * Authorized Info
     */
    @Indexed(unique = true)
    private String share_id;
    @Indexed
    private String owner_id;
    @Indexed
    private String receiver_id;


    /**
     * Shared file Info in Transaction
     */
    @Indexed
    private String transaction_id;
    private SharedFileMetaData meta_data;

    /**
     * Other Info
     */
    private String description;
    private String storage_address;


    public GFSSharedFile(ShareFileRequest request, String owner_id, String receiver_id) {
        super();
        this.setShare_id(UUID.randomUUID().toString());
        this.setOwner_id(owner_id);
        this.setReceiver_id(receiver_id);
        this.setTransaction_id(request.getTransaction_id());
        this.setDescription(request.getDescription());
    }
}