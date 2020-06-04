package com.gfs.admin.service.impl;

import com.gfs.admin.service.inf.AdminActivityService;
import com.gfs.domain.document.AdminAccount;
import com.gfs.domain.document.AdminActivityLog;
import com.gfs.domain.enums.admin.AdminAction;
import com.gfs.domain.repository.inf.AdminActivityLogRepository;
import com.gfs.domain.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminActivityServiceImpl implements AdminActivityService {

    @Autowired
    AdminActivityLogRepository adminActivityLogRepository;

    @Override
    public void newActivity(AdminAccount adminAccount, AdminAction action, String refId, String note, Object data, Object before, Object after) {
        try {
            AdminActivityLog log = new AdminActivityLog();
            log.setAdmin_id(adminAccount.getAccount_id());
            log.setAction(action);
            log.setReference_id(refId);
            log.setNote(note);
            log.setData(data);
            log.setBefore(before);
            log.setAfter(after);
            adminActivityLogRepository.save(log);
        } catch (Exception e) {
            LoggerUtil.e(this, e.getMessage());
        }
    }
}
