package fg.exercise.exceptions;

import java.time.format.DateTimeParseException;

import static fg.exercise.mappers.TimestampConverter.TIMESTAMP_FORMAT;


public class TemperatureForGivenTimestampAlreadyExistsException extends BadRequestException {

    public TemperatureForGivenTimestampAlreadyExistsException(String timestamp) {
        this.code = 1000;
        this.description = "A temperature record with given timestamp [" + timestamp + "] already exists.";
    }
}
