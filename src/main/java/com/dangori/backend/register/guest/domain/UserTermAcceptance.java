package com.dangori.backend.register.guest.domain;

import com.dangori.backend.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_term_acceptance")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class UserTermAcceptance extends BaseEntity {

    @Column(name = "user_seq")
    private Long userSeq;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "term_version_seq")
    private TermVersion termVersion;

    @Column(name = "agreed_at")
    private LocalDateTime agreedAt;

    @Column(name = "ip")
    private String ip;

    @Column(name = "user_agent")
    private String userAgent;

    @Column(name = "source")
    private String source;

    @Column(name = "withdrawn_at")
    private LocalDateTime withdrawnAt;

    @PrePersist
    void onCreate() {
        if (agreedAt == null) agreedAt = LocalDateTime.now();
    }
}
