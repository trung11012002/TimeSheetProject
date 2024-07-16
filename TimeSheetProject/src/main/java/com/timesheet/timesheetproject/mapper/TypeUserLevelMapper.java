package com.timesheet.timesheetproject.mapper;

import com.timesheet.timesheetproject.dto.request.typeuser.TypeUserCreationRequest;
import com.timesheet.timesheetproject.dto.request.typeuserlevel.TypeUserLevelCreationRequest;
import com.timesheet.timesheetproject.dto.request.typeuserlevel.TypeUserLevelRequest;
import com.timesheet.timesheetproject.dto.response.TypeUserLevelResponse;
import com.timesheet.timesheetproject.dto.response.TypeUserResponse;
import com.timesheet.timesheetproject.entity.TypeUser;
import com.timesheet.timesheetproject.entity.TypeUserLevel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TypeUserLevelMapper {
    //create
    TypeUserLevel toTypeUserLevel(TypeUserLevelCreationRequest typeUserLevelCreationRequest);

    //update

    //get
    TypeUserLevel toTypeUserLevel(TypeUserLevelRequest typeUserLevelRequest);

    //entity to response
    TypeUserLevelResponse toTypeUserLevelResponse(TypeUserLevel typeUserLevel);
    List<TypeUserLevelResponse> toTypeUserLevelResponses(List<TypeUserLevel> typeUserLevels);
}
