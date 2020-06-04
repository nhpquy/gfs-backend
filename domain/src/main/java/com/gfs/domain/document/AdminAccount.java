package com.gfs.domain.document;

import com.gfs.domain.constant.CollectionName;
import com.gfs.domain.enums.AccountStatus;
import com.gfs.domain.enums.admin.AdminRole;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = CollectionName.ADMIN_ACCOUNTS)
public class AdminAccount extends ObjectIdDocument {
    @Indexed(unique = true)
    private String account_id;
    @Indexed(unique = true)
    private String username;
    private String email;
    private String password;
    private String name;
    private AdminRole role;
    private AccountStatus account_status;
}
