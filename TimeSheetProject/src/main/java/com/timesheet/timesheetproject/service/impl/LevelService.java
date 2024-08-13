package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.mapper.LevelMapper;
import com.timesheet.timesheetproject.repository.LevelRepository;
import com.timesheet.timesheetproject.service.ILevelService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LevelService implements ILevelService {
    @Autowired
    LevelMapper levelMapper;
    @Autowired
    LevelRepository levelRepostitory;
    @Override
    public List<LevelResponse> getAllLevel() {
        return levelMapper.toLevelResponses(levelRepostitory.findAll());
    }

    @Override
    public List<LevelResponse> getByTypeUserId(Long typeUserId) {
        return levelMapper.toLevelResponses(levelRepostitory.findLevelsByTypeUserIdNative(typeUserId));
    }
}
