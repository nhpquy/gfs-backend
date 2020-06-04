package com.gfs.domain.request;

import com.gfs.domain.enums.GFSFileStatus;
import lombok.Data;

@Data
public class ListOwnerFilesPagingRequest extends PagingRequest {
    private GFSFileStatus status;
    private Boolean encrypted;
}
