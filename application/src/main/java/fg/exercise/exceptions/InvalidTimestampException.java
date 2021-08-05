package fg.exercise.exceptions;

import java.time.format.DateTimeParseException;

import static fg.exercise.mappers.TimestampConverter.TIMESTAMP_FORMAT;


public class InvalidTimestampException extends BadRequestException {

    public InvalidTimestampException(DateTimeParseException e) {
        this.code = 100;
        this.description = String.format("Input string [%s] could not be parsed. Supported format is: [%s]", e.getParsedString(), TIMESTAMP_FORMAT);
    }
}
