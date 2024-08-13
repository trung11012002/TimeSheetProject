package com.timesheet.timesheetproject.mapper;


import com.timesheet.timesheetproject.dto.request.myrequest.MyRequestEarlyOrLateCreationRequest;
import com.timesheet.timesheetproject.dto.request.myrequest.MyRequestLeaveCreationRequest;
import com.timesheet.timesheetproject.dto.response.MyRequestResponse;
import com.timesheet.timesheetproject.entity.MyRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MyrequestMapper {
    MyRequestResponse toMyRequestResponse(MyRequest myRequest);
    List<MyRequestResponse> toMyRequestResponses(List<MyRequest> myRequests);
    MyRequest toMyRequest(MyRequestLeaveCreationRequest myRequestLeaveCreationRequest);
    MyRequest toMyRequest(MyRequestEarlyOrLateCreationRequest myRequestEarlyOrLateCreationRequest);
}
