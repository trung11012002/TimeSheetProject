package com.timesheet.timesheetproject.mapper;


import com.timesheet.timesheetproject.dto.request.shedule.ScheduleConfigCreateRequest;
import com.timesheet.timesheetproject.dto.request.shedule.ScheduleConfigUpdateRequest;
import com.timesheet.timesheetproject.dto.request.user.UserUpdateRequest;
import com.timesheet.timesheetproject.dto.response.BranchResponse;
import com.timesheet.timesheetproject.dto.response.ScheduleConfigResponse;
import com.timesheet.timesheetproject.entity.Branch;
import com.timesheet.timesheetproject.entity.ScheduleConfig;
import com.timesheet.timesheetproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    ScheduleConfig toscheduleConfig(ScheduleConfigCreateRequest scheduleConfigCreateRequest);
    ScheduleConfigResponse toScheduleConfigResponse(ScheduleConfig scheduleConfig);
    void updateSheduleConfig(@MappingTarget ScheduleConfig ScheduleConfig, ScheduleConfigUpdateRequest scheduleConfigUpdateRequest);
    List<ScheduleConfigResponse> toScheduleConfigResponses(List<ScheduleConfig> scheduleConfig);

}
