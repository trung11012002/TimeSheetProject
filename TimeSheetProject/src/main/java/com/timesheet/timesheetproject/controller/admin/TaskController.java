package com.timesheet.timesheetproject.controller.admin;

import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.BranchResponse;
import com.timesheet.timesheetproject.dto.response.TaskResponse;
import com.timesheet.timesheetproject.service.IBranchService;
import com.timesheet.timesheetproject.service.ITaskService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskController {
    @Autowired
    ITaskService taskService;

    @GetMapping("/project-id/{projectId}")
    ApiResponse<List<TaskResponse>> getTaskByProjectId(@PathVariable Long projectId) {
        return ApiResponse.<List<TaskResponse>>builder()
                .code(1000)
                .result(taskService.getTaskByProjectId(projectId))
                .build();
    }
}
