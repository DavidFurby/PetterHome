package com.backend.backend.Security;

public enum ApplicationUserPermission {
    USER_PET("user:pet"), USER_INFO("user:info");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
