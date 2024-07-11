package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Branch;
import com.timesheet.timesheetproject.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepostitory extends JpaRepository<Branch,Long> {
}
