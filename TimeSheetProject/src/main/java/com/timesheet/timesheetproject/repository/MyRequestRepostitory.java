package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.MyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRequestRepostitory extends JpaRepository<MyRequest,Long> {
}
