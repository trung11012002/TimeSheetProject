package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch,Long> {
}
