package com.timesheet.timesheetproject.mapper;


import com.timesheet.timesheetproject.dto.response.BranchResponse;
import com.timesheet.timesheetproject.dto.response.UserRoleResponse;
import com.timesheet.timesheetproject.entity.Branch;
import com.timesheet.timesheetproject.entity.UserRole;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {
    UserRoleResponse toUserRoleResponse(UserRole userRole);
    List<UserRoleResponse>toUserRoleResponses(List<UserRole>userRoles);
}
