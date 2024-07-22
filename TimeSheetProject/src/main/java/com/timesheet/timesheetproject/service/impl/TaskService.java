package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.response.PermissionResponse;
import com.timesheet.timesheetproject.dto.response.TaskResponse;
import com.timesheet.timesheetproject.mapper.PermissionMapper;
import com.timesheet.timesheetproject.mapper.TaskMapper;
import com.timesheet.timesheetproject.repository.PermissionRepository;
import com.timesheet.timesheetproject.repository.TaskRepository;
import com.timesheet.timesheetproject.service.IPermissionService;
import com.timesheet.timesheetproject.service.ITaskService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TaskService implements ITaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    TaskMapper taskMapper;


    @Override
    public List<TaskResponse> getTaskByProjectId(Long projectId) {
        return taskMapper.toTaskresponses(taskRepository.findTasksByProjectId(projectId));
    }
}
