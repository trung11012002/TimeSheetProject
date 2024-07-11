package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemUserRepostitory extends JpaRepository<TeamUser,Long> {
}
