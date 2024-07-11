package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.StatusMyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusMyRequestRepostitory extends JpaRepository<StatusMyRequest,Long> {
}
