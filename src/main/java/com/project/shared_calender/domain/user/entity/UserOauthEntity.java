package com.project.shared_calender.domain.user.entity;

import com.project.shared_calender.domain.user.constant.OauthType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Service
@Entity
@Table(name = "tb_user_oauth")
public class UserOauthEntity {
    @Id
    @Column(name = "userSeq")
    private long id;
    @Convert(converter = OauthType.AttributeConverter.class)
    private OauthType type;

    private String oauthKey;
}
