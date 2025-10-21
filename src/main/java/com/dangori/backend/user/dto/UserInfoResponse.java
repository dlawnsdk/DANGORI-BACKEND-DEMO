package com.dangori.backend.user.dto;

import com.dangori.backend.user.domain.UserInfo;
import com.dangori.backend.common.util.JsonUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserInfoResponse {
    // 필요한거 여기서 추가..
    private Integer age;
    private String gender;
    private String nation;

    private String email;
    private List<String> preferCategorySeqJson;
    private String nickname;
    private String profileImagePath;

    // 가입할 때 테이블 세 군데에 insert 해 주니까 null 체크 안함
    // JSON으로 받아온 데이터를 Map으로 파싱한 후 반환해 줘야 함
    public static UserInfoResponse of(UserInfo userInfo) {

        return UserInfoResponse.builder()
                .email(userInfo.getUserEmail())
                .gender(userInfo.getDetail().getUserGender())
                .nickname(userInfo.getDetail().getUserName())
                .profileImagePath(userInfo.getDetail().getProfileImage())
                .build();
    }
}
