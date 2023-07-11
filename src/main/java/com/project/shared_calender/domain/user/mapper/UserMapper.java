package com.project.shared_calender.domain.user.mapper;

import com.project.shared_calender.common.interfaces.EntityMapper;
import com.project.shared_calender.domain.user.dto.User;
import com.project.shared_calender.domain.user.dto.UserSimpleDetail;
import com.project.shared_calender.domain.user.entity.UserEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends EntityMapper<UserEntity, User> {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);
    @Override
    UserEntity toEntity(final User dto);

    UserEntity toEntity(User dto, String password);

    @Override
    User toDto(final UserEntity entity);

    UserSimpleDetail toSimpleDetail(final UserEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    void merge(@MappingTarget UserEntity entity, User user);
}
