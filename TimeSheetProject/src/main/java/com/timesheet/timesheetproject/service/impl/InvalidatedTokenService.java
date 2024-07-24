package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.repository.InvalidatedTokenRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvalidatedTokenService {
    @Autowired
    InvalidatedTokenRepository invalidatedTokenRepository;

//    @Scheduled(cron = "0 0 12 ? * MON")

//    @Scheduled(cron = "0 * * * * *")
//    public void deleteEveryMonday(){
//        log.info("hello");
//    }

}
