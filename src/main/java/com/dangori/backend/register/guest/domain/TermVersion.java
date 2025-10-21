package com.dangori.backend.register.guest.domain;

import com.dangori.backend.common.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "term_version")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class TermVersion extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "term_seq")
    private Term term;

    @Column(name = "version")
    private String version;

    @Column(name = "is_required")
    private boolean required;

    @Column(name = "locale")
    private String locale;

    @Column(name = "effective_at")
    private LocalDateTime effectiveAt;

    @Column(name = "content")
    private String content;

    @Column(name = "checksum")
    private String checksum;

    @Column(name = "is_active")
    private boolean active;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "retired_at")
    private LocalDateTime retiredAt;
}
