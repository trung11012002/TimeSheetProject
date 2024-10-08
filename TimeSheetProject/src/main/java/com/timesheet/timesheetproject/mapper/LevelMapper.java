package com.timesheet.timesheetproject.mapper;

import com.timesheet.timesheetproject.dto.request.level.LevelCreationRequest;
import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.request.user.UserUpdateRequest;
import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LevelMapper {
    Level toLevel(LevelCreationRequest levelCreationRequest);
    LevelResponse toLevelResponse(Level level);

    List<Level> toLevels(List<LevelCreationRequest> levelCreationRequests);
    List<LevelResponse> toLevelResponses(List<Level> levels);
}
