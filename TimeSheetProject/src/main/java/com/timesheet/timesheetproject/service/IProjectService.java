package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.request.role.RoleCreationRequest;
import com.timesheet.timesheetproject.dto.response.ProjectResponse;
import com.timesheet.timesheetproject.dto.response.RoleResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IProjectService {
    List<ProjectResponse> getProjectByUserId(Long userId);
    List<ProjectResponse> getProjectByUser();
    List<ProjectResponse> getProjectByUserIdJPQL(Long userId);
}
