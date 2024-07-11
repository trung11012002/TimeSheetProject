package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepostitory extends JpaRepository<Permission,Long> {
}
