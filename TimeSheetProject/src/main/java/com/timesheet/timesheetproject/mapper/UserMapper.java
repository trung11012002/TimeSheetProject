package com.timesheet.timesheetproject.mapper;


import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.request.user.UserUpdateRequest;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import com.timesheet.timesheetproject.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "createdDate", source = "createdDate")
//    @Mapping(target = "modifiedDate", source = "modifiedDate")
//    @Mapping(target = "createdBy", source = "createdBy")
//    @Mapping(target = "modifiedBy", source = "modifiedBy")
    UserResponse toUserResponse(User user);
    List<UserResponse> toUserResponses(List<User> users);
}
