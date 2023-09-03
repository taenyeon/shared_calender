package com.project.shared_calender.service;

import com.project.shared_calender.common.constant.ResponseCode;
import com.project.shared_calender.common.exception.ResponseException;
import com.project.shared_calender.domain.user.dto.User;
import com.project.shared_calender.domain.user.dto.UserProfile;
import com.project.shared_calender.domain.user.dto.UserSimple;
import com.project.shared_calender.domain.user.entity.UserEntity;
import com.project.shared_calender.domain.user.entity.UserProfileEntity;
import com.project.shared_calender.domain.user.mapper.UserMapper;
import com.project.shared_calender.domain.user.mapper.UserProfileMapper;
import com.project.shared_calender.domain.user.repository.UserProfileRepository;
import com.project.shared_calender.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final UserProfileMapper userProfileMapper = UserProfileMapper.MAPPER;

    public boolean save(long userId) {
        // 처음 save는 회원 생성 시점에서 동작하기 때문에 빈 값으로 세팅해줌
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userId);
        UserProfileEntity userProfileEntity = userProfileMapper.toEntity(userProfile);
        return userProfileRepository.save(userProfileEntity).getId() == userId;
    }

    public boolean modify(long userId, UserProfile userProfile) {
        UserProfileEntity userProfileEntity = userProfileMapper.merge(userId, userProfile);
        return userProfileRepository.save(userProfileEntity).getId() == userId;
    }

    public UserProfile getById(long userId) {
        UserProfileEntity userProfileEntity = userProfileRepository.findById(userId)
                .orElseThrow(() -> new ResponseException(ResponseCode.NO_RESULT));
        return userProfileMapper.toDto(userProfileEntity);
    }


}
