package com.dangori.backend.register.guest.service;

import com.dangori.backend.register.guest.domain.TermVersion;
import com.dangori.backend.register.guest.domain.repository.TermVersionRepository;
import com.dangori.backend.register.guest.dto.CurrentTermDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RegisterService {

    private final TermVersionRepository termVersionRepository;

    /**
     * locale에 맞는 "현재 시행 중인 활성 버전"만 반환
     */
    public List<CurrentTermDto> getCurrentTerms() {
        final LocalDateTime now = LocalDateTime.now();
        List<TermVersion> list = termVersionRepository.findLatestActive(now);

        return list.stream()
                .map(CurrentTermDto::fromEntity)
                .toList();
    }
}
