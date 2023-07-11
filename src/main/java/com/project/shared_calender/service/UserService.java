package com.project.shared_calender.service;

import com.project.shared_calender.common.constant.ResponseCode;
import com.project.shared_calender.common.exception.ResponseException;
import com.project.shared_calender.domain.user.dto.User;
import com.project.shared_calender.domain.user.dto.UserSimpleDetail;
import com.project.shared_calender.domain.user.entity.UserEntity;
import com.project.shared_calender.domain.user.mapper.UserMapper;
import com.project.shared_calender.jpaRepository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.MAPPER;

    private UserEntity getEntityById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseException(ResponseCode.NO_RESULT));
    }

    public long join(User user, String password) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new ResponseException(ResponseCode.INVALID_REQUEST_PARAM);
        }
        UserEntity userEntity = userMapper.toEntity(user, password);
        return userRepository.save(userEntity).getId();
    }

    public User getById(long id) {
        UserEntity userEntity = getEntityById(id);
        return userMapper.toDto(userEntity);
    }


    public UserSimpleDetail getSimpleById(long id) {
        UserEntity userEntity = getEntityById(id);
        return userMapper.toSimpleDetail(userEntity);
    }

    public void modify(long id, User user) {
        UserEntity userEntity = getEntityById(id);
        userMapper.merge(userEntity, user);
        userRepository.save(userEntity);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

}
