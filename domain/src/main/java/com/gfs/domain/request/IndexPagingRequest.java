package com.gfs.domain.request;

import lombok.Data;

@Data
public class IndexPagingRequest {
    private int page;
    private int limit;
    private long from;
    private long to;

    public int getLimit() {
        if (limit <= 0 || limit > 50)
            return 50;
        return limit;
    }
}
