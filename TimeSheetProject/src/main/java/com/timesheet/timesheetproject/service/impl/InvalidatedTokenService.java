package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.repository.InvalidatedTokenRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvalidatedTokenService {
    @Autowired
    InvalidatedTokenRepository
}
