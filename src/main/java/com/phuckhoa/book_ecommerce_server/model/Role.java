package com.phuckhoa.book_ecommerce_server.model;

public enum Role {
    USER("USER"),
    ADMIN("ADMIN"),
    MANAGER("MANAGER");

    private String code;

    Role(String newRole) {
        this.code = newRole;
    }

    public String getCode() {
        return this.code;
    }
}
