package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Repository
public interface InvalidatedTokenRepository extends JpaRepository<InvalidatedToken, String> {
    @Transactional
    @Modifying
    @Query("DELETE FROM InvalidatedToken i WHERE i.date BETWEEN :startDate AND :endDate")
    int deleteInvalidatedTokensBetweenDates(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}