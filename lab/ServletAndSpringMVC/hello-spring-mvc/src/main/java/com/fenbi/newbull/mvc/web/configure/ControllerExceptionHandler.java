package com.fenbi.newbull.mvc.web.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestController
@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<String> handle(NullPointerException ex) {
        log.info("got exception", ex);
        return new ResponseEntity<>("some thing is wrong", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
