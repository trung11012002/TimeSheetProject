package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectTaskRepository extends JpaRepository<ProjectTask,Long> {
}
