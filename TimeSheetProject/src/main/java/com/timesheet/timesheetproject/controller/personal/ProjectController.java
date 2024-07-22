package com.timesheet.timesheetproject.controller.personal;

import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.dto.response.PositionResponse;
import com.timesheet.timesheetproject.dto.response.ProjectResponse;
import com.timesheet.timesheetproject.service.ILevelService;
import com.timesheet.timesheetproject.service.IProjectService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/project")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectController {
    @Autowired
    IProjectService projectService;

    @GetMapping("/user")
    ApiResponse<List<ProjectResponse>> getProjectByUser(){
        return ApiResponse.<List<ProjectResponse>>builder()
                .code(1000)
                .result(projectService.getProjectByUser())
                .build();
    }

    @GetMapping("/user-id/{userId}")
    ApiResponse<List<ProjectResponse>> getProjectByUserId(@PathVariable Long userId){
        return ApiResponse.<List<ProjectResponse>>builder()
                .code(1000)
                .result(projectService.getProjectByUserId(userId))
                .build();
    }

    @GetMapping("/user-id/jpql/{userId}")
    ApiResponse<List<ProjectResponse>> getProjectByUserIdJPQL(@PathVariable Long userId){
        return ApiResponse.<List<ProjectResponse>>builder()
                .code(1000)
                .result(projectService.getProjectByUserIdJPQL(userId))
                .build();
    }
}
