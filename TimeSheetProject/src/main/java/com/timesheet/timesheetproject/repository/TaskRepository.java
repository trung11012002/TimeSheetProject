package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
