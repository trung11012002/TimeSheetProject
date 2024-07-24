package com.timesheet.timesheetproject.service.impl;

import com.timesheet.timesheetproject.dto.request.shedule.ScheduleConfigCreateRequest;
import com.timesheet.timesheetproject.dto.request.shedule.ScheduleConfigUpdateRequest;
import com.timesheet.timesheetproject.dto.response.ScheduleConfigResponse;
import com.timesheet.timesheetproject.entity.ScheduleConfig;
import com.timesheet.timesheetproject.exception.AppException;
import com.timesheet.timesheetproject.exception.ErrorCode;
import com.timesheet.timesheetproject.mapper.ScheduleMapper;
import com.timesheet.timesheetproject.repository.InvalidatedTokenRepository;
import com.timesheet.timesheetproject.repository.ScheduleConfigRepository;
import com.timesheet.timesheetproject.service.IDynamicSchedulingService;
import com.timesheet.timesheetproject.util.LocalDateUtils;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

@Service
@Slf4j
public class DynamicSchedulingService implements IDynamicSchedulingService {
    @Autowired
    private InvalidatedTokenRepository invalidatedTokenRepository;
    @Autowired
    private ScheduleConfigRepository scheduleConfigRepository;
    @Autowired
    private ScheduleMapper scheduleMapper;

    private ThreadPoolTaskScheduler taskScheduler;
    private Map<Long, ScheduledFuture<?>> scheduledTasks = new HashMap<>();

    @PostConstruct
    public void init() {
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.initialize();
        List<ScheduleConfig> configs = scheduleConfigRepository.findAll();

        Runnable task = new Runnable() {
            @Override
            public void run() {
                runTask();
            }
        };
        for(ScheduleConfig config : configs){
            addNewTask(task , config);
        }
    }


    public void updateCronExpression(ScheduleConfig scheduleConfig) {
        if (scheduledTasks.containsKey(scheduleConfig.getId())) {
            scheduledTasks.get(scheduleConfig.getId()).cancel(true);
        }
        Runnable task = new Runnable() {
            @Override
            public void run() {
                runTask();
            }
        };
        addNewTask(task, scheduleConfig);
    }

    public void addNewTask(Runnable task, ScheduleConfig scheduleConfig) {
        CronTrigger cronTrigger = new CronTrigger(scheduleConfig.getCronExpression());
        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(task, cronTrigger);
        scheduledTasks.put(scheduleConfig.getId(), scheduledFuture);
    }

    public void removeTask(Long id) {
        if (scheduledTasks.containsKey(id)) {
            scheduledTasks.get(id).cancel(true);
            scheduledTasks.remove(id);
        }
        scheduleConfigRepository.deleteById(id);
    }

    private void runTask() {
        LocalDate currentDate = LocalDate.now();
        LocalDate oneWeekAgo = currentDate.minusWeeks(1);
        LocalDate startOfWeek = LocalDateUtils.getStartOfWeek(oneWeekAgo);
        LocalDate endOfWeek = LocalDateUtils.getEndOfWeek(oneWeekAgo);
        invalidatedTokenRepository.deleteInvalidatedTokensBetweenDates(startOfWeek, endOfWeek);
    }

    @Override
    public ScheduleConfigResponse getSheduleDeleteAllInvalidatedToken(Long id) {
        ScheduleConfig scheduleConfig = scheduleConfigRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHORIZED));
        return scheduleMapper.toScheduleConfigResponse(scheduleConfig);
    }

    @Override
    public List<ScheduleConfigResponse> getAllSheduleDeleteAllInvalidatedToken() {
        return scheduleMapper.toScheduleConfigResponses(scheduleConfigRepository.findAll());
    }

    @Override
    public ScheduleConfigResponse createSheduleDeleteAllInvalidatedToken(ScheduleConfigCreateRequest request) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                runTask();
            }
        };

        ScheduleConfig scheduleConfig = scheduleMapper.toscheduleConfig(request);
        scheduleConfig = scheduleConfigRepository.save(scheduleConfig);

        addNewTask(task, scheduleConfig);
        return scheduleMapper.toScheduleConfigResponse(scheduleConfig);
    }

    @Override
    public ScheduleConfigResponse updateSheduleDeleteAllInvalidatedToken(ScheduleConfigUpdateRequest request) {
        ScheduleConfig scheduleConfig = scheduleConfigRepository.findById(request.getId())
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHORIZED));

        scheduleMapper.updateSheduleConfig(scheduleConfig, request);
        scheduleConfig = scheduleConfigRepository.save(scheduleConfig);
        updateCronExpression(scheduleConfig);
        return scheduleMapper.toScheduleConfigResponse(scheduleConfig);
    }

    @Override
    public void deleteSheduleDeleteAllInvalidatedToken(Long id) {
        removeTask(id);
    }
}
