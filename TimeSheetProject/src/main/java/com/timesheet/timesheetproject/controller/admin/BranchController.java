package com.timesheet.timesheetproject.controller.admin;

import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.BranchResponse;
import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.service.IBranchService;
import com.timesheet.timesheetproject.service.ILevelService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/branch")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchController {
    @Autowired
    IBranchService branchService;

    @GetMapping("")
    ApiResponse<List<BranchResponse>> getAll() {
        return ApiResponse.<List<BranchResponse>>builder()
                .code(1000)
                .result(branchService.getAllBranch())
                .build();
    }
}
