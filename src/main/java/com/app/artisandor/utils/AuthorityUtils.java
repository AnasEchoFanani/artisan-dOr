package com.app.artisandor.utils;

import com.app.carrentalmongodb.enums.Authority;
import com.app.carrentalmongodb.enums.Roles;

import java.util.EnumSet;
import java.util.Set;

public class AuthorityUtils {

    public static Set<Authority> getAuthoritiesForRole(Roles role) {
        return switch (role) {
            case ROLE_SUPER_ADMIN -> EnumSet.allOf(Authority.class);
            case ROLE_ADMIN -> EnumSet.of(
                    Authority.MANAGE_USERS,
                    Authority.VIEW_LOGS,
                    Authority.READ,
                    Authority.WRITE,
                    Authority.DELETE,
                    Authority.UPDATE,
                    Authority.MANAGE_VEHICLES,
                    Authority.MANAGE_RENTAL,
                    Authority.MANAGE_LOCALISATION,
                    Authority.MANAGE_BOOKING
            );
            case ROLE_MODERATOR -> EnumSet.of(
                    Authority.VIEW_LOGS,
                    Authority.READ,
                    Authority.WRITE,
                    Authority.DELETE,
                    Authority.UPDATE,
                    Authority.MANAGE_VEHICLES,
                    Authority.MANAGE_RENTAL,
                    Authority.MANAGE_BOOKING
            );
            case ROLE_EMPLOYER -> EnumSet.of(
                    Authority.READ,
                    Authority.WRITE,
                    Authority.DELETE,
                    Authority.UPDATE,
                    Authority.MANAGE_RENTAL,
                    Authority.MANAGE_BOOKING
            );
            case ROLE_EDITOR, ROLE_CUSTOMER -> EnumSet.of(
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
