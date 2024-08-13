package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.response.BranchResponse;
import com.timesheet.timesheetproject.dto.response.ProjectResponse;
import com.timesheet.timesheetproject.entity.Project;
import com.timesheet.timesheetproject.mapper.BranchMapper;
import com.timesheet.timesheetproject.mapper.ProjectMapper;
import com.timesheet.timesheetproject.repository.BranchRepository;
import com.timesheet.timesheetproject.repository.ProjectRepository;
import com.timesheet.timesheetproject.service.IBranchService;
import com.timesheet.timesheetproject.service.IProjectService;
import com.timesheet.timesheetproject.util.SecurityUtils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProjectService implements IProjectService {
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ProjectMapper projectMapper;
    @Override
    public List<ProjectResponse> getProjectByUserId(Long userId) {
        long start = System.currentTimeMillis();

        List<Project> result = projectRepository.getProjectByUserId(userId);

        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("Execution time: " + executionTime + "ms");

        return projectMapper.toProjectResponses(result);
    }

    @Override
    public List<ProjectResponse> getProjectByUser() {
        String username = SecurityUtils.getUsername();
        return projectMapper.toProjectResponses(projectRepository.getProjectByUsername(username));
    }

    @Override
    public List<ProjectResponse> getProjectByUserIdJPQL(Long userId) {
        long start = System.currentTimeMillis();

        List<Project> result = projectRepository.getProjectByUserIdJPQL(userId);

        long end = System.currentTimeMillis();
        long executionTime = end - start;
        System.out.println("Execution time: " + executionTime + "ms");

        return projectMapper.toProjectResponses(result);
    }
}
