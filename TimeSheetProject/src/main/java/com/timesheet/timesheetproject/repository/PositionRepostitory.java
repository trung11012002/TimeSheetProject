package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepostitory extends JpaRepository<Position,Long> {
}
