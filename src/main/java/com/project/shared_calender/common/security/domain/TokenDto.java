package com.project.shared_calender.common.security.domain;

import com.project.shared_calender.domain.user.dto.UserSimple;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDto {

    private String refreshToken;
    private String accessToken;
    private UserSimple userSimple;

}

