package com.timesheet.timesheetproject.controller.personal;

import com.timesheet.timesheetproject.dto.request.myrequest.MyRequestEarlyOrLateCreationRequest;
import com.timesheet.timesheetproject.dto.request.myrequest.MyRequestLeaveCreationRequest;
import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.MyRequestResponse;
import com.timesheet.timesheetproject.service.IMyRequestService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/my-request")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyRequestController {
    @Autowired
    IMyRequestService myRequestService;

    @PostMapping("/create-request-leave")
    ApiResponse<MyRequestResponse> createMyRequestOff(@RequestBody @Valid MyRequestLeaveCreationRequest request) {
        return ApiResponse.<MyRequestResponse>builder()
                .code(1000)
                .result(myRequestService.createRequestLeave(request))
                .build();
    }

    @PostMapping("/create-request-late-early")
    ApiResponse<MyRequestResponse> createMyRequestLateOrEarly(@RequestBody @Valid MyRequestEarlyOrLateCreationRequest request) {
        return ApiResponse.<MyRequestResponse>builder()
                .code(1000)
                .result(myRequestService.createRequestEarlyOrLate(request))
                .build();
    }

    @DeleteMapping("/cancel")
    ApiResponse<String> createMyRequestLateOrEarly(@RequestParam("myRequestId") Long myRequestId) {
        return ApiResponse.<String>builder()
                .code(1000)
                .result(myRequestService.cancelMyRequest(myRequestId))
                .build();
    }
    @GetMapping("/month")
    ApiResponse<List<MyRequestResponse>> getMyRequestMonthOfUser(@RequestParam("date")LocalDate date) {
        return ApiResponse.<List<MyRequestResponse>>builder()
                .code(1000)
                .result(myRequestService.getMyRequestMonthOfUser(date))
                .build();
    }
}
