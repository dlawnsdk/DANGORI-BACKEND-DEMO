package com.dangori.backend.register.guest.service;

import com.dangori.backend.register.guest.domain.TermVersion;
import com.dangori.backend.register.guest.domain.repository.TermVersionRepository;
import com.dangori.backend.register.guest.dto.CurrentTermResponse;
import com.dangori.backend.user.domain.UserInfo;
import com.dangori.backend.user.domain.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegisterService {

    private final UserInfoRepository userInfoRepository;
    private final TermVersionRepository termVersionRepository;

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
}
