package org.example.java19_final9.errors.handler;

import org.springframework.security.access.AccessDeniedException;

public class DeniedException extends AccessDeniedException {
    public DeniedException(String msg) {
        super(msg);
    }
}
