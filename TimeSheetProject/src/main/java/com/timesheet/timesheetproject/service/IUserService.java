package com.timesheet.timesheetproject.service;

import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.request.user.UserUpdateRequest;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import com.timesheet.timesheetproject.entity.User;

import java.util.List;

public interface IUserService {
    User createUser(UserCreationRequest requet);
    List<UserResponse> getAllUser();
    UserResponse getUser(long id);
    UserResponse updateUser(long userId,UserUpdateRequest request);
    void deleteUser(long userId);
    UserResponse getMyInfo();
}
