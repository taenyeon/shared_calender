package com.project.shared_calender.service;

import com.project.shared_calender.common.constant.ResponseCode;
import com.project.shared_calender.common.encoder.EncryptUtil;
import com.project.shared_calender.common.exception.ResponseException;
import com.project.shared_calender.common.security.JwtTokenProvider;
import com.project.shared_calender.common.security.domain.TokenDto;
import com.project.shared_calender.domain.user.dto.User;
import com.project.shared_calender.domain.user.dto.UserSimple;
import com.project.shared_calender.domain.user.entity.UserEntity;
import com.project.shared_calender.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MyService {
    private final UserService userService;
    private final UserMapper userMapper = UserMapper.MAPPER;
    private final JwtTokenProvider jwtTokenProvider;

    public Boolean join(User user, String password) {
        return userService.save(user, password);
    }

    public TokenDto login(String email, String password) {
        String encryptEmail;
        try {
            encryptEmail = EncryptUtil.encrypt(email);
        } catch (Exception e) {
            throw new ResponseException(ResponseCode.INVALID_REQUEST);
        }
        UserEntity userEntity = userService.getEntityByEmail(encryptEmail);
        userService.checkIsSamePassword(password, userEntity.getPassword());
        UserSimple userSimple = userMapper.toSimpleDetail(userEntity);
        TokenDto tokenDto = jwtTokenProvider.generateToken(userEntity.getId());
        tokenDto.setUserSimple(userSimple);
        return tokenDto;
    }

    public boolean logout(String refreshToken) {
        return jwtTokenProvider.dropRefreshToken(refreshToken);
    }

}
