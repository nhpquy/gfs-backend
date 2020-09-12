package com.gfs.domain.repository.extend;


import com.gfs.domain.document.Organization;
import com.gfs.domain.request.ListOrgPagingRequest;

import java.util.List;
import java.util.Map;

public interface OrganizationRepositoryExtend {
    public Organization updateDetail(String orgId, Map<String, Object> updateValues);

    public Organization decreaseWalletBwfNumber(String orgId);

    public Organization increaseWalletBwfNumber(String orgId);

    public List<Organization> findOrganizationPaging(ListOrgPagingRequest request);
}