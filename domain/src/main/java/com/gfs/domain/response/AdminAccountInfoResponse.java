package com.gfs.domain.response;

import com.gfs.domain.document.AdminAccount;
import com.gfs.domain.document.ObjectIdDocument;
import com.gfs.domain.enums.AccountStatus;
import com.gfs.domain.enums.admin.AdminRole;
import lombok.Data;

@Data
public class AdminAccountInfoResponse extends DocumentResponse {
    private String account_id;
    private String username;
    private String email;
    private String name;
    private AdminRole role;
    private AccountStatus account_status;

    public AdminAccountInfoResponse() {
    }

    public AdminAccountInfoResponse(String hex_id, long created_at, long updated_at) {
        super(hex_id, created_at, updated_at);
    }

    public AdminAccountInfoResponse(ObjectIdDocument document) {
        super(document);
    }

    public AdminAccountInfoResponse(String account_id, String username, String email, String name, AdminRole role, AccountStatus account_status) {
        this.account_id = account_id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.role = role;
        this.account_status = account_status;
    }

    public AdminAccountInfoResponse(String hex_id, long created_at, long updated_at, String account_id, String username, String email, String name, AdminRole role, AccountStatus account_status) {
        super(hex_id, created_at, updated_at);
        this.account_id = account_id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.role = role;
        this.account_status = account_status;
    }

    public AdminAccountInfoResponse(ObjectIdDocument document, String account_id, String username, String email, String name, AdminRole role, AccountStatus account_status) {
        super(document);
        this.account_id = account_id;
        this.username = username;
        this.email = email;
        this.name = name;
        this.role = role;
        this.account_status = account_status;
    }

    public AdminAccountInfoResponse(AdminAccount adminAccount) {
        this(adminAccount,
                adminAccount.getAccount_id(),
                adminAccount.getUsername(),
                adminAccount.getEmail(),
                adminAccount.getName(),
                adminAccount.getRole(),
                adminAccount.getAccount_status());
    }
}
