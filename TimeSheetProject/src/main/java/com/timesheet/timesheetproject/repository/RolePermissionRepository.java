package com.timesheet.timesheetproject.repository;

import com.timesheet.timesheetproject.entity.Branch;
import com.timesheet.timesheetproject.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolePermissionRepository extends JpaRepository<RolePermission,Long> {
}
