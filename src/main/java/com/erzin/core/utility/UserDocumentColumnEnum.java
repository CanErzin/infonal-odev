package com.erzin.core.utility;

/**
 * This enum class holds properties of the user entity and
 * names of the columns for that properties.
 *
 *
 */
public enum UserDocumentColumnEnum {
    ID("id"),
    NAME("name"),
    LAST_NAME("lastName"),
    PHONE("phoneNumber");


    private String columnName;

    UserDocumentColumnEnum(String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }

}
