package com.project.shared_calender.domain.user.mapper;

import com.project.shared_calender.common.interfaces.EntityMapper;
import com.project.shared_calender.domain.user.dto.UserProfile;
import com.project.shared_calender.domain.user.entity.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserProfileMapper extends EntityMapper<UserProfileEntity, UserProfile> {

    UserProfileMapper MAPPER = Mappers.getMapper(UserProfileMapper.class);
    @Override
    UserProfileEntity toEntity(final UserProfile dto);

    @Override
    UserProfile toDto(final UserProfileEntity entity);

    UserProfileEntity merge(long id, final UserProfile dto);
}
