package com.gfs.domain.response;

import com.gfs.domain.config.model.SharedFileMetaData;
import com.gfs.domain.document.GFSSharedFile;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GFSSharedFileResponse extends DocumentResponse {
    private String share_id;
    private String transaction_id;
    private SharedFileMetaData tx_data;
    private String description;

    public GFSSharedFileResponse(GFSSharedFile sharedFile) {
        super(sharedFile);
        this.setShare_id(sharedFile.getShare_id());
        this.setTransaction_id(sharedFile.getTransaction_id());
        this.setTx_data(sharedFile.getTx_data());
        this.setDescription(sharedFile.getDescription());
    }
}
