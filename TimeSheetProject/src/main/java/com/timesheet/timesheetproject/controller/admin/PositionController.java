package com.timesheet.timesheetproject.controller.admin;

import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.PositionResponse;
import com.timesheet.timesheetproject.service.IPositionService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/position")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionController {
    @Autowired
    IPositionService positionService;

    @GetMapping()
    ApiResponse<List<PositionResponse>> getAllPosition(){
        return ApiResponse.<List<PositionResponse>>builder()
                .code(1000)
                .result(positionService.getAllPosition())
                .build();
    }
}
