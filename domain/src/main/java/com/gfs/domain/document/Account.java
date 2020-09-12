package com.gfs.domain.document;

import com.gfs.domain.enums.AccountOnlineStatus;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.enums.AccountStatus;
import com.gfs.domain.enums.Gender;
import com.gfs.domain.model.FacebookUserData;
import com.gfs.domain.model.GoogleUserData;
import com.gfs.domain.model.VerificationToken;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
public class Account extends ObjectIdDocument {

    private AccountProfile profile_type;
    @Indexed(unique = true)
    private String account_id;

    /**
     * Authorize Info
     */
    private String phone_number;
    @Indexed(unique = true)
    private String email;
    private String password;
    private boolean secure_pwd;

    /**
     * Profile Info
     */
    private String name;
    private Gender gender;
    private String address;
    private String avatar;
    private String cover;
    private String date_of_birth;

    /**
     * Social connect
     */
    private String fb_account_id;
    private FacebookUserData fb_user_data;
    private String gg_account_id;
    private GoogleUserData gg_user_data;

    /**
     * Other Info
     */
    private AccountStatus account_status;
    private AccountOnlineStatus online_status;
    private VerificationToken activate_token;
    private VerificationToken forgot_pwd_token;
    private VerificationToken verify_email_token;
    private VerificationToken change_phone_token;

    /**
     * Organization Info
     */
    private String current_org_id;


    public Account(Account account) {
        setProfile_type(account.getProfile_type());
        setAccount_id(account.getAccount_id());
        setPhone_number(account.getPhone_number());
        setEmail(account.getEmail());
        setPassword(account.getPassword());
        setSecure_pwd(account.isSecure_pwd());
        setName(account.getName());
        setGender(account.getGender());
        setAddress(account.getAddress());
        setDate_of_birth(account.getDate_of_birth());
        setAvatar(account.getAvatar());
        setCover(account.getCover());
        setAccount_status(account.getAccount_status());
        setOnline_status(account.getOnline_status());
        setActivate_token(account.getActivate_token());
    }
}
