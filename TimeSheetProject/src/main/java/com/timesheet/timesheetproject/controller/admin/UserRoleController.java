package com.timesheet.timesheetproject.controller.admin;

import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.RoleResponse;
import com.timesheet.timesheetproject.dto.response.UserRoleResponse;
import com.timesheet.timesheetproject.service.IRoleService;
import com.timesheet.timesheetproject.service.IUserRoleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user-role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRoleController {
    @Autowired
    IUserRoleService userRoleService;

    @PutMapping("/{userId}/roles")
    ApiResponse<List<UserRoleResponse>> updateUserRolesByUserId(@PathVariable("userId") Long userId,@RequestBody() Long[] roleIds) {
        return ApiResponse.<List<UserRoleResponse>>builder()
                .code(1000)
                .result(userRoleService.updateUserRolesByUserId(userId,roleIds))
                .build();
    }
}
