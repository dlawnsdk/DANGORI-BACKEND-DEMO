package com.dangori.backend.user.service;

import com.dangori.backend.common.domain.CommonCode;
import com.dangori.backend.user.domain.UserDetailInfo;
import com.dangori.backend.user.domain.UserInfo;
import com.dangori.backend.user.domain.repository.UserDetailInfoRepository;
import com.dangori.backend.user.domain.repository.UserInfoRepository;
import com.dangori.backend.user.dto.UserDetailRequest;
import com.dangori.backend.user.dto.UserInfoResponse;
import com.dangori.backend.user.exception.UserException;
import com.dangori.backend.user.exception.UserExceptionType;
import com.dangori.backend.common.domain.repository.CommonCodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserInfoRepository userInfoRepository;
    private final UserDetailInfoRepository userDetailInfoRepository;
    private final CommonCodeRepository commonCodeRepository;

    public UserInfoResponse getUserInfo(Long memberId) {
        UserInfo userInfo = userInfoRepository.findById(memberId)
                .orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_MEMBER));

        return UserInfoResponse.of(userInfo);
    }

    @Transactional
    public void updateUserDetail(Long memberId, UserDetailRequest request) {
        UserInfo user = userInfoRepository.findById(memberId)
            .orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_MEMBER));

        CommonCode genderCode = commonCodeRepository.findByCodeName(request.getUserGender())
                .orElseThrow();

        CommonCode nationCode = commonCodeRepository.findByCodeName(request.getUserNation())
                .orElseThrow();

        UserDetailInfo detail = userDetailInfoRepository.findByUser(user)
                .orElseThrow(() -> new UserException(UserExceptionType.NOT_FOUND_USER_DETAIL));

        detail.updateBasicInfo(
                String.valueOf(genderCode),
                String.valueOf(nationCode)
        );
    }
}
