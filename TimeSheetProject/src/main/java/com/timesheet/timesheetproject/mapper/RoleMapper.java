package com.timesheet.timesheetproject.mapper;


import com.timesheet.timesheetproject.dto.request.role.RoleCreationRequest;
import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.request.user.UserUpdateRequest;
import com.timesheet.timesheetproject.dto.response.RoleResponse;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import com.timesheet.timesheetproject.entity.Role;
import com.timesheet.timesheetproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleCreationRequest roleCreationRequest);
    RoleResponse toRoleResponse(Role role);
}
