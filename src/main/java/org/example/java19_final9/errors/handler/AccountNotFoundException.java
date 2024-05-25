package org.example.java19_final9.errors.handler;

import java.util.NoSuchElementException;

public class AccountNotFoundException extends NoSuchElementException {
    public AccountNotFoundException(String s) {
        super(s);
    }
}
