package com.dangori.backend.register.guest.domain.repository;

import com.dangori.backend.register.guest.domain.TermVersion;
import com.dangori.backend.register.guest.domain.UserTermAcceptance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface UserTermAcceptanceRepository extends JpaRepository<UserTermAcceptance, Long> {


}
