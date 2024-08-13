package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.response.PositionResponse;
import com.timesheet.timesheetproject.mapper.PositionMapper;
import com.timesheet.timesheetproject.repository.PositionRepository;
import com.timesheet.timesheetproject.service.IPositionService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionService implements IPositionService {
    @Autowired
    PositionMapper positionMapper;
    @Autowired
    PositionRepository positionRepostitory;
    @Override
    public List<PositionResponse> getAllPosition() {
        return positionMapper.toPositionResponses(positionRepostitory.findAll());
    }
}
