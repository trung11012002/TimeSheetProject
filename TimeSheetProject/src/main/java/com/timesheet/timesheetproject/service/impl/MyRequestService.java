package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.request.myrequest.MyRequestEarlyOrLateCreationRequest;
import com.timesheet.timesheetproject.dto.request.myrequest.MyRequestLeaveCreationRequest;
import com.timesheet.timesheetproject.dto.response.MyRequestResponse;
import com.timesheet.timesheetproject.entity.MyRequest;
import com.timesheet.timesheetproject.entity.User;
import com.timesheet.timesheetproject.exception.AppException;
import com.timesheet.timesheetproject.exception.ErrorCode;
import com.timesheet.timesheetproject.mapper.MyrequestMapper;
import com.timesheet.timesheetproject.repository.MyRequestRepository;
import com.timesheet.timesheetproject.repository.UserRepository;
import com.timesheet.timesheetproject.service.IMyRequestService;
import com.timesheet.timesheetproject.util.LocalDateUtils;
import com.timesheet.timesheetproject.util.SecurityUtils;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MyRequestService implements IMyRequestService {
    @Autowired
    MyRequestRepository myRequestRepository;
    @Autowired
    MyrequestMapper myrequestMapper;
    @Autowired
    UserRepository userRepository;

    @Override
    public MyRequestResponse createRequestLeave(MyRequestLeaveCreationRequest request) {

        //get username from context
        String username = SecurityUtils.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        //Check if there are any leave requests on this date ("Full day, Morning, Afternoon")
        MyRequest myRequestOld = myRequestRepository.findByTypeRequestLeave(request.getDate(), username);
        if (myRequestOld != null) {
            throw new AppException(ErrorCode.REQUEST_INVALID);
        }

        MyRequest myRequest = myrequestMapper.toMyRequest(request);
        myRequest.setUser(user);
        myRequest.setStatus("PENDING");
        return myrequestMapper.toMyRequestResponse(myRequestRepository.save(myRequest));

    }

    @Override
    public MyRequestResponse createRequestEarlyOrLate(MyRequestEarlyOrLateCreationRequest request) {
        //get username from context
        String username = SecurityUtils.getUsername();
        User user = userRepository.findByUsername(username).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        //check had request leave full day
        MyRequest myRequestFullDay = myRequestRepository.findByTypeRequestLeaveFullDay(request.getDate(), username);
        if (myRequestFullDay != null) {
            throw new AppException(ErrorCode.REQUEST_INVALID);
        }

        //case: request = late (check this date had late)
        if (request.getType().equals("Late") && myRequestRepository.findMyRequestLateByDateAndUsername(request.getDate(), username) != null) {
            throw new AppException(ErrorCode.REQUEST_INVALID);
        }

        //case: request = early
        if (request.getType().equals("Early") && myRequestRepository.findMyRequestEarlyByDateAndUsername(request.getDate(), username) != null) {
            throw new AppException(ErrorCode.REQUEST_INVALID);
        }

        //case: this date had late and early
        int sumMyRequestEarlyAndLateOneDay = myRequestRepository.findMyRequestEarlyOrLateByDateAndUsername(request.getDate(), username);
        if (sumMyRequestEarlyAndLateOneDay >= 2) {
            throw new AppException(ErrorCode.REQUEST_INVALID);
        }
        //case: sum hour request late and early > 2
        if ((myRequestRepository.sumHourRequestOneDay(request.getDate(),username) +request.getHour()) > 2) {
            throw new AppException(ErrorCode.REQUEST_INVALID);
        }

        //mapper
        MyRequest myRequest = myrequestMapper.toMyRequest(request);
        myRequest.setStatus("PENDING");
        myRequest.setUser(user);

        //check sum request late + request early < 2 of week
        //or sum request late + request early < 4 of month
        LocalDate startDateOfWeek = LocalDateUtils.getStartOfWeek(request.getDate());
        LocalDate endDateOfWeek = LocalDateUtils.getEndOfWeek(request.getDate());
        LocalDate startDateOfMonth = LocalDateUtils.getStartOfMonth(request.getDate());
        LocalDate endDateOfMonth = LocalDateUtils.getEndOfMonth(request.getDate());
        if (myRequestRepository.sumTypeRequestLateOrRequestEarlyOfWeek(startDateOfWeek, endDateOfWeek, username) >= 2
                || myRequestRepository.sumTypeRequestLateOrRequestEarlyOfMonth(startDateOfMonth, endDateOfMonth, username) >= 4
                || sumMyRequestEarlyAndLateOneDay == 1) {
            myRequest.setStatus("REJECTED");
        }

        return myrequestMapper.toMyRequestResponse(myRequestRepository.save(myRequest));
    }

    @Override
    public String cancelMyRequest(Long myRequestId) {
        String username = SecurityUtils.getUsername();
        LocalDate dateNow = LocalDate.now();
        MyRequest myRequest = myRequestRepository.findByIdAndUserName(myRequestId,username)
                .orElseThrow(() -> new AppException(ErrorCode.MY_REQUEST_NOT_EXISTED));
        if(dateNow.compareTo(myRequest.getDate()) >= 0){
            return "Cancel false";
        }
        myRequestRepository.deleteById(myRequestId);
        return "Cancel success";
    }

    @Override
    public List<MyRequestResponse> getMyRequestMonthOfUser(LocalDate date) {
        String username = SecurityUtils.getUsername();
        LocalDate startDateOfMonth = LocalDateUtils.getStartOfMonth(date);
        LocalDate endDateOfMonth = LocalDateUtils.getEndOfMonth(date);
        return myRequestRepository.findMyRequestMonthOfUser(startDateOfMonth,endDateOfMonth,username);
    }
}
