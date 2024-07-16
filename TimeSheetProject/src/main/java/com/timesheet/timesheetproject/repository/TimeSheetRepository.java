package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSheetRepository extends JpaRepository<TimeSheet,Long> {
}
