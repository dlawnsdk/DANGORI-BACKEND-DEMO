package com.dangori.backend.register.guest.service;

import com.dangori.backend.common.dto.ResultResponse;
import com.dangori.backend.common.enums.ResponseMessage;
import com.dangori.backend.register.exception.RegisterException;
import com.dangori.backend.register.exception.RegisterExceptionType;
import com.dangori.backend.register.guest.domain.TermVersion;
import com.dangori.backend.register.guest.domain.UserTermAcceptance;
import com.dangori.backend.register.guest.domain.repository.TermVersionRepository;
import com.dangori.backend.register.guest.domain.repository.UserTermAcceptanceRepository;
import com.dangori.backend.register.guest.dto.CurrentTermResponse;
import com.dangori.backend.register.guest.dto.GuestRegisterRequest;
import com.dangori.backend.user.domain.UserDetailInfo;
import com.dangori.backend.user.domain.UserInfo;
import com.dangori.backend.user.domain.repository.UserDetailInfoRepository;
import com.dangori.backend.user.domain.repository.UserInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserInfoRepository userInfoRepository;
    private final UserDetailInfoRepository userDetailInfoRepository;
    private final UserTermAcceptanceRepository userTermAcceptanceRepository;
    private final TermVersionRepository termVersionRepository;
    private final PasswordEncoder passwordEncoder;

    private final HttpServletRequest request;


    /**
     * locale에 맞는 "현재 시행 중인 활성 버전"만 반환
     */
    public List<CurrentTermResponse> getCurrentTerms() {
        final LocalDateTime now = LocalDateTime.now();
        List<TermVersion> list = termVersionRepository.findLatestActive(now);

        return list.stream()
                .map(CurrentTermResponse::fromEntity)
                .toList();
    }

    /**
     * 이메일 중복체크
     * true: 사용 불가능한 이메일
     * false: 사용 가능한 이메일
     */
    public boolean checkEmail(String userEmail) {
        Optional<UserInfo> userInfo = userInfoRepository.findByUserEmail(userEmail);
        return userInfo.isPresent();
    }

    @Transactional
    public boolean registerAccount(GuestRegisterRequest guestRegisterRequest, MultipartFile profileImage) {

        // 회원 검증
        if (checkEmail(guestRegisterRequest.userEmail())) {
            return false;
        }

        // 회원 정보 저장
        UserInfo userInfo = saveUserInfo(guestRegisterRequest.userEmail(), guestRegisterRequest.userPassword(), 7L);

        // 회원 상세정보 저장
        UserDetailInfo userDetailInfo = saveUserDetailInfo(
                userInfo,
                guestRegisterRequest.userName(),
                guestRegisterRequest.userGender(),
                guestRegisterRequest.userNumber(),
                guestRegisterRequest.userAddress(),
                guestRegisterRequest.userAddressDetail(),
                guestRegisterRequest.userPostCode()
        );

        // 약관 동의 항목 저장
        saveTermAgreement(userInfo, guestRegisterRequest.terms());

        // 프로필 이미지 있으면 저장
        if(profileImage != null) {
//            saveProfileImage(profileImage); // storage 추가
            userDetailInfo.setProfileImage(profileImage.getOriginalFilename());
        }

        return true;
    }

    private UserInfo saveUserInfo(String userEmail, String userPassword, Long registerCode) {

        String encodedPassword = passwordEncoder.encode(userPassword);

        return userInfoRepository.save(UserInfo.of(userEmail, encodedPassword, registerCode));
    }

    private UserDetailInfo saveUserDetailInfo(UserInfo userInfo, String userName, String userGender, String userNumber, String userAddress, String userAddressDetail, String userPostCode) {

        return userDetailInfoRepository.save(UserDetailInfo.of(userInfo, userName, userGender, userNumber, userAddress, userAddressDetail, userPostCode));
    }

    public void saveTermAgreement(UserInfo userInfo, List<GuestRegisterRequest.TermAgreement> terms) {

        String ip = Optional.ofNullable(request.getHeader("X-Forwarded-For"))
                .orElse(request.getRemoteAddr());
        String userAgent = request.getHeader("User-Agent");

        for (GuestRegisterRequest.TermAgreement term : terms) {
            if (term.accepted()) {
                UserTermAcceptance entity = UserTermAcceptance.builder()
                        .user(userInfo)
                        .termVersionSeq(term.versionSeq())
                        .ip(ip)
                        .userAgent(userAgent)
                        .agreedAt(LocalDateTime.now())
                        .build();
                userTermAcceptanceRepository.save(entity);
            }
        }
    }

    private boolean saveProfileImage(MultipartFile profileImage) {

        return true;
    }
}
