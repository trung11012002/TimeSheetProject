package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepostitory extends JpaRepository<Team,Long> {
}
