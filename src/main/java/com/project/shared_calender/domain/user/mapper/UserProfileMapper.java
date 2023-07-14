package com.project.shared_calender.domain.user.mapper;

import com.project.shared_calender.common.interfaces.EntityMapper;
import com.project.shared_calender.domain.user.dto.UserProfile;
import com.project.shared_calender.domain.user.entity.UserProfileEntity;

public interface UserProfileMapper extends EntityMapper<UserProfileEntity, UserProfile> {
    @Override
    UserProfileEntity toEntity(final UserProfile dto);

    @Override
    UserProfile toDto(final UserProfileEntity entity);
}
