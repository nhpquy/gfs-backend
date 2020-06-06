package com.gfs.domain.request;

import lombok.Data;

@Data
public class ListReceivedFilesPagingRequest extends PagingRequest {
    private String sender_id;
}
