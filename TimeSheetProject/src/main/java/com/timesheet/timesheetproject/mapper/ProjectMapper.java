package com.timesheet.timesheetproject.mapper;

import com.timesheet.timesheetproject.dto.request.level.LevelCreationRequest;
import com.timesheet.timesheetproject.dto.request.project.ProjectCreationRequest;
import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.dto.response.ProjectResponse;
import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.Project;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    Project toProject(ProjectCreationRequest projectCreationRequest);
    ProjectResponse toProjectResponse(Project project);

    List<Project> toProject(List<ProjectCreationRequest> projectCreationRequests);
    List<ProjectResponse> toProjectResponses(List<Project> projects);
}
