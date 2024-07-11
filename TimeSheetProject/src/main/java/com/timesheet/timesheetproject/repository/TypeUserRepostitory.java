package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeUserRepostitory extends JpaRepository<TypeUser,Long> {
}
