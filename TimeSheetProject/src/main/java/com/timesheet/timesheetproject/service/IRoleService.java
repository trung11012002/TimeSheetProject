package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.request.role.RoleCreationRequest;
import com.timesheet.timesheetproject.dto.response.RoleResponse;
import com.timesheet.timesheetproject.dto.response.UserRoleResponse;
import com.timesheet.timesheetproject.entity.UserRole;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IRoleService {
    List<RoleResponse> getRoleByUserId(Long userId);
    List<RoleResponse> getAllRole();
    RoleResponse creation(RoleCreationRequest request);
    RoleResponse getRoleById(Long roleId);
    Page<RoleResponse> searchPage(String name, Integer pageNo);

}
