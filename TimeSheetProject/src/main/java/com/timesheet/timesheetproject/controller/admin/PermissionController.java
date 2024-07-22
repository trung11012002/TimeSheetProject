package com.timesheet.timesheetproject.controller.admin;

import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.PermissionResponse;
import com.timesheet.timesheetproject.dto.response.RoleResponse;
import com.timesheet.timesheetproject.service.IPermissionService;
import com.timesheet.timesheetproject.service.IRoleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/permission")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionController {
    @Autowired
    IPermissionService permissionService;
    @GetMapping("")
    ApiResponse<List<PermissionResponse>> getAll() {
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(1000)
                .result(permissionService.getAllPermission())
                .build();
    }
    @GetMapping("/role-id/{roleId}")
    ApiResponse<List<PermissionResponse>> getPermissionByRoleId(@PathVariable("roleId") Long roleId) {
        return ApiResponse.<List<PermissionResponse>>builder()
                .code(1000)
                .result(permissionService.getPermissionByRoleId(roleId))
                .build();
    }

}
