package com.project.shared_calender.domain.user.constant;

import com.project.shared_calender.common.constant.ResponseCode;
import com.project.shared_calender.common.exception.ResponseException;

import javax.persistence.Converter;
import java.util.Arrays;

public enum Gender {
    MALE("M", "남성"),
    FEMALE("W", "여성"),
    X("X", "미수집");

    private final String code;
    private final String explanation;

    Gender(String code, String explanation) {
        this.code = code;
        this.explanation = explanation;
    }

    public String getCode() {
        return this.code;
    }

    public String getExplanation() {
        return this.explanation;
    }

    @Converter
    public static class AttributeConverter implements javax.persistence.AttributeConverter<Gender, String> {
        @Override
        public String convertToDatabaseColumn(Gender type) {
            return type.getCode();
        }

        @Override
        public Gender convertToEntityAttribute(String code) {
            return Arrays.stream(Gender.values())
                    .filter(type -> type.getCode().equals(code))
                    .findFirst()
                    .orElseThrow(() -> new ResponseException(ResponseCode.NO_RESULT));
        }
    }
}
