package com.example.demo.boot.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author: 04637
 * @date: 2019/4/30
 */
@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity handleServiceException(ServiceException e) {
        switch (e.getExpCode()) {
            case TOKEN_INVALID:
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getObject());
            case PERMISSION_DENIED:
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(e.getMessage());
            case UNPROCESSABLE_ENTITY:
                return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getObject());
            case NOT_FOUND:
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .body(e.getMessage());
            default:
                if (e.getThrowable() != null) {
                    e.getThrowable().printStackTrace();
                } else {
                    log.error(e.getMessage());
                }
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(e.getMessage());
    }

    @ExceptionHandler(MapperException.class)
    public ResponseEntity handleMapperException(MapperException e) {
        e.getThrowable().printStackTrace();
        log.error(e.getThrowable().getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }

    @ExceptionHandler(AuthException.class)
    public com.example.demo.boot.common.ResponseEntity handleAuthException(AuthException e) {
        return com.example.demo.boot.common.ResponseEntity.failed(e.getMessage())
                .setExpCode(ExpCode.TOKEN_INVALID);
    }
}
