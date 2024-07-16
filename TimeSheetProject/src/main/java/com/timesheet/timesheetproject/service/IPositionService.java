package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.dto.response.PositionResponse;

import java.util.List;

public interface IPositionService {
    List<PositionResponse> getAllPosition();
}
