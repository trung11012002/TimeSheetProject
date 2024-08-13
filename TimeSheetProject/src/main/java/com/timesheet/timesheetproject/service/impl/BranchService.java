package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.response.BranchResponse;
import com.timesheet.timesheetproject.mapper.BranchMapper;
import com.timesheet.timesheetproject.repository.BranchRepository;
import com.timesheet.timesheetproject.service.IBranchService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BranchService implements IBranchService {
    @Autowired
    BranchMapper branchMapper;
    @Autowired
    BranchRepository branchRepostitory;
    @Override
    public List<BranchResponse> getAllBranch() {
        return branchMapper.toBranchResponses(branchRepostitory.findAll());
    }
}
