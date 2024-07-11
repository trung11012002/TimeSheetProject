package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepostitory extends JpaRepository<Project,Long> {
}
