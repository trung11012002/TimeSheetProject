package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.request.timesheet.TimeSheetCreationRequest;
import com.timesheet.timesheetproject.dto.request.timesheet.TimeSheetUpdateRequest;
import com.timesheet.timesheetproject.dto.response.TimeSheetResponse;
import com.timesheet.timesheetproject.entity.*;
import com.timesheet.timesheetproject.exception.AppException;
import com.timesheet.timesheetproject.exception.ErrorCode;
import com.timesheet.timesheetproject.mapper.TimeSheetMapper;
import com.timesheet.timesheetproject.repository.*;
import com.timesheet.timesheetproject.service.ITimeSheetService;
import com.timesheet.timesheetproject.util.LocalDateUtils;
import com.timesheet.timesheetproject.util.SecurityUtils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TimeSheetService implements ITimeSheetService {
    @Autowired
    TimeSheetRepository timeSheetRepository;
    @Autowired
    TimeSheetMapper timeSheetMapper;
    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public TimeSheetResponse creation(TimeSheetCreationRequest request) {
        //get username from context
        String username = SecurityUtils.getUsername();

        //check working hour < 0
        if (request.getWorkingTime() < 0) {
            throw new AppException(ErrorCode.WORKING_HOUR_LESS_ZERO);
        }

        //check user existed
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        //check sum hour timesheet day <= 16
        double sumWorkingHour = 0;
        List<TimeSheet> timeSheetsForSumWorkingHour = timeSheetRepository.getTimeSheetByDateAndUsername(request.getDate(),username);
        for(TimeSheet timeSheetWorkingHour : timeSheetsForSumWorkingHour){
            sumWorkingHour+=timeSheetWorkingHour.getWorkingTime();
        }
        sumWorkingHour+=request.getWorkingTime();
        if (sumWorkingHour > 16) {
            throw new AppException(ErrorCode.WORKING_HOUR_FOR_DAY_BIGGER_16);
        }

        //check user in the project && check task in the project
        Project project = projectRepository.checkUserInTheProject(username,request.getProjectId());
        if (project == null) throw new AppException(ErrorCode.USER_NOT_IN_PROJECT);
        Task task = taskRepository.checkTaskInTheProject(request.getProjectId(),request.getTaskId());
        if (task == null) throw new AppException(ErrorCode.TASK_NOT_IN_PROJECT);

        //mapper
        TimeSheet timeSheet = timeSheetMapper.toTimeSheet(request);
        timeSheet.setStatus("NEW");

        //set param
        timeSheet.setUser(user);
        timeSheet.setProject(project);
        timeSheet.setTask(task);

        return timeSheetMapper.toTimeSheetResponse(timeSheetRepository.save(timeSheet));
    }

    @Override
    public List<TimeSheetResponse> getTimeSheetWeekNowOfUser() {
        //get username from context
        String username = SecurityUtils.getUsername();
        //time date now for week
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfWeek = LocalDateUtils.getStartOfWeek(currentDate);
        LocalDate endOfWeek = LocalDateUtils.getEndOfWeek(currentDate);

        return timeSheetMapper.toTimeSheetResponses(timeSheetRepository.getTimeSheetByStartDateAndEnDateAndUsername(startOfWeek,endOfWeek,username));
    }

    @Override
    public List<TimeSheetResponse> getTimeSheetWeekByDayOfUser(LocalDate day) {
        //get username from context
        String username = SecurityUtils.getUsername();
        //time date for week
        LocalDate startOfWeek = LocalDateUtils.getStartOfWeek(day);
        LocalDate endOfWeek = LocalDateUtils.getEndOfWeek(day);
        return timeSheetMapper.toTimeSheetResponses(timeSheetRepository.getTimeSheetByStartDateAndEnDateAndUsername(startOfWeek,endOfWeek,username));
    }

    @Override
    public List<TimeSheetResponse> getTimeSheetMonthByDayOfUser(LocalDate day) {
        //get username from context
        String username = SecurityUtils.getUsername();
        //time date for week
        LocalDate startOfMonth = LocalDateUtils.getStartOfMonth(day);
        LocalDate endOfMonth = LocalDateUtils.getEndOfMonth(day);
        return timeSheetMapper.toTimeSheetResponses(timeSheetRepository.getTimeSheetByStartDateAndEnDateAndUsername(startOfMonth,endOfMonth,username));
    }

    @Transactional
    @Override
    public List<TimeSheetResponse> updateTimeSheetWeekToPendingOfUser(LocalDate day) {
        //get username from context
        String username = SecurityUtils.getUsername();
        //time date for week
        LocalDate startOfWeek = LocalDateUtils.getStartOfWeek(day);
        LocalDate endOfWeek = LocalDateUtils.getEndOfWeek(day);

        //select timesheet of week status = new
        List<TimeSheet> timeSheets = timeSheetRepository.getTimeSheetWeekStatusNewByUsername(startOfWeek,endOfWeek,username);

        //update from new to pending
        for(TimeSheet timeSheet : timeSheets){
            timeSheet.setStatus("PENDING");
        }
        return timeSheetMapper.toTimeSheetResponses(timeSheetRepository.saveAll(timeSheets));
    }

    @Override
    public TimeSheetResponse updateTimeSheet(TimeSheetUpdateRequest request) {
        //get username from context
        String username = SecurityUtils.getUsername();

        //check working hour < 0
        if (request.getWorkingTime() < 0) {
            throw new AppException(ErrorCode.WORKING_HOUR_LESS_ZERO);
        }

        //find timesheet byId
        TimeSheet timeSheet = timeSheetRepository.findById(request.getId()).orElseThrow(() -> new AppException(ErrorCode.TIMESHEET_NOT_EXISTED));

        //check user existed
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        //check sum hour timesheet day <= 16
        double sumWorkingHour = 0;
        List<TimeSheet> timeSheetsForSumWorkingHour = timeSheetRepository.getTimeSheetByDateAndUsername(timeSheet.getDate(),username);
        for(TimeSheet timeSheetWorkingHour : timeSheetsForSumWorkingHour){
            sumWorkingHour+=timeSheetWorkingHour.getWorkingTime();
        }
        sumWorkingHour-=timeSheet.getWorkingTime();
        sumWorkingHour+=request.getWorkingTime();
        if (sumWorkingHour > 16) {
            throw new AppException(ErrorCode.WORKING_HOUR_FOR_DAY_BIGGER_16);
        }

        //check user in the project && check task in the project
        Project project = projectRepository.checkUserInTheProject(username,request.getProjectId());
        if (project == null) throw new AppException(ErrorCode.USER_NOT_IN_PROJECT);
        Task task = taskRepository.checkTaskInTheProject(request.getProjectId(),request.getTaskId());
        if (task == null) throw new AppException(ErrorCode.TASK_NOT_IN_PROJECT);

        //mapper
        timeSheetMapper.updateTimesheet(timeSheet,request);
        timeSheet.setUser(user);
        timeSheet.setProject(project);
        timeSheet.setTask(task);

        return timeSheetMapper.toTimeSheetResponse(timeSheetRepository.save(timeSheet));
    }

    @Override
    public void deleteTimesheet(Long timesheetId) {
        //get username from context
        String username = SecurityUtils.getUsername();
        TimeSheet timeSheet = timeSheetRepository.findById(timesheetId).orElseThrow(() -> new AppException(ErrorCode.TIMESHEET_NOT_EXISTED));
        if(!username.equals(timeSheet.getUser().getUsername())){
            throw new AppException(ErrorCode.TIMESHEET_NOT_OF_USER);
        }
        timeSheetRepository.delete(timeSheet);
    }

    @Override
    public List<TimeSheetResponse> getTimeSheetWeekByDayOfManagement(LocalDate date) {
        LocalDate startDate = LocalDateUtils.getStartOfWeek(date);
        LocalDate endDate = LocalDateUtils.getEndOfWeek(date);
        return timeSheetMapper.toTimeSheetResponses(timeSheetRepository.getTimeSheetByStartDateAndEndDate(startDate,endDate));
    }

    @Override
    public List<TimeSheetResponse> getTimeSheetMonthByDayOfManagement(LocalDate date) {
        LocalDate startDate = LocalDateUtils.getStartOfWeek(date);
        LocalDate endDate = LocalDateUtils.getEndOfWeek(date);
        return timeSheetMapper.toTimeSheetResponses(timeSheetRepository.getTimeSheetByStartDateAndEndDate(startDate,endDate));
    }

    @Override
    public Page<TimeSheetResponse> searchOfManagement(Long projectId, Long branchId, String username,Integer pageNo,Integer limit) {
        Pageable pageable = PageRequest.of(pageNo-1,limit);
        Page<TimeSheet> timeSheets = timeSheetRepository.searchOfManagement(username,projectId,branchId,pageable);
        return timeSheets.map(timeSheetMapper::toTimeSheetResponse);

    }
}
