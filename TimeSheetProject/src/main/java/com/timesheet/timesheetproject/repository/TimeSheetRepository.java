package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.TimeSheet;
import com.timesheet.timesheetproject.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {
    @Query("SELECT ts FROM TimeSheet ts WHERE ts.date = :date AND ts.user.username = :username")
    List<TimeSheet> getTimeSheetByDateAndUsername(@Param("date") LocalDate date, @Param("username") String username);

    @Query("SELECT ts FROM TimeSheet ts WHERE (ts.date BETWEEN :startDate AND :endDate) AND ts.user.username = :username")
    List<TimeSheet> getTimeSheetByStartDateAndEnDateAndUsername(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("username") String username);

    @Query("SELECT ts FROM TimeSheet ts WHERE ts.status = 'NEW' AND (ts.date BETWEEN :startDate AND :endDate)  AND ts.user.username = :username")
    List<TimeSheet> getTimeSheetWeekStatusNewByUsername(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("username") String username);

    @Query("SELECT ts FROM TimeSheet ts WHERE (ts.date BETWEEN :startDate AND :endDate)")
    List<TimeSheet> getTimeSheetByStartDateAndEndDate(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query("SELECT t FROM TimeSheet t WHERE " +
            "(:username IS NULL OR LOWER(t.user.username) LIKE LOWER(CONCAT('%', :username, '%'))) AND " +
            "(:projectId IS NULL OR t.project.id = :prjectId) AND " +
            "(:brandId IS NULL OR t.user.branch.id = :branchId)")
    Page<TimeSheet> searchOfManagement(
            @Param("username") String username,
            @Param("projectId") Long projectId,
            @Param("branchId") Long branchId,
            Pageable pageable);
}
