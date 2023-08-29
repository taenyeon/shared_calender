package com.project.shared_calender.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "user_profile")
public class UserProfileEntity {

    @Id
    @Column(name = "id")
    private long id;
    private String message;
    private String imageUrl;
}
