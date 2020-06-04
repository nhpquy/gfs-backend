package com.gfs.domain.request;

import com.gfs.domain.annotations.ObjectId;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagingRequest {
    @ObjectId
    private String from_hex_id;
    private long from;
    private long to;
    private String action;
    private int limit;

    public PagingRequest(String from_hex_id, long from, long to, String action, int limit) {
        this.from_hex_id = from_hex_id;
        this.from = from;
        this.to = to;
        this.action = action;
        this.limit = limit;
    }

    public int getLimit() {
        if (limit <= 0 || limit > 50)
            return 50;
        return limit;
    }
}
