package com.timesheet.timesheetproject.controller.redis;

import com.timesheet.timesheetproject.service.IBaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private IBaseRedisService redisService;
    @PostMapping
    public void set(){
        redisService.set("hihi" , "haha");
    }
}
