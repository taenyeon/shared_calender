package com.project.shared_calender.domain.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.shared_calender.domain.user.constant.Gender;
import com.project.shared_calender.domain.user.constant.UserType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
@Entity
@Table(name = "tb_user")
public class UserEntity implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private long id;

    @Convert(converter = UserType.AttributeConverter.class)
    private UserType type;

    private String email;

    private String password;

    private String name;

    private String phoneNumber;

    @Convert(converter = Gender.AttributeConverter.class)
    private Gender gender;

    private LocalDate birthDay;

    private String profileText;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyAt;

    @OneToOne
    @JoinColumn(name = "userSeq")
    private UserProfileEntity userProfileEntity;

    @OneToOne
    @JoinColumn(name = "userSeq")
    private UserOauthEntity userOauthEntity;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}