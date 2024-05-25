package org.example.java19_final9.enums;

public enum AccountType {
    USER("USER"),
    ADMIN("ADMIN");

    private final String value;

    AccountType(String value) {
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}
