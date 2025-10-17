package com.dangori.backend.user.domain.repository;


import com.dangori.backend.user.domain.UserDetailInfo;
import com.dangori.backend.user.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailInfoRepository extends JpaRepository<UserDetailInfo, Long> {
    Optional<UserDetailInfo> findByUser(UserInfo user);
}
