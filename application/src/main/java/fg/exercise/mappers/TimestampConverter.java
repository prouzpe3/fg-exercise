package fg.exercise.mappers;

import fg.exercise.exceptions.InvalidTimestampException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;


public class TimestampConverter {

    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String TIME_FORMAT = "HH:mm:ss";
    public static final String TIMESTAMP_FORMAT = DATE_FORMAT + " " + TIME_FORMAT;

    protected static DateTimeFormatter getTimestampFormatter() {
        return new DateTimeFormatterBuilder().appendPattern(TIMESTAMP_FORMAT).toFormatter();
    }

    protected static DateTimeFormatter getDateFormatter() {
        return new DateTimeFormatterBuilder().appendPattern(DATE_FORMAT).toFormatter();
    }

    protected static DateTimeFormatter getTimeFormatter() {
        return new DateTimeFormatterBuilder().appendPattern(TIME_FORMAT).toFormatter();
    }

    public static LocalDate timestampToDate(String timestamp) {
        try {
            return LocalDate.parse(timestamp, getTimestampFormatter());
        } catch (DateTimeParseException e) {
            throw new InvalidTimestampException(e);
        }
    }

    public static LocalTime timestampToTime(String timestamp) {
        try {
            return LocalTime.parse(timestamp, getTimestampFormatter());
        } catch (DateTimeParseException e) {
            throw new InvalidTimestampException(e);
        }
    }

    public static String dateAndTimeToTimestamp(LocalDate date, LocalTime time) {
        String dateString = date.format(getDateFormatter());
        String timeString = time.format(getTimeFormatter());
        return dateString + " " + timeString;
    }

}
