package com.project.shared_calender.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "tb_user_profile")
public class UserProfileEntity {

    @Id
    @Column(name = "userSeq")
    private long userId;
    private String profileText;
    private String profileImageUrl;
}
