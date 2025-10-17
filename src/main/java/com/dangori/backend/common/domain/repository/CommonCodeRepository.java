package com.dangori.backend.common.domain.repository;

import com.dangori.backend.common.domain.CommonCode;
import com.dangori.backend.common.enums.YnType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommonCodeRepository extends JpaRepository<CommonCode, String> {
    // 중복이면 2개 나올 듯
    Optional<CommonCode> findByCodeName(String codeName);
    CommonCode findByCodeNameAndDeleteFlag(String codeName, YnType deleteFlag);
    List<CommonCode> findByParentCodeSeqAndDeleteFlag(Long parentCodeSeq, YnType deleteFlag);

}
