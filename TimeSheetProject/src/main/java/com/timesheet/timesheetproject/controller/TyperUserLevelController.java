package com.timesheet.timesheetproject.controller;

import com.timesheet.timesheetproject.dto.response.ApiResponse;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/type-user-level")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TyperUserLevelController {
}
