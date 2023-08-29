package com.project.shared_calender.domain.user.dto;

import com.project.shared_calender.domain.user.constant.UserType;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserSimple {
    private long id;

    private UserType type;

    private String email;

    private String name;

    private UserProfile userProfile;
}
