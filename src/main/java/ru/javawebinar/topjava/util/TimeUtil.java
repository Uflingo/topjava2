package ru.javawebinar.topjava.util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 07.01.2015.
 */
public class TimeUtil {
    public static boolean isBetween(LocalTime lt, LocalTime startTime, LocalTime endTime) {
        return lt.compareTo(startTime) >= 0 && lt.compareTo(endTime) <= 0;
    }
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String formatLocalDateTime(String localDateTimeString, String pattern) {
        if (localDateTimeString.matches("\\d{4}\\-\\d{2}\\-\\d{2}T\\d{2}:\\d{2}")){
            LocalDateTime createdLDT = LocalDateTime.parse(localDateTimeString);
            return createdLDT.format(DateTimeFormatter.ofPattern(pattern));
        }
        return localDateTimeString;
    }

}
