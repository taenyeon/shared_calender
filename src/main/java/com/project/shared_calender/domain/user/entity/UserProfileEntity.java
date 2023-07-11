package com.project.shared_calender.domain.user.entity;

import lombok.AllArgsConstructor;
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
@Service
@Entity
@Table(name = "tb_user_profile")
public class UserProfileEntity {

    @Id
    @Column(name = "userSeq")
    private long id;
    private String profileText;
    private String profileImageUrl;
}
