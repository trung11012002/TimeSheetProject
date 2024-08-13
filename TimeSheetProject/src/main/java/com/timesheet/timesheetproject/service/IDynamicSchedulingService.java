package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.request.shedule.ScheduleConfigCreateRequest;
import com.timesheet.timesheetproject.dto.request.shedule.ScheduleConfigUpdateRequest;
import com.timesheet.timesheetproject.dto.response.ScheduleConfigResponse;
import com.timesheet.timesheetproject.entity.ScheduleConfig;

import java.util.List;

public interface IDynamicSchedulingService {
    ScheduleConfigResponse getSheduleDeleteAllInvalidatedToken(Long id);
    List<ScheduleConfigResponse> getAllSheduleDeleteAllInvalidatedToken();

    ScheduleConfigResponse createSheduleDeleteAllInvalidatedToken(ScheduleConfigCreateRequest request);
    ScheduleConfigResponse updateSheduleDeleteAllInvalidatedToken(ScheduleConfigUpdateRequest request);
    void deleteSheduleDeleteAllInvalidatedToken(Long id);

}
