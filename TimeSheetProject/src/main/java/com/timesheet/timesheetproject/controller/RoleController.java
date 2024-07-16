package com.timesheet.timesheetproject.controller;

import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.dto.response.RoleResponse;
import com.timesheet.timesheetproject.service.ILevelService;
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
@RequestMapping("/role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleController {
    @Autowired
    IRoleService roleService;
    @GetMapping("")
    ApiResponse<List<RoleResponse>> getAll() {
        return ApiResponse.<List<RoleResponse>>builder()
                .code(1000)
                .result(roleService.getAllRole())
                .build();
    }

    @GetMapping("/user-id/{userId}")
    ApiResponse<List<RoleResponse>> getRoleByUserId(@PathVariable("userId")Long userId) {
        return ApiResponse.<List<RoleResponse>>builder()
                .code(1000)
                .result(roleService.getRoleByUserId(userId))
                .build();
    }
}
