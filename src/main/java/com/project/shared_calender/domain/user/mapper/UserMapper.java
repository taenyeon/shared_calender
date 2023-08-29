package com.project.shared_calender.domain.user.mapper;

import com.project.shared_calender.common.encoder.EncryptUtil;
import com.project.shared_calender.common.interfaces.EntityMapper;
import com.project.shared_calender.domain.user.dto.User;
import com.project.shared_calender.domain.user.dto.UserSimple;
import com.project.shared_calender.domain.user.entity.UserEntity;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper extends EntityMapper<UserEntity, User> {
    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    @Override
    @Mapping(source = "email", target = "email", qualifiedByName = "encrypt")
    @Mapping(source = "name", target = "name", qualifiedByName = "encrypt")
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedByName = "encrypt")
    @Mapping(source = "userProfile", target = "userProfileEntity")
    UserEntity toEntity(final User dto);

    @Mapping(source = "dto.email", target = "email", qualifiedByName = "encrypt")
    @Mapping(source = "dto.name", target = "name", qualifiedByName = "encrypt")
    @Mapping(source = "dto.phoneNumber", target = "phoneNumber", qualifiedByName = "encrypt")
    @Mapping(source = "dto.userProfile", target = "userProfileEntity")
    UserEntity toEntity(User dto, String password);

    @Override
    @Mapping(source = "email", target = "email", qualifiedByName = "decrypt")
    @Mapping(source = "name", target = "name", qualifiedByName = "decrypt")
    @Mapping(source = "phoneNumber", target = "phoneNumber", qualifiedByName = "decrypt")
    @Mapping(source = "userProfileEntity", target = "userProfile")
    User toDto(final UserEntity entity);

    @Mapping(source = "email", target = "email", qualifiedByName = "decrypt")
    @Mapping(source = "name", target = "name", qualifiedByName = "decrypt")
    @Mapping(source = "userProfileEntity", target = "userProfile")
    UserSimple toSimpleDetail(final UserEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "createAt", ignore = true)
    void merge(@MappingTarget User beforeUser, User afterUser);

    @Named("decrypt")
    static String decrypt(String raw) throws Exception {
        return EncryptUtil.decrypt(raw);
    }
    @Named("encrypt")
    static String encrypt(String raw) throws Exception {
        return EncryptUtil.encrypt(raw);
    }

}
