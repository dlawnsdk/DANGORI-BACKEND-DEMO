package com.dangori.backend.register.guest.dto;

import java.util.List;

/**
 * Flutter에서 전달되는 회원가입 요청 payload JSON을 받기 위한 DTO.
 *
 * {
 *   "userEmail": "...",
 *   "userPassword": "...",
 *   "userNumber": "...",
 *   "userName": "...",
 *   "gender": "...",
 *   "address": "...",
 *   "addressDetail": "...",
 *   "postCode": "...",
 *   "terms": [
 *     { "termSeq": 1, "accepted": true, "version": "v1" },
 *     { "termSeq": 2, "accepted": false, "version": "v1" }
 *   ]
 * }
 */
public record GuestRegisterRequest(
        String userEmail,
        String userPassword,
        String userNumber,
        String userName,
        String userGender,
        String userAddress,
        String userAddressDetail,
        String userPostCode,
        List<TermAgreement> terms
) {
    /**
     * 약관 동의 항목
     */
    public record TermAgreement(
            Long termSeq,
            Long versionSeq,
            boolean accepted,
            String version
    ) {}
}
