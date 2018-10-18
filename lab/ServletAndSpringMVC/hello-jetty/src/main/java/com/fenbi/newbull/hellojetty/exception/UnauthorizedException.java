package com.fenbi.newbull.hellojetty.exception;

import com.fenbi.newbull.hellojetty.annotation.MyResponseStatus;
import org.eclipse.jetty.http.HttpStatus;

@MyResponseStatus(HttpStatus.UNAUTHORIZED_401)
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
    }

    public UnauthorizedException(String message) {
        super(message);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

    public UnauthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}