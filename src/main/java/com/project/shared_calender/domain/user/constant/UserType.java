package com.project.shared_calender.domain.user.constant;

import com.project.shared_calender.common.constant.ResponseCode;
import com.project.shared_calender.common.exception.ResponseException;

import javax.persistence.Converter;
import java.util.Arrays;

public enum UserType {
    SERVER("S", "서버"),
    NAVER("N", "네이버"),
    KAKAO("K", "카카오"),
    GOOGLE("G", "구글");

    private final String code;
    private final String explanation;

    UserType(String code, String explanation) {
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
    public static class AttributeConverter implements javax.persistence.AttributeConverter<UserType, String> {
        @Override
        public String convertToDatabaseColumn(UserType type) {
            return type.getCode();
        }

        @Override
        public UserType convertToEntityAttribute(String code) {
            return Arrays.stream(UserType.values())
                    .filter(type -> type.getCode().equals(code))
                    .findFirst()
                    .orElseThrow(() -> new ResponseException(ResponseCode.NO_RESULT));
        }
    }
}
