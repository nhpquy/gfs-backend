package com.gfs.domain.request;

import lombok.Data;

@Data
public class ListSharedFilesPagingRequest extends PagingRequest {
    private String receiver_id;
}
