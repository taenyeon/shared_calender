package com.project.shared_calender.domain.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
    private long id;
    private String message;
    private String imageUrl;
}
