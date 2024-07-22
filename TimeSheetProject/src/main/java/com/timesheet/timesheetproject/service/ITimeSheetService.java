package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.request.timesheet.TimeSheetCreationRequest;
import com.timesheet.timesheetproject.dto.request.timesheet.TimeSheetUpdateRequest;
import com.timesheet.timesheetproject.dto.response.TimeSheetResponse;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ITimeSheetService {
    TimeSheetResponse creation(TimeSheetCreationRequest request);

    List<TimeSheetResponse> getTimeSheetWeekNowOfUser();

    List<TimeSheetResponse> getTimeSheetWeekByDayOfUser(LocalDate day);

    List<TimeSheetResponse> getTimeSheetMonthByDayOfUser(LocalDate day);

    List<TimeSheetResponse> updateTimeSheetWeekToPendingOfUser(LocalDate day);

    TimeSheetResponse updateTimeSheet(TimeSheetUpdateRequest request);
    void deleteTimesheet(Long timesheetId);

    List<TimeSheetResponse> getTimeSheetWeekByDayOfManagement(LocalDate date);
    List<TimeSheetResponse> getTimeSheetMonthByDayOfManagement(LocalDate date);
    Page<TimeSheetResponse> searchOfManagement(Long projectId, Long branchId, String username, Integer pageNo, Integer limit);


}
