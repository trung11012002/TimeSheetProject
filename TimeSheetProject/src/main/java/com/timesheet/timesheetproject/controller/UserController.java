package com.timesheet.timesheetproject.controller;

import com.timesheet.timesheetproject.dto.request.user.UserResetPasswordRequest;
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
    ApiResponse<UserResponse> createUser(@RequestBody @Valid  UserCreationRequest request){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
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
                .result(userService.getUserById(userId))
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
    @PutMapping("")
    ApiResponse<UserResponse> updateUser(@RequestBody UserUpdateRequest request){
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.updateUser(request))
                .build();
    }

    @PreAuthorize("hasAuthority('delete-user')")
    @DeleteMapping("/{userId}")
    String deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return "User has been deleted";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/reset-password")
    ApiResponse<UserResponse> deleteUser(@RequestBody UserResetPasswordRequest request){
        return ApiResponse.<UserResponse>builder()
                .code(1000)
                .result(userService.resetPassword(request))
                .build();
    }
    @GetMapping("/search")
    ApiResponse<List<UserResponse>> searchUsers(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "positionId", required = false) Long positionId,
            @RequestParam(value = "active", required = false) Boolean active,
            @RequestParam(value = "userTypeId", required = false) Long userTypeId,
            @RequestParam(value = "branchId", required = false) Long branchId,
            @RequestParam(value = "levelId", required = false) Long levelId){

        if ("".equals(username)) {
            username = null;
        }
        return ApiResponse.<List<UserResponse>>builder()
                .code(1000)
                .result(userService.searchUsers(username,positionId,active,userTypeId,branchId,levelId))
                .build();
    }
}
