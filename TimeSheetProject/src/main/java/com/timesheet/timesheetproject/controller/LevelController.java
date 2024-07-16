package com.timesheet.timesheetproject.controller;

import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.service.ILevelService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/level")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LevelController {
    @Autowired
    ILevelService levelService;

    @GetMapping("")
    ApiResponse<List<LevelResponse>> getAll() {
        return ApiResponse.<List<LevelResponse>>builder()
                .code(1000)
                .result(levelService.getAllLevel())
                .build();
    }
    @GetMapping("/type-user-id/{typeUserId}")
    ApiResponse<List<LevelResponse>> getByTypeUserId(@PathVariable Long typeUserId){
        return ApiResponse.<List<LevelResponse>>builder()
                .code(1000)
                .result(levelService.getByTypeUserId(typeUserId))
                .build();
    }
}
