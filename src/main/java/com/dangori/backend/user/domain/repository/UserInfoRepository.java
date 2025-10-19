package com.dangori.backend.user.domain.repository;

import com.dangori.backend.user.domain.UserInfo;
import com.dangori.backend.common.enums.YnType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserEmail(String email);
}
