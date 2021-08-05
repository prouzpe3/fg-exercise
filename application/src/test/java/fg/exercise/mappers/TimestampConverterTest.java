package fg.exercise.mappers;

import fg.exercise.exceptions.InvalidTimestampException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TimestampConverterTest {

    @Test
    public void timestampToDate_invalidFormat_shouldThrow() {
        String timestamp = "2020-02-10T10:24:12";
        assertThrows(InvalidTimestampException.class, () ->
                TimestampConverter.timestampToDate(timestamp));
    }


    @Test
    public void timestampToDate_timeWithMillis_shouldThrow() {
        String timestamp = "2020-02-10 10:24:12.10";
        assertThrows(InvalidTimestampException.class, () ->
                TimestampConverter.timestampToDate(timestamp));
    }


    @Test
    public void timestampToDate_invalidTime_shouldThrow() {
        String timestamp = "2020-02-10 24:24:12";
        assertThrows(InvalidTimestampException.class, () ->
                TimestampConverter.timestampToDate(timestamp));
    }


    @Test
    public void timestampToDate_midnight_shouldThrow() {
        String timestamp = "2020-02-10 24:00:00";
        assertThrows(InvalidTimestampException.class, () ->
                TimestampConverter.timestampToDate(timestamp));
    }


    @Test
    public void timestampToDate_invalidDate_shouldThrow() {
        String timestamp = "2020-02-30 10:24:12";
        assertThrows(InvalidTimestampException.class, () ->
                TimestampConverter.timestampToDate(timestamp));
    }


    @Test
    public void timestampToDate_validInput_shouldSucceed() {
        String timestamp = "2020-02-10 10:24:12";
        assertEquals(LocalDate.of(2020, 2, 10), TimestampConverter.timestampToDate(timestamp));
    }


    @Test
    public void timestampToTime_invalidFormat_shouldThrow() {
        String timestamp = "2020-02-10T10:24:12";
        assertThrows(InvalidTimestampException.class, () ->
                TimestampConverter.timestampToTime(timestamp));
    }


    @Test
    public void timestampToTime_timeWithMillis_shouldThrow() {
        String timestamp = "2020-02-10 10:24:12.10";
        assertThrows(InvalidTimestampException.class, () ->
                TimestampConverter.timestampToTime(timestamp));
    }


    @Test
    public void timestampToTime_invalidTime_shouldThrow() {
        String timestamp = "2020-02-10 24:24:12";
        assertThrows(InvalidTimestampException.class, () ->
                TimestampConverter.timestampToTime(timestamp));
    }


    @Test
    public void timestampToTime_midnight_shouldThrow() {
        String timestamp = "2020-02-10 24:00:00";
        assertThrows(InvalidTimestampException.class, () ->
                TimestampConverter.timestampToTime(timestamp));
    }


    @Test
    public void timestampToTime_invalidDate_shouldThrow() {
        String timestamp = "2020-02-30 10:24:12";
        assertThrows(InvalidTimestampException.class, () ->
                TimestampConverter.timestampToTime(timestamp));
    }


    @Test
    public void timestampToTime_validInput_shouldSucceed() {
        String timestamp = "2020-02-10 10:24:12";
        assertEquals(LocalTime.of(10, 24, 12), TimestampConverter.timestampToTime(timestamp));
    }


    @Test
    public void dateAndTimeToTimestamp_shouldConvert() {
        assertEquals("2020-02-10 10:24:12", TimestampConverter.dateAndTimeToTimestamp(LocalDate.of(2020, 2, 10), LocalTime.of(10, 24, 12)));
    }
}
