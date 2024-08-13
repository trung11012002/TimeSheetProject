package com.timesheet.timesheetproject.controller.personal;

import com.timesheet.timesheetproject.dto.request.timesheet.TimeSheetCreationRequest;
import com.timesheet.timesheetproject.dto.request.timesheet.TimeSheetUpdateRequest;
import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.TimeSheetResponse;
import com.timesheet.timesheetproject.service.ITimeSheetService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController(value = "timeSheetControllerOfPersonal")
@RequestMapping("/time-sheet")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeSheetController {
    @Autowired
    ITimeSheetService timeSheetService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('create-timesheet')")
    ApiResponse<TimeSheetResponse> creation(@RequestBody @Valid TimeSheetCreationRequest request) {
        return ApiResponse.<TimeSheetResponse>builder()
                .code(1000)
                .result(timeSheetService.creation(request))
                .build();
    }

    @GetMapping("/week-now")
    ApiResponse<List<TimeSheetResponse>> getTimeSheetWeekNow() {
        return ApiResponse.<List<TimeSheetResponse>>builder()
                .code(1000)
                .result(timeSheetService.getTimeSheetWeekNowOfUser())
                .build();
    }

    @GetMapping("/week/{day}")
    ApiResponse<List<TimeSheetResponse>> getTimeSheetWeekByDay(@PathVariable LocalDate day) {
        return ApiResponse.<List<TimeSheetResponse>>builder()
                .code(1000)
                .result(timeSheetService.getTimeSheetWeekByDayOfUser(day))
                .build();
    }

    @GetMapping("/month/{day}")
    ApiResponse<List<TimeSheetResponse>> getTimeSheetMonthByDay(@PathVariable LocalDate day) {
        return ApiResponse.<List<TimeSheetResponse>>builder()
                .code(1000)
                .result(timeSheetService.getTimeSheetMonthByDayOfUser(day))
                .build();
    }

    @PutMapping("/update-week-pending/{day}")
    @PreAuthorize("hasAuthority('update-timesheet-week-to-pending')")
    ApiResponse<List<TimeSheetResponse>> updateTimeSheetWeekToPending(@PathVariable LocalDate day) {
        return ApiResponse.<List<TimeSheetResponse>>builder()
                .code(1000)
                .result(timeSheetService.updateTimeSheetWeekToPendingOfUser(day))
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
