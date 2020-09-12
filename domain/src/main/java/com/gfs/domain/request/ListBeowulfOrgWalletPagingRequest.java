package com.gfs.domain.request;

import lombok.Data;

@Data
public class ListBeowulfOrgWalletPagingRequest extends PagingRequest {
    private String wallet_name;
}
