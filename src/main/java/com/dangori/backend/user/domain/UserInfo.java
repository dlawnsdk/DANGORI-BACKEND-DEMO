package com.dangori.backend.user.domain;

import com.dangori.backend.common.domain.BaseEntity;
import com.dangori.backend.common.enums.YnType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;

// CREATE TABLE user_info (
//        seq BIGINT NOT NULL AUTO_INCREMENT,
//        user_email VARCHAR(255) NOT NULL,
//        user_password VARCHAR(255) NOT NULL,
//        withdraw_flag ENUM('Y','N') NOT NULL DEFAULT 'N',
//        ban_flag ENUM('Y','N') NOT NULL DEFAULT 'N',
//        register_code BIGINT NOT NULL,
//        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
//        updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
//        PRIMARY KEY (seq),
//        UNIQUE KEY uk_user_email (user_email),
//        INDEX idx_withdraw_flag (withdraw_flag),
//        INDEX idx_ban_flag (ban_flag),
//        INDEX idx_register_code (register_code),
//        INDEX idx_created_at (created_at),
//        INDEX idx_updated_at (updated_at)
// ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

@Entity
@Table(name = "user_info")
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo extends BaseEntity {

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_password")
    private String userPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "withdraw_flag")
    private YnType withdrawFlag;

    @Enumerated(EnumType.STRING)
    @Column(name = "ban_flag")
    private YnType banFlag;

    @Column(name = "register_code")
    private Long registerCode;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @OneToOne(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserDetailInfo detail;

    @Builder
    private UserInfo(String userEmail, String userPassword, Long registerCode) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.registerCode = registerCode;
    }

    public static UserInfo of(String userEmail, String userPassword, Long registerCode) {
        return new UserInfo(userEmail, userPassword, registerCode);
    }
}
