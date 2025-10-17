package com.dangori.backend.user.domain;

import com.dangori.backend.common.domain.BaseEntity;
import com.dangori.backend.common.enums.YnType;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_info")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserInfo extends BaseEntity {

    @Column(name = "user_email", unique = true)
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_number", unique = true)
    private String userNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "withdraw_flag", columnDefinition = "enum('Y','N')")
    private YnType withdrawFlag;

    @Enumerated(EnumType.STRING)
    @Column(name = "ban_flag", columnDefinition = "enum('Y','N')")
    private YnType banFlag;

    @Column(name = "register_code")
    private Long registerCode;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserDetailInfo detail;

    public void addDetail(UserDetailInfo detail) {
        this.detail = detail;
        if (detail != null) detail.assignUser(this); // 양방향 세팅 + @MapsId 동기화
    }
}
