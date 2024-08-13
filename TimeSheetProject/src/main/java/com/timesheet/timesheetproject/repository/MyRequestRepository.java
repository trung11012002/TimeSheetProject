package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.dto.response.MyRequestResponse;
import com.timesheet.timesheetproject.entity.MyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MyRequestRepository extends JpaRepository<MyRequest, Long> {

    @Query("SELECT mr FROM MyRequest mr WHERE " +
            "mr.id = :myRequestId " +
            "AND mr.user.username = :username")
    Optional<MyRequest> findByIdAndUserName(@Param("myRequestId") Long myRequestId, @Param("username") String username);
    @Query("SELECT mr FROM MyRequest mr WHERE " +
            "(mr.type = 'Full day' OR mr.type = 'Morning' OR mr.type = 'Afternoon') " +
            "AND mr.date = :date " +
            "AND mr.user.username = :username")
    MyRequest findByTypeRequestLeave(@Param("date") LocalDate date, @Param("username") String username);

    @Query("SELECT mr FROM MyRequest mr WHERE " +
            "mr.type = 'Full day' " +
            "AND mr.date = :date " +
            "AND mr.user.username = :username")
    MyRequest findByTypeRequestLeaveFullDay(@Param("date") LocalDate date, @Param("username") String username);

    @Query("SELECT COUNT(mr) FROM MyRequest mr WHERE " +
            "(mr.type = 'Early' OR mr.type = 'Late') " +
            "AND mr.date BETWEEN :startDate AND :endDate " +
            "AND mr.user.username = :username")
    Integer sumTypeRequestLateOrRequestEarlyOfWeek(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("username") String username);

    @Query("SELECT COUNT(mr) FROM MyRequest mr WHERE " +
            "(mr.type = 'Early' OR mr.type = 'Late') " +
            "AND mr.date BETWEEN :startDate AND :endDate " +
            "AND mr.user.username = :username")
    Integer sumTypeRequestLateOrRequestEarlyOfMonth(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate, @Param("username") String username);

    @Query("SELECT mr FROM MyRequest mr WHERE " +
            "(mr.type = 'Late') " +
            "AND mr.date = :date " +
            "AND mr.user.username = :username")
    MyRequest findMyRequestLateByDateAndUsername(@Param("date") LocalDate date, @Param("username") String username);

    @Query("SELECT mr FROM MyRequest mr WHERE " +
            "(mr.type = 'Early') " +
            "AND mr.date = :date " +
            "AND mr.user.username = :username")
    MyRequest findMyRequestEarlyByDateAndUsername(@Param("date") LocalDate date, @Param("username") String username);

    @Query("SELECT COUNT(mr) FROM MyRequest mr WHERE " +
            "(mr.type = 'Early' OR mr.type = 'Late') " +
            "AND mr.date = :date " +
            "AND mr.user.username = :username")
    Integer findMyRequestEarlyOrLateByDateAndUsername(@Param("date") LocalDate date, @Param("username") String username);

    @Query("SELECT SUM(mr.hour) FROM MyRequest mr WHERE " +
            "(mr.type = 'Early' OR mr.type = 'Late') " +
            "AND mr.date = :date " +
            "AND mr.user.username = :username")
    Double sumHourRequestOneDay(@Param("date") LocalDate date, @Param("username") String username);

    @Query("SELECT new com.timesheet.timesheetproject.dto.response.MyRequestResponse(mr.date ,mr.type,mr.hour,mr.status) " +
            "FROM MyRequest mr WHERE " +
            "mr.date BETWEEN :startDate AND :endDate " +
            "AND mr.user.username = :username")
    List<MyRequestResponse> findMyRequestMonthOfUser(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("username") String username);
}
