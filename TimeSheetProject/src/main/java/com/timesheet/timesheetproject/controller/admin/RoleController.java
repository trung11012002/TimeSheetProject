package com.timesheet.timesheetproject.controller.admin;

import com.timesheet.timesheetproject.dto.request.role.RoleCreationRequest;
import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.dto.response.RoleResponse;
import com.timesheet.timesheetproject.enums.Role;
import com.timesheet.timesheetproject.service.ILevelService;
import com.timesheet.timesheetproject.service.IRoleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("")
    ApiResponse<RoleResponse> addRole(@RequestBody RoleCreationRequest request) {
        return ApiResponse.<RoleResponse>builder()
                .code(1000)
                .result(roleService.creation(request))
                .build();
    }
    @GetMapping("/{roleId}")
    ApiResponse<RoleResponse> getRoleById(@PathVariable("roleId")Long roleId) {
        return ApiResponse.<RoleResponse>builder()
                .code(1000)
                .result(roleService.getRoleById(roleId))
                .build();
    }

    @GetMapping("/search-page")
    ApiResponse<Page<RoleResponse>> searchPage(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {

        return ApiResponse.<Page<RoleResponse>>builder()
                .code(1000)
                .result(roleService.searchPage(name,pageNo))
                .build();
    }

}
