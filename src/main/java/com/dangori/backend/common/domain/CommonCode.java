package com.dangori.backend.common.domain;

import com.dangori.backend.common.enums.YnType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "common_code")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class CommonCode extends BaseEntity{

    @Column(name = "code_name")
    private String codeName;

    @Column(name = "parent_code_seq")
    private Long parentCodeSeq;

    @Column(name = "code_order")
    private Integer codeOrder;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "delete_flag", nullable = false, columnDefinition = "enum('Y','N')")
    private YnType deleteFlag;

    @Column(name = "updated_at", insertable = false, updatable = false)
    private LocalDateTime updatedAt;
}

