package fg.exercise.exceptions;

import java.time.format.DateTimeParseException;

import static fg.exercise.mappers.TimestampConverter.TIMESTAMP_FORMAT;


public class InvalidTimestampException extends BadRequestException {

    public InvalidTimestampException(DateTimeParseException e) {
        this.code = 100;
        this.description = "Input string [" + e.getParsedString() + "] could not be parsed. Supported format is: [" + TIMESTAMP_FORMAT + "]";
    }
}
