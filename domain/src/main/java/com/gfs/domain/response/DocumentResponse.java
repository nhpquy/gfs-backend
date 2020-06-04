package com.gfs.domain.response;

import com.gfs.domain.document.ObjectIdDocument;

public class DocumentResponse extends BaseResponse {
    private String hex_id;
    private long created_at;
    private long updated_at;

    public DocumentResponse() {
        super();
    }

    public DocumentResponse(String hex_id, long created_at, long updated_at) {
        super();
        this.hex_id = hex_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public DocumentResponse(ObjectIdDocument document) {
        this(document.getId().toHexString(), document.getCreated_at(), document.getUpdated_at());
    }

    public String getHex_id() {
        return hex_id;
    }

    public void setHex_id(String hex_id) {
        this.hex_id = hex_id;
    }

    public long getCreated_at() {
        return created_at;
    }

    public void setCreated_at(long created_at) {
        this.created_at = created_at;
    }

    public long getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(long updated_at) {
        this.updated_at = updated_at;
    }
}
