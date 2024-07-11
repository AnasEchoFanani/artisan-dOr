package com.app.artisandor.utils;

import com.app.artisandor.enums.Authority;
import com.app.artisandor.enums.Roles;

import java.util.EnumSet;
import java.util.Set;

public class AuthorityUtils {

    public static Set<Authority> getAuthoritiesForRole(Roles role) {
        return switch (role) {
            case ROLE_ADMIN -> EnumSet.allOf(Authority.class);
            case ROLE_CUSTOMER -> EnumSet.of(
                    Authority.READ,
                    Authority.WRITE,
                    Authority.UPDATE
            );
            case ROLE_GUEST -> EnumSet.of(
                    Authority.READ
            );
        };
    }
}
