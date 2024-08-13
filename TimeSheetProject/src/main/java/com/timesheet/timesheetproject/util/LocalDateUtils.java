package com.timesheet.timesheetproject.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

public class LocalDateUtils {
    public static LocalDate getStartOfWeek(LocalDate date) {
        return date.with(DayOfWeek.MONDAY);
    }

    public static LocalDate getEndOfWeek(LocalDate date) {
        return date.with(DayOfWeek.SUNDAY);
    }

    public static LocalDate getStartOfMonth(LocalDate date) {
        return date.withDayOfMonth(1);
    }

    public static LocalDate getEndOfMonth(LocalDate date) {
        YearMonth yearMonth = YearMonth.of(date.getYear(), date.getMonth());
        return yearMonth.atEndOfMonth();
    }
}
