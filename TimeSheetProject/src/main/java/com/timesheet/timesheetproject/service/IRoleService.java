package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.response.RoleResponse;
import com.timesheet.timesheetproject.dto.response.UserRoleResponse;
import com.timesheet.timesheetproject.entity.UserRole;

import java.util.List;

public interface IRoleService {
    List<RoleResponse> getRoleByUserId(Long userId);
    List<RoleResponse> getAllRole();
}
