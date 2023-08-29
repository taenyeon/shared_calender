package com.project.shared_calender.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.shared_calender.domain.user.constant.Gender;
import com.project.shared_calender.domain.user.constant.UserType;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private long id;

    private String email;

    private UserType type;

    private String name;

    private String phoneNumber;

    private Gender gender;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyAt;

    private UserProfile userProfile;
}
