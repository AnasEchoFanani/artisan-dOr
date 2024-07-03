package com.app.artisandor.enums;

/**
 * This enum represents different authorities that a user can have in the application.
 * Each authority is represented by a constant in this enum.
 */
public enum Authority {
    /**
     * Authority to read data.
     */
    READ,

    /**
     * Authority to write or create data.
     */
    WRITE,

    /**
     * Authority to delete data.
     */
    DELETE,

    /**
     * Authority to update or modify existing data.
     */
    UPDATE,

    /**
     * Authority to view logs.
     */
    VIEW_LOGS,

    /**
     * Authority to configure application settings.
     */
    CONFIGURE_SETTINGS
}
