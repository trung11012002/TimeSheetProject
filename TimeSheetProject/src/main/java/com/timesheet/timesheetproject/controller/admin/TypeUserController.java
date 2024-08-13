package com.timesheet.timesheetproject.controller.admin;

import com.timesheet.timesheetproject.dto.response.ApiResponse;
import com.timesheet.timesheetproject.dto.response.TypeUserResponse;
import com.timesheet.timesheetproject.service.ITypeUserService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/type-user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TypeUserController {
    @Autowired
    ITypeUserService typeUserService;
    @GetMapping()
    ApiResponse<List<TypeUserResponse>> getAllTypeUser(){
        return ApiResponse.<List<TypeUserResponse>>builder()
                .code(1000)
                .result(typeUserService.getAllTypeUser())
                .build();
    }
}
