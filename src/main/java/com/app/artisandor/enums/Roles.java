package com.app.artisandor.enums;

/**
 * This enum represents the different roles that users can have in the application.
 * The roles are defined as constants and can be used for authorization and access control.
 */
public enum Roles {
    /**
     * Represents a regular customer of the application.
     */
    ROLE_CUSTOMER,

    /**
     * Represents an administrator user who has full access to all features and data.
     */
    ROLE_ADMIN,

    /**
     * Represents a user of the application.
     */
    ROLE_GUEST
}
