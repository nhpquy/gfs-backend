package com.gfs.domain.request;

import com.gfs.domain.annotations.Email;
import com.gfs.domain.enums.OrganizationStatus;
import lombok.Data;

import java.util.List;

@Data
public class ListOrgPagingRequest extends PagingRequest {
    private String org_name;
    @Email
    private String org_email;
    private List<OrganizationStatus> statuses;
}
