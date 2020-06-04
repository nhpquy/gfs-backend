package com.gfs.domain.model;

import com.gfs.domain.document.Account;

public class ConnectedGoogleResponseModel {
    private String user_id;
    private String name;
    private String email;
    private String picture;

    public ConnectedGoogleResponseModel() {
    }

    public ConnectedGoogleResponseModel(String user_id, String name, String email, String picture) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public ConnectedGoogleResponseModel(Account account) {
        this();
        setUser_id(account.getGg_account_id());
        GoogleUserData data = account.getGg_user_data();
        if (data != null) {
            setEmail(data.getEmail());
            setName(data.getName());
            setPicture(data.getPicture());
        }
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
