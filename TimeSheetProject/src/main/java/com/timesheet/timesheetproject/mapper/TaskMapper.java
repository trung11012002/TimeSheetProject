package com.timesheet.timesheetproject.mapper;


import com.timesheet.timesheetproject.dto.request.task.TaskCreationRequest;
import com.timesheet.timesheetproject.dto.response.BranchResponse;
import com.timesheet.timesheetproject.dto.response.TaskResponse;
import com.timesheet.timesheetproject.entity.Branch;
import com.timesheet.timesheetproject.entity.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    Task toTask(TaskCreationRequest taskCreationRequest);
    List<Task> toTasks(List<TaskCreationRequest> taskCreationRequests);
    TaskResponse toTaskresponse(Task task);
    List<TaskResponse> toTaskresponses(List<Task> tasks);
}
