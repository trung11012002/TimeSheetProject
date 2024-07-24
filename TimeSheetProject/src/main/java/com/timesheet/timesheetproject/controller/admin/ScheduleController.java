package com.timesheet.timesheetproject.controller.admin;

import com.timesheet.timesheetproject.dto.request.shedule.ScheduleConfigCreateRequest;
import com.timesheet.timesheetproject.dto.request.shedule.ScheduleConfigUpdateRequest;
import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.BranchResponse;
import com.timesheet.timesheetproject.dto.response.ScheduleConfigResponse;
import com.timesheet.timesheetproject.service.IBranchService;
import com.timesheet.timesheetproject.service.IDynamicSchedulingService;
import com.timesheet.timesheetproject.service.impl.DynamicSchedulingService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleController {
    @Autowired
    IDynamicSchedulingService dynamicSchedulingService;

    @GetMapping("/invalid-token/{id}")
    ApiResponse<ScheduleConfigResponse> getScheduleDeleteAllInvalidToken(@PathVariable("id") Long id){
        return ApiResponse.<ScheduleConfigResponse>builder()
                .code(1000)
                .result(dynamicSchedulingService.getSheduleDeleteAllInvalidatedToken(id))
                .build();
    }

    @GetMapping("/invalid-tokens")
    ApiResponse<List<ScheduleConfigResponse>> getAllScheduleDeleteAllInvalidToken(@RequestBody ScheduleConfigCreateRequest request){
        dynamicSchedulingService.createSheduleDeleteAllInvalidatedToken(request);
        return ApiResponse.<List<ScheduleConfigResponse>>builder()
                .code(1000)
                .result(dynamicSchedulingService.getAllSheduleDeleteAllInvalidatedToken())
                .build();
    }

    @PostMapping("/invalid-token")
    ApiResponse<ScheduleConfigResponse> createScheduleDeleteAllInvalidToken(@RequestBody ScheduleConfigCreateRequest request){
        return ApiResponse.<ScheduleConfigResponse>builder()
                .code(1000)
                .result(dynamicSchedulingService.createSheduleDeleteAllInvalidatedToken(request))
                .build();
    }

    @PutMapping("/invalid-token")
    ApiResponse<ScheduleConfigResponse> updateScheduleDeleteAllInvalidToken(@RequestBody ScheduleConfigUpdateRequest request){
        return ApiResponse.<ScheduleConfigResponse>builder()
                .code(1000)
                .result(dynamicSchedulingService.updateSheduleDeleteAllInvalidatedToken(request))
                .build();
    }

    @DeleteMapping("/invalid-token")
    ApiResponse<String> deleteScheduleDeleteAllInvalidToken(@RequestParam("id") Long id){
        dynamicSchedulingService.deleteSheduleDeleteAllInvalidatedToken(id);
        return ApiResponse.<String>builder()
                .code(1000)
                .result("Succsess")
                .build();
    }
}
