package com.timesheet.timesheetproject.controller;

import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.request.user.UserCreationRequest;
import com.timesheet.timesheetproject.dto.request.user.UserUpdateRequest;
import com.timesheet.timesheetproject.dto.response.UserResponse;
import com.timesheet.timesheetproject.entity.User;
import com.timesheet.timesheetproject.service.IUserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "userRestControllerOfAdmin")
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {
    IUserService userService;

    @PreAuthorize("hasAuthority('create-user')")
    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
        ApiResponse<User> apiResponse = new ApiResponse<>();
        apiResponse.setResult(userService.createUser(request));
        return apiResponse;
    }

    @PreAuthorize("hasAuthority('get-user-all')")
    @GetMapping
    ApiResponse<List<UserResponse>> getUsers(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Username: {}", authentication.getName());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info("Role: {}", grantedAuthority.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAllUser())
                .build();
    }

    @PreAuthorize("hasAuthority('get-user-id')")
    @GetMapping("/{userId}")
    ApiResponse<UserResponse> getUser(@PathVariable("userId") Long userId){
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.getUser(userId))
                .build();
    }

    @PreAuthorize("hasAuthority('my-info')")
    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo(){
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @PreAuthorize("hasAuthority('update-user')")
    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable("userId") Long userId, @RequestBody UserUpdateRequest request){
        return userService.updateUser(userId , request);
    }

    @PreAuthorize("hasAuthority('delete-user')")
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }

}
