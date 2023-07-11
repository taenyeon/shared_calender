package com.project.shared_calender.common.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


@Getter
@RequiredArgsConstructor
public enum ResponseCode {
    // defaultError
    UNKNOWN_REASON ("CODE000","정의되지 않은 오류입니다."),
    SUCCESS("CODE001","성공"),
    NO_RESULT("CODE002","결과 값을 찾을 수 없습니다."),
    INVALID_REQUEST("CODE003","유효하지 않은 요청입니다."),
    INVALID_REQUEST_PARAM("CODE004","유효하지 않은 파라미터 값입니다."),

    //AUTH
    NOT_ALLOW_REFRESH_TOKEN("AUTH000","토근이 만료되었습니다."),
    INVALID_TOKEN("AUTH001", "유효하지 않은 토큰입니다."),
    NOT_AUTHORIZATION("AUTH002", "권한이 없습니다.")
    ;

    private final String resultCode;
    private final String resultMessage;

    public HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("resultCode",this.resultCode);
        headers.add("resultMessage",this.resultMessage);
        return headers;
    }

    public ResponseEntity<Object> response(){
        return ResponseEntity
                .ok()
                .headers(getHeaders())
                .build();
    }

    public ResponseEntity<Object> response(Object result){
        return ResponseEntity
                .ok()
                .headers(getHeaders())
                .body(result);

    }

}
