package com.project.shared_calender.domain.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private long userId;
    private String profileText;
    private String profileImageUrl;
}
