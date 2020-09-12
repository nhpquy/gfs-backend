package com.gfs.domain.enums;

public enum OrgRole {
    none(-1),
    readonly(0),
    editor(1),
    admin(2),
    owner(9999);

    int value;

    OrgRole(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
