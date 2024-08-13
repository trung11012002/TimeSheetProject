package com.timesheet.timesheetproject.mapper;


import com.timesheet.timesheetproject.dto.response.BaseResponse;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import com.timesheet.timesheetproject.entity.Base;
import com.timesheet.timesheetproject.entity.Branch;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BaseMapper {
    BaseResponse toBaseResponse(Base base);
    Base toBase (BaseResponse baseResponse);
}
