package com.project.shared_calender.domain.user.constant;

import com.project.shared_calender.common.constant.ResponseCode;
import com.project.shared_calender.common.exception.ResponseException;

import javax.persistence.Converter;
import java.util.Arrays;

public enum OauthType {
    NAVER("N", "네이버"),
    KAKAO("K", "카카오"),
    GOOGLE("G", "구글");

    private final String code;
    private final String explanation;

    OauthType(String code, String explanation) {
        this.code = code;
        this.explanation = explanation;
    }

    public String getCode() {
        return code;
    }

    public String getExplanation() {
        return explanation;
    }

    @Converter
    public static class AttributeConverter implements com.project.shared_calender.domain.user.constant.AttributeConverter<OauthType, String> {
        @Override
        public String convertToDatabaseColumn(OauthType type) {
            return type.getCode();
        }

        @Override
        public OauthType convertToEntityAttribute(String code) {
            return Arrays.stream(OauthType.values())
                    .filter(type -> type.getCode().equals(code))
                    .findFirst()
                    .orElseThrow(() -> new ResponseException(ResponseCode.NO_RESULT));
        }
    }
}
