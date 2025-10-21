package com.dangori.backend.user.domain;

import com.dangori.backend.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import java.time.LocalDateTime;

// CREATE TABLE user_detail_info (
//        seq BIGINT NOT NULL,
//        user_name VARCHAR(255) NOT NULL,
//        user_gender ENUM('M','W') NOT NULL,
//        user_number VARCHAR(13) NOT NULL,
//        user_profile_image VARCHAR(255) NOT NULL DEFAULT '',
//        user_address VARCHAR(255) NOT NULL,
//        user_address_detail VARCHAR(255) NOT NULL,
//        user_post_code VARCHAR(255) NOT NULL,
//        business_info_seq BIGINT NOT NULL DEFAULT 0,
//        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
//        updated_at TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
//        PRIMARY KEY (seq),
//        UNIQUE KEY uk_user_name (user_name),
//        UNIQUE KEY uk_user_number (user_number),
//        INDEX idx_business_info_seq (business_info_seq),
//        INDEX idx_created_at (created_at),
//        INDEX idx_updated_at (updated_at)
// ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

@Entity
@Table(name = "user_detail_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@Getter
@DynamicInsert
@DynamicUpdate
@AllArgsConstructor
public class UserDetailInfo extends BaseEntity{

    @OneToOne
    @JoinColumn(name = "user_seq")
    private UserInfo user;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_gender")
    private String userGender;

    @Column(name = "user_number")
    private String userNumber;

    @Column(name = "user_profile_image")
    private String profileImage;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "user_address_detail")
    private String userAddressDetail;

    @Column(name = "user_post_code")
    private String userPostCode;

    @Column(name = "business_info_seq")
    private Long businessInfoSeq;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    private UserDetailInfo(UserInfo userInfo, String userName, String userGender, String userNumber, String userAddress, String userAddressDetail, String userPostCode) {
        this.user = userInfo;
        this.userName = userName;
        this.userGender = userGender;
        this.userNumber = userNumber;
        this.userAddress = userAddress;
        this.userAddressDetail = userAddressDetail;
        this.userPostCode = userPostCode;
    }

    public static UserDetailInfo of(UserInfo userInfo, String userName, String userGender, String userNumber, String userAddress, String userAddressDetail, String userPostCode) {
        return new UserDetailInfo(userInfo, userName, userGender, userNumber, userAddress, userAddressDetail, userPostCode);
    }

    public void updateBasicInfo(String userName, String userGender) {
        this.userName = userName;
        this.userGender = userGender;
    }

    public void setProfileImage(String url) {
        this.profileImage = url;
    }
}
