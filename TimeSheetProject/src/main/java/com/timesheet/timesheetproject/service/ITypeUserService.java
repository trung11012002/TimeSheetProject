package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.dto.response.TypeUserResponse;

import java.util.List;

public interface ITypeUserService {
    List<TypeUserResponse> getAllTypeUser();
//    List<TypeUserResponse> getByTypeUserId(Long typeUserId);
}
