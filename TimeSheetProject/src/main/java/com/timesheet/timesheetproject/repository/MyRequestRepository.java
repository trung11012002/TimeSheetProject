package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.MyRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyRequestRepository extends JpaRepository<MyRequest,Long> {
}
