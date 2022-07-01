package com.transkript.reportcard.data.enums;

public enum Role {
    ROLE_USER("user"), ROLE_MANAGER("teacher"), ROLE_ADMIN("admin");

    private final String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
