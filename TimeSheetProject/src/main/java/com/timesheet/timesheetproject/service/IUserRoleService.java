package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.response.BranchResponse;
import com.timesheet.timesheetproject.dto.response.UserRoleResponse;
import com.timesheet.timesheetproject.entity.UserRole;

import java.util.List;

public interface IUserRoleService {
    UserRoleResponse creation(UserRole userRole);
    List<UserRoleResponse> updateUserRolesByUserId(Long userId, Long[] roleIds);
}
