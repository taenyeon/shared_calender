package com.project.shared_calender.domain.calender.constant;

import com.project.shared_calender.common.constant.ResponseCode;
import com.project.shared_calender.common.exception.ResponseException;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum CalenderType {

    P("P", "개인 켈린더"),
    S("S", "공유 켈린더");
    private final String code;
    private final String description;

    CalenderType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static class AttributeConverter implements javax.persistence.AttributeConverter<CalenderType, String> {
        @Override
        public String convertToDatabaseColumn(CalenderType type) {
            return type.getCode();
        }

        @Override
        public CalenderType convertToEntityAttribute(String code) {
            return Arrays.stream(CalenderType.values())
                    .filter(type -> type.getCode().equals(code))
                    .findFirst()
                    .orElseThrow(() -> new ResponseException(ResponseCode.NO_RESULT));
        }
    }
}
