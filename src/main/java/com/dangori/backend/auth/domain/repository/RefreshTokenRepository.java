package com.dangori.backend.auth.domain.repository;

import com.dangori.backend.auth.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByUserSeq(Long userId);
    Optional<RefreshToken> findByToken(String token);
    void deleteByUserSeq(Long userId);
}
