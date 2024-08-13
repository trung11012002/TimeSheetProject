package com.timesheet.timesheetproject.mapper;

import com.timesheet.timesheetproject.dto.request.level.LevelCreationRequest;
import com.timesheet.timesheetproject.dto.request.position.PositionCreationRequest;
import com.timesheet.timesheetproject.dto.response.LevelResponse;
import com.timesheet.timesheetproject.dto.response.PositionResponse;
import com.timesheet.timesheetproject.entity.Level;
import com.timesheet.timesheetproject.entity.Position;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    Position toPosition(PositionCreationRequest positionCreationRequest);
    PositionResponse toPositionResponse(Position position);

    List<Position> toPositions(List<PositionCreationRequest> positionCreationRequests);
    List<PositionResponse> toPositionResponses(List<Position> positions);
}
