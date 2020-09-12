package com.gfs.domain.enums;

public enum AccountStatus {
    verifying(1),
    activated(2),
    approved(3),
    deactivated(-1),
    banned(-2),
    deleted(-3);

    private int statusCode;

    AccountStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
