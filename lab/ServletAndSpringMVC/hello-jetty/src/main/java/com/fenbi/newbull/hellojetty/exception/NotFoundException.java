package com.fenbi.newbull.hellojetty.exception;

import com.fenbi.newbull.hellojetty.annotation.MyResponseStatus;
import org.eclipse.jetty.http.HttpStatus;

@MyResponseStatus(HttpStatus.NOT_FOUND_404)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}