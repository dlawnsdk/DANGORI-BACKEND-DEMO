package com.dangori.backend.user.domain;

import com.dangori.backend.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_detail")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserDetailInfo extends BaseEntity{

    @MapsId   // user_info.seq를 PK로 공유
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seq")
    private UserInfo user;

    @Column(name = "user_nickname", unique = true)
    private String nickname;

    @Column(name = "user_gender", columnDefinition = "enum('M','W')")
    private String gender;

    @Column(name = "user_profile_image")
    private String profileImage;

    @Column(name = "business_info_seq")
    private Long businessInfoSeq;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    public void updateBasicInfo(String nickname, String gender) {
        this.nickname = nickname;
        this.gender = gender;
    }

    public void assignUser(UserInfo user) {
        this.user = user;
        this.seq = (user != null ? user.getSeq() : null);
    }
}
