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
        checkIsIdentifyEmail(user.getEmail());
        UserEntity userEntity = userMapper.toEntity(user, password);
        return userRepository.save(userEntity).getId();
    }

    // todo email identify Exception 따로 관리 필요.
    private void checkIsIdentifyEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new ResponseException(ResponseCode.INVALID_REQUEST_PARAM);
        }
    }

    public User getById(long id) {
        UserEntity userEntity = getEntityById(id);
        return userMapper.toDto(userEntity);
    }


    public UserSimpleDetail getSimpleById(long id) {
        UserEntity userEntity = getEntityById(id);
        return userMapper.toSimpleDetail(userEntity);
    }

    public void modify(long id, User afterUser) {
        User userEntity = userMapper.toDto(getEntityById(id));
        userMapper.merge(userEntity, afterUser);
        userRepository.save(userMapper.toEntity(userEntity));
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }


}
