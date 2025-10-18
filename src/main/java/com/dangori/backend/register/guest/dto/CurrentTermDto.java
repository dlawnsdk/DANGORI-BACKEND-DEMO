package com.dangori.backend.register.guest.dto;

import com.dangori.backend.register.guest.domain.TermVersion;

import java.time.LocalDateTime;

public record CurrentTermDto(
        Long termsSeq,
        int codeSeq,
        String displayName,
        Long versionSeq,
        String version,
        boolean required,
        String locale,
        LocalDateTime effectiveAt,
        String contentUrl,
        String checksum
) {
    // === Entity -> DTO 변환 ===
    public static CurrentTermDto fromEntity(TermVersion tv) {
        if (tv == null || tv.getTerm() == null) {
            throw new IllegalArgumentException("Invalid TermVersion: null term");
        }
        return new CurrentTermDto(
                tv.getTerm().getSeq(),
                tv.getTerm().getCodeSeq(),
                tv.getTerm().getDisplayName(),
                tv.getSeq(),
                tv.getVersion(),
                tv.isRequired(),
                tv.getLocale(),
                tv.getEffectiveAt(),
                tv.getContent(),
                tv.getChecksum()
        );
    }
}
