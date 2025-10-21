package com.dangori.backend.register.guest.domain;

import com.dangori.backend.common.domain.BaseEntity;
import com.dangori.backend.user.domain.UserInfo;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_term_acceptance")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class UserTermAcceptance extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private UserInfo user;

    @Column(name = "term_version_seq")
    private Long termVersionSeq;

    @Column(name = "agreed_at")
    private LocalDateTime agreedAt;

    @Column(name = "ip")
    private String ip;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "withdrawn_at")
    private LocalDateTime withdrawnAt;
}
