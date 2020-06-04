package com.gfs.domain.enums;

public enum AccountStatus {
    verifying(1),
    activated(2),
    approved(3),
    deactive(-1),
    banned(-2),
    tem_banned(-3),
    deleted(-4);

    private int statusCode;

    AccountStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
