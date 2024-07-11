package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepostitory extends JpaRepository<Task,Long> {
}
