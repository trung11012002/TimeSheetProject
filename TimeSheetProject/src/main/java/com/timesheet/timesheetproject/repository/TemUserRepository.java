package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.TeamUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemUserRepository extends JpaRepository<TeamUser,Long> {
}
