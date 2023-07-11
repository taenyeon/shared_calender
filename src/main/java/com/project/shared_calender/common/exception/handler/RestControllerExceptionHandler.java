package com.project.shared_calender.common.exception.handler;

import com.project.shared_calender.common.constant.ResponseCode;
import com.project.shared_calender.common.exception.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class RestControllerExceptionHandler {

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<Object> handleResponseException(ResponseException responseException){
        log.error(responseException.toString());
        return responseException.getErrorResponse().response();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleResponseException(Exception e){
        e.printStackTrace();
        return ResponseCode.UNKNOWN_REASON.response();
    }
}