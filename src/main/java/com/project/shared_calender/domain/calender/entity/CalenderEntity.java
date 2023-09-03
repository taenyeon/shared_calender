package com.project.shared_calender.domain.calender.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.shared_calender.domain.calender.constant.CalenderType;
import com.project.shared_calender.domain.user.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
@DynamicUpdate
@Builder
@Table(name = "calender")
public class CalenderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private long owner;

    @Convert(converter = CalenderType.AttributeConverter.class)
    private CalenderType calenderType;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyAt;

    private Boolean isDeleted;
}
