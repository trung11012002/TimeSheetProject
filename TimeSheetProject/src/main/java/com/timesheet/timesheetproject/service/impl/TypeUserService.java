package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.response.TypeUserResponse;
import com.timesheet.timesheetproject.mapper.TypeUserMapper;
import com.timesheet.timesheetproject.repository.TypeUserRepository;
import com.timesheet.timesheetproject.service.ITypeUserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TypeUserService implements ITypeUserService {
    @Autowired
    TypeUserMapper typeUserMapper;
    @Autowired
    TypeUserRepository typeUserRepostitory;

    @Override
    public List<TypeUserResponse> getAllTypeUser() {
        return typeUserMapper.toTypeUserResponses(typeUserRepostitory.findAll());
    }
}
