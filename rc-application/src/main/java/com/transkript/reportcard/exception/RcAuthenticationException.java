package com.transkript.reportcard.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;


public class RcAuthenticationException extends AuthenticationException {
    @Getter private HttpStatus status;

    public RcAuthenticationException(String msg) {
        super(msg);
        this.status = HttpStatus.UNAUTHORIZED;
    }
    public RcAuthenticationException(String msg, Throwable cause) {
        super(msg, cause);
        this.status = HttpStatus.UNAUTHORIZED;
    }
    public RcAuthenticationException(String msg, HttpStatus status) {
        this(msg);
        this.status = status;
    }
}
