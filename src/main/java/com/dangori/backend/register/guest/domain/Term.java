package com.dangori.backend.register.guest.domain;

import com.dangori.backend.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

@Entity
@Table(name = "term")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Term extends BaseEntity {

    @Comment("'TOS', 'PRIVACY', 'LOCATION', 'MARKETING'")
    @Column(name = "code_seq")
    private Long codeSeq;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
