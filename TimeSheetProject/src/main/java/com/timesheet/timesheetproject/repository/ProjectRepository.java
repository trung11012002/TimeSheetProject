package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project,Long> {
}
