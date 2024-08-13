package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.response.BranchResponse;
import com.timesheet.timesheetproject.dto.response.UserResponse;

import java.util.List;

public interface IBranchService {
    List<BranchResponse> getAllBranch();
}
