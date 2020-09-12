package com.gfs.domain.repository.extend;


import com.gfs.domain.document.BeowulfWallet;
import com.gfs.domain.request.ListBeowulfOrgWalletPagingRequest;

import java.util.List;

public interface BeowulfWalletRepositoryExtend {
    public List<BeowulfWallet> findWalletPaging(ListBeowulfOrgWalletPagingRequest request);
}
