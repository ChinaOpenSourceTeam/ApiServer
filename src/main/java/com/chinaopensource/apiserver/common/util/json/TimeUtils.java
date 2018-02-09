package com.chinaopensource.apiserver.common.util.json;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * create by lzl ON 2018/02/08
 */
public class TimeUtils {
    public static final ZoneId DEFAULT_TIMEZONE = ZoneId.of("Asia/Shanghai");

    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Pattern dp = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");
    private static final Pattern dtp = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}$");


    public static String format(LocalDateTime localDateTime, DateTimeFormatter dateTimeFormatter) {
        return localDateTime.format(dateTimeFormatter);
    }

    public static LocalDateTime parse(String dateString) {
        if (dtp.matcher(dateString).matches()) {
            return LocalDateTime.parse(dateString, DATETIME_FORMATTER);
        } else if (dp.matcher(dateString).matches()) {
            LocalDate date = LocalDate.parse(dateString, DATE_FORMATTER);
            return LocalDateTime.of(date, LocalTime.of(0, 0, 0));
        } else {
            throw new RuntimeException("Malformed date string: " + dateString);
        }
    }

    //parse time by adding timeMax for timeRange.getEnd()
    public static LocalDateTime addDayEnd(LocalDateTime localDateTime) {
        if (localDateTime != null) {
            return LocalDateTime.of(localDateTime.toLocalDate(), LocalTime.MAX);
        }
        return null;
    }

    public static LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    public static LocalDateTime getYesterday() {
        return LocalDateTime.now().minusDays(1);
    }

    public static LocalDateTime getTodayStart() {
        LocalDateTime now = LocalDateTime.now();
        return LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0, 0);
    }

    public static LocalDateTime getDayStart(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 0, 0, 0);
    }

    public static LocalDateTime getDayEnd(LocalDateTime dateTime) {
        return LocalDateTime.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth(), 23, 59, 59);
    }

    public static long getTimestamp(LocalDateTime localDateTime) {
        Date date = Date.from(localDateTime.atZone(DEFAULT_TIMEZONE).toInstant());
        return date.getTime();
    }

    public static long getTimestamp(String dateString, DateTimeFormatter dateTimeFormatter) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateString, dateTimeFormatter);
        return getTimestamp(localDateTime);
    }

    public static LocalDateTime getLocalDateTime(Long timestamp) {
        Date date = new Date(timestamp);
        return LocalDateTime.ofInstant(date.toInstant(), TimeUtils.DEFAULT_TIMEZONE);
    }

}
