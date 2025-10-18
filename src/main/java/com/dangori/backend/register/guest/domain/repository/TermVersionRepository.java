package com.dangori.backend.register.guest.domain.repository;

import com.dangori.backend.register.guest.domain.TermVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface TermVersionRepository extends JpaRepository<TermVersion, Long> {

    /**
     * - active = true
     * - effectiveAt <= :now
     * - 동일 Term 내에서 effectiveAt 최대값
     */
    @Query("""
            select tv
            from TermVersion tv
            join fetch tv.term t
            where tv.active = true
              and tv.effectiveAt <= :now
              and tv.effectiveAt = (
                select max(tv2.effectiveAt)
                from TermVersion tv2
                where tv2.term = tv.term
                  and tv2.active = true
                  and tv2.effectiveAt <= :now
              )
            order by t.codeSeq asc
            """)
    List<TermVersion> findLatestActive(@Param("now") LocalDateTime now);

}
