package com.project.shared_calender.domain.user.repository;

import com.project.shared_calender.domain.user.entity.UserEntity;
import com.project.shared_calender.domain.user.entity.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {

}
