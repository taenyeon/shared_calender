package com.project.shared_calender.domain.calender.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.shared_calender.domain.calender.constant.CalenderType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Calender {
    private long id;

    private String name;

    private long owner;

    private CalenderType calenderType;

    private Boolean isDeleted;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyAt;

}
