package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Project;
import com.timesheet.timesheetproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t " +
            "JOIN t.projectTasks pt " +
            "WHERE pt.project.id = :projectId")
    List<Task> findTasksByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT t FROM Task t " +
            "JOIN t.projectTasks pt " +
            "WHERE pt.project.id = :projectId AND t.id = :taskId")
    Task checkTaskInTheProject(@Param("projectId") Long projectId,@Param("taskId") Long taskId);

}
