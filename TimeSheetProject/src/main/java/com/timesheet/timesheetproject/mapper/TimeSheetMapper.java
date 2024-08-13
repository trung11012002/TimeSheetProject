package com.timesheet.timesheetproject.mapper;


import com.timesheet.timesheetproject.dto.request.timesheet.TimeSheetCreationRequest;
import com.timesheet.timesheetproject.dto.request.timesheet.TimeSheetUpdateRequest;
import com.timesheet.timesheetproject.dto.request.user.UserUpdateRequest;
import com.timesheet.timesheetproject.dto.response.BranchResponse;
import com.timesheet.timesheetproject.dto.response.TimeSheetResponse;
import com.timesheet.timesheetproject.entity.Branch;
import com.timesheet.timesheetproject.entity.TimeSheet;
import com.timesheet.timesheetproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TimeSheetMapper {
    TimeSheetResponse toTimeSheetResponse(TimeSheet timeSheet);

    List<TimeSheetResponse> toTimeSheetResponses(List<TimeSheet> timeSheets);

    TimeSheet toTimeSheet(TimeSheetCreationRequest timeSheetCreationRequest);

    List<TimeSheet> toTimeSheets(List<TimeSheetCreationRequest> timeSheetCreationRequests);

    void updateTimesheet(@MappingTarget TimeSheet timeSheet, TimeSheetUpdateRequest request);

}
