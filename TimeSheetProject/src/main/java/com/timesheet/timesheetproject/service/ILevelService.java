package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.request.user.UserUpdateRequest;
import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.dto.response.UserResponse;

import java.util.List;

public interface ILevelService {
    List<LevelResponse> getAllLevel();
    List<LevelResponse> getByTypeUserId(Long typeUserId);
}
