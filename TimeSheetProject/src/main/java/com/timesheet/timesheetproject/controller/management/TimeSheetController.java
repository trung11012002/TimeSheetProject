package com.timesheet.timesheetproject.controller.management;

import com.timesheet.timesheetproject.dto.request.timesheet.TimeSheetUpdateRequest;
import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.TimeSheetResponse;
import com.timesheet.timesheetproject.service.ITimeSheetService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController(value = "timeSheetControllerOfManagement")
@RequestMapping("/management/time-sheet")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeSheetController {
    @Autowired
    ITimeSheetService timeSheetService;

    @GetMapping("/week")
    ApiResponse<List<TimeSheetResponse>> getTimeSheetWeekByDay(@RequestParam("date") LocalDate date) {
        return ApiResponse.<List<TimeSheetResponse>>builder()
                .code(1000)
                .result(timeSheetService.getTimeSheetWeekByDayOfManagement(date))
                .build();
    }

    @GetMapping("/month")
    ApiResponse<List<TimeSheetResponse>> getTimeSheetMonthByDay(@RequestParam("date") LocalDate date) {
        return ApiResponse.<List<TimeSheetResponse>>builder()
                .code(1000)
                .result(timeSheetService.getTimeSheetMonthByDayOfManagement(date))
                .build();
    }

    @GetMapping("/search")
    ApiResponse<Page<TimeSheetResponse>> searchTimeSheet(
            @RequestParam(value = "projectId" , required = false) Long projectId,
            @RequestParam(value = "branchId",required = false) Long branchId,
            @RequestParam(value = "username",required = false) String username,
            @RequestParam(value = "pageNo",defaultValue = "1") Integer pageNo,
            @RequestParam(value = "limit",defaultValue = "5") Integer limit) {
        return ApiResponse.<Page<TimeSheetResponse>>builder()
                .code(1000)
                .result(timeSheetService.searchOfManagement(projectId,branchId,username,pageNo,limit))
                .build();
    }


    @PutMapping("/update-timesheet")
    @PreAuthorize("hasAuthority('update-timesheet-by-user')")
    ApiResponse<TimeSheetResponse> updateTimeSheet(@RequestBody TimeSheetUpdateRequest request) {
        return ApiResponse.<TimeSheetResponse>builder()
                .code(1000)
                .result(timeSheetService.updateTimeSheet(request))
                .build();
    }

    @DeleteMapping("/delete/{timesheetId}")
    @PreAuthorize("hasAuthority('delete-timesheet-by-user')")
    ApiResponse<String> deleteTimeSheet(@PathVariable Long timesheetId) {
        timeSheetService.deleteTimesheet(timesheetId);
        return ApiResponse.<String>builder()
                .code(1000)
                .result("sucsses")
                .build();
    }
}
