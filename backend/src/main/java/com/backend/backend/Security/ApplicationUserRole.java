package com.backend.backend.Security;

import static com.backend.backend.Security.ApplicationUserPermission.USER_PET;

import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum ApplicationUserRole {
    USER(Sets.newHashSet()), ADMIN(Sets.newHashSet(USER_PET)), ADMINTRAINEE(Sets.newHashSet());

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
