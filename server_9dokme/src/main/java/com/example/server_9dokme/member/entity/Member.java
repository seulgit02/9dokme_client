package com.example.server_9dokme.member.entity;
import com.example.server_9dokme.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "subscribe_id")
    private Long subscribeId;

    private Integer userRole;

    private String password;

    private String nickName;

    private String socialId;   //이메일

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    public enum SocialType { // 수정 가능성 있음
        KAKAO
    }
}
