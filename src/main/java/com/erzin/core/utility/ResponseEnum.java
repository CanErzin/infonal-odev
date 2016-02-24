package com.erzin.core.utility;

/**
 * This enum class holds properties of the user entity and
 * names of the columns for that properties.
 *
 *
 */
public enum ResponseEnum {
    SUCCESS("SUCCESS"),
    ERROR("ERROR"),
    CAPTA_FAIL("CAPTA_FAIL");



    private String message ;

    ResponseEnum(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
