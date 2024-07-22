package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.response.BranchResponse;
import com.timesheet.timesheetproject.dto.response.TaskResponse;

import java.util.List;

public interface ITaskService {
    List<TaskResponse> getTaskByProjectId(Long projectId);
}
