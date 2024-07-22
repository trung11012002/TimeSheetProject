package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.dto.response.PermissionResponse;

import java.util.List;

public interface IPermissionService {
    List<PermissionResponse> getAllPermission();
    List<PermissionResponse> getPermissionByRoleId(Long roleId);

}
