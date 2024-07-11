package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.TimeSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSheetRepostitory extends JpaRepository<TimeSheet,Long> {
}
