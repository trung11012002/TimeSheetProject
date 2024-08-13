package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.request.myrequest.MyRequestEarlyOrLateCreationRequest;
import com.timesheet.timesheetproject.dto.request.myrequest.MyRequestLeaveCreationRequest;
import com.timesheet.timesheetproject.dto.response.MyRequestResponse;

import java.time.LocalDate;
import java.util.List;

public interface IMyRequestService {
    MyRequestResponse createRequestLeave(MyRequestLeaveCreationRequest request);
    MyRequestResponse createRequestEarlyOrLate(MyRequestEarlyOrLateCreationRequest request);
    String cancelMyRequest(Long myRequestId);
    List<MyRequestResponse> getMyRequestMonthOfUser(LocalDate date);

}
