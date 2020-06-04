package com.gfs.admin.service.inf;

import com.gfs.domain.document.AdminAccount;
import com.gfs.domain.enums.admin.AdminAction;

public interface AdminActivityService {
    public void newActivity(AdminAccount adminAccount, AdminAction action, String refId, String note, Object data, Object before, Object after);
}
