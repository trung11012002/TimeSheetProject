package com.timesheet.timesheetproject.controller.redis;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.timesheet.timesheetproject.dto.response.UserProjection;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import com.timesheet.timesheetproject.service.IBaseRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@JsonInclude(JsonInclude.Include.NON_NULL)
@RestController
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private IBaseRedisService<String,String, UserResponse> redisService;
    @PostMapping
    public void set(){
//        redisService.set("hihi" , "haha");
    }
    @GetMapping()
    public void get(){
        System.out.println(redisService.get("test1"));
    }
}
