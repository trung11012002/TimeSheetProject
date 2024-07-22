package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {

    @Query("SELECT p FROM Project p " +
            "JOIN p.team t " +
            "JOIN t.teamUsers tu " +
            "JOIN tu.user u " +
            "WHERE u.username = :username")
    List<Project> getProjectByUsername(@Param("username") String username);
    @Query(value = "SELECT p.*\n" +
            "FROM project p\n" +
            "JOIN team t ON p.team_id = t.id\n" +
            "JOIN team_user tu ON t.id = tu.team_id\n" +
            "JOIN user u ON tu.user_id = u.id\n" +
            "WHERE u.id = :userId", nativeQuery = true)
    List<Project> getProjectByUserId(@Param("userId") Long userId);

    @Query("SELECT p FROM Project p " +
            "JOIN p.team t " +
            "JOIN t.teamUsers tu " +
            "JOIN tu.user u " +
            "WHERE u.id = :userId")
    List<Project> getProjectByUserIdJPQL(@Param("userId") Long userId);

    @Query("SELECT p FROM Project p " +
            "JOIN p.team t " +
            "JOIN t.teamUsers tu " +
            "JOIN tu.user u " +
            "WHERE u.username = :username AND p.id = :projectId")
    Project checkUserInTheProject(@Param("username") String username,@Param("projectId") Long projectId);
}
