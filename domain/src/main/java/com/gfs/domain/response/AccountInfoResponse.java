package com.gfs.domain.response;

import com.gfs.domain.document.Account;
import com.gfs.domain.document.ObjectIdDocument;
import com.gfs.domain.enums.AccountOnlineStatus;
import com.gfs.domain.enums.AccountProfile;
import com.gfs.domain.enums.AccountStatus;
import com.gfs.domain.enums.Gender;
import com.gfs.domain.model.ConnectedFacebookResponseModel;
import com.gfs.domain.model.ConnectedGoogleResponseModel;
import lombok.Data;

@Data
public class AccountInfoResponse extends DocumentResponse {
    private String account_id;
    private String phone_number;
    private String email;
    private boolean secure_pwd;
    private String name;
    private Gender gender;
    private String address;
    private String avatar;
    private String cover;
    private ConnectedFacebookResponseModel fb_user_data;
    private ConnectedGoogleResponseModel gg_user_data;
    private AccountStatus account_status;
    private AccountOnlineStatus online_status;
    private String date_of_birth;
    private AccountProfile profile;

    public AccountInfoResponse() {
    }

    public AccountInfoResponse(String hex_id, long created_at, long updated_at) {
        super(hex_id, created_at, updated_at);
    }

    public AccountInfoResponse(ObjectIdDocument document) {
        super(document);
    }

    public AccountInfoResponse(String account_id, String phone_number, String email, boolean secure_pwd, String name, Gender gender, String address, String avatar, String cover, ConnectedFacebookResponseModel fb_user_data, ConnectedGoogleResponseModel gg_user_data, AccountStatus account_status, AccountOnlineStatus online_status, String date_of_birth, AccountProfile profile) {
        this.account_id = account_id;
        this.phone_number = phone_number;
        this.email = email;
        this.secure_pwd = secure_pwd;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.avatar = avatar;
        this.cover = cover;
        this.fb_user_data = fb_user_data;
        this.gg_user_data = gg_user_data;
        this.account_status = account_status;
        this.online_status = online_status;
        this.date_of_birth = date_of_birth;
        this.profile = profile;
    }

    public AccountInfoResponse(String hex_id, long created_at, long updated_at, String account_id, String phone_number, String email, boolean secure_pwd, String name, Gender gender, String address, String avatar, String cover, ConnectedFacebookResponseModel fb_user_data, ConnectedGoogleResponseModel gg_user_data, AccountStatus account_status, AccountOnlineStatus online_status, String date_of_birth, AccountProfile profile) {
        super(hex_id, created_at, updated_at);
        this.account_id = account_id;
        this.phone_number = phone_number;
        this.email = email;
        this.secure_pwd = secure_pwd;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.avatar = avatar;
        this.cover = cover;
        this.fb_user_data = fb_user_data;
        this.gg_user_data = gg_user_data;
        this.account_status = account_status;
        this.online_status = online_status;
        this.date_of_birth = date_of_birth;
        this.profile = profile;
    }

    public AccountInfoResponse(ObjectIdDocument document, String account_id, String phone_number, String email, boolean secure_pwd, String name, Gender gender, String address, String avatar, String cover, ConnectedFacebookResponseModel fb_user_data, ConnectedGoogleResponseModel gg_user_data, AccountStatus account_status, AccountOnlineStatus online_status, String date_of_birth, AccountProfile profile) {
        super(document);
        this.account_id = account_id;
        this.phone_number = phone_number;
        this.email = email;
        this.secure_pwd = secure_pwd;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.avatar = avatar;
        this.cover = cover;
        this.fb_user_data = fb_user_data;
        this.gg_user_data = gg_user_data;
        this.account_status = account_status;
        this.online_status = online_status;
        this.date_of_birth = date_of_birth;
        this.profile = profile;
    }

    public AccountInfoResponse(Account account) {
        this(account,
                account.getAccount_id(),
                account.getPhone_number(),
                account.getEmail(),
                account.isSecure_pwd(),
                account.getName(),
                account.getGender(),
                account.getAddress(),
                account.getAvatar(),
                account.getCover(),
                new ConnectedFacebookResponseModel(account),
                new ConnectedGoogleResponseModel(account),
                account.getAccount_status(),
                account.getOnline_status(),
                account.getDate_of_birth(),
                account.getProfile_type());
    }
}
