package com.project.shared_calender.common.exception;

import com.project.shared_calender.common.constant.ResponseCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseException extends RuntimeException {
    ResponseCode errorResponse;

    private String originalExceptionMessage;

    public ResponseException(ResponseCode errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ResponseException(Exception exception) {
        this.errorResponse = ResponseCode.UNKNOWN_REASON;
        this.originalExceptionMessage = exception.getMessage();
    }

    public ResponseException(ResponseCode errorResponse, Exception exception) {
        this.errorResponse = errorResponse;
        this.originalExceptionMessage = exception.getMessage();
    }

    @Override
    public String toString() {
        return "ResponseException{" +
                "errorResponse= resultCode : " + errorResponse.getResultCode() + ", resultMessage : "+errorResponse.getResultMessage() +
                ", originalExceptionMessage='" + originalExceptionMessage + '\'' +
                '}';
    }
}
