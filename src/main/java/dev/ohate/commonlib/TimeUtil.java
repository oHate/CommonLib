package dev.ohate.commonlib;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

public final class TimeUtil {

    public static final DateTimeFormatter DEFAULT_DATE_FORMAT = DateTimeFormatter.ofPattern("EEE, MMM dd yyyy hh:mm a zzz")
            .withZone(ZoneId.of("US/Eastern"));

    private static final long MILLS_IN_SECOND = 1000L;
    private static final long MILLS_IN_MINUTE = MILLS_IN_SECOND * 60L;
    private static final long MILLS_IN_HOUR = MILLS_IN_MINUTE * 60L;
    private static final long MILLS_IN_DAY = MILLS_IN_HOUR * 24L;
    private static final long MILLS_IN_WEEK = MILLS_IN_DAY * 7L;
    private static final long MILLS_IN_MONTH = MILLS_IN_DAY * 30L;
    private static final long MILLS_IN_YEAR = MILLS_IN_MONTH * 12;

    private static final Map<Long, String> TIME_UNITS = new LinkedHashMap<>();

    static {
        TIME_UNITS.put(MILLS_IN_YEAR, "y");
        TIME_UNITS.put(MILLS_IN_MONTH, "M");
        TIME_UNITS.put(MILLS_IN_WEEK, "w");
        TIME_UNITS.put(MILLS_IN_DAY, "d");
        TIME_UNITS.put(MILLS_IN_HOUR, "h");
        TIME_UNITS.put(MILLS_IN_MINUTE, "m");
        TIME_UNITS.put(MILLS_IN_SECOND, "s");
    }

    public static String instantToString(Instant instant, DateTimeFormatter dateFormatter) {
        return dateFormatter.format(instant);
    }

    public static String instantToString(Instant instant) {
        return instantToString(instant, DEFAULT_DATE_FORMAT);
    }

    public static String instantToString(long milliseconds) {
        return instantToString(Instant.ofEpochMilli(milliseconds));
    }

    public static String instantToString() {
        return instantToString(Instant.now());
    }

    public static String formatTimer(long milliseconds) {
        StringBuilder builder = new StringBuilder();

        for (Map.Entry<Long, String> entry : TIME_UNITS.entrySet()) {
            long unitMillis = entry.getKey();

            if (milliseconds >= unitMillis) {
                long unitValue = milliseconds / unitMillis;
                builder.append(unitValue).append(entry.getValue()).append(" ");
                milliseconds -= unitValue * unitMillis;
            }
        }

        return builder.toString().trim();
    }

    public static String formatTimer(Instant instant) {
        return formatTimer(instant.toEpochMilli());
    }

}
