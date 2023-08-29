package com.project.shared_calender.service;

import com.project.shared_calender.common.constant.ResponseCode;
import com.project.shared_calender.common.exception.ResponseException;
import com.project.shared_calender.domain.user.dto.User;
import com.project.shared_calender.domain.user.dto.UserSimple;
import com.project.shared_calender.domain.user.entity.UserEntity;
import com.project.shared_calender.domain.user.mapper.UserMapper;
import com.project.shared_calender.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.MAPPER;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // ENTITY METHOD
    private UserEntity getEntityById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseCode.NO_RESULT));
    }

    public UserEntity getEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseException(ResponseCode.INVALID_REQUEST_PARAM));
    }

    public Boolean save(User user, String password) {
        checkIsIdentifyEmail(user.getEmail());
        password = getEncodedPassword(password);
        UserEntity userEntity = userMapper.toEntity(user, password);
        return userRepository.save(userEntity).getId() > 0;
    }

    public void modify(long id, User afterUser) {
        User userEntity = userMapper.toDto(getEntityById(id));
        userMapper.merge(userEntity, afterUser);
        userRepository.save(userMapper.toEntity(userEntity));
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    // DTO METHOD

    public User getById(long id) {
        UserEntity userEntity = getEntityById(id);
        return userMapper.toDto(userEntity);
    }


    public UserSimple getSimpleById(long id) {
        UserEntity userEntity = getEntityById(id);
        return userMapper.toSimpleDetail(userEntity);
    }
    private String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }

    // VALIDATE METHOD
    private void checkIsIdentifyEmail(String email) {
        if (this.getEntityByEmail(email) != null) {
            throw new ResponseException(ResponseCode.INVALID_REQUEST_PARAM);
        }
    }

    public void checkIsSamePassword(String rawPassword, String encryptPassword) {
        if (!passwordEncoder.matches(rawPassword, encryptPassword)) {
            throw new ResponseException(ResponseCode.NO_RESULT);
        }
    }


}
