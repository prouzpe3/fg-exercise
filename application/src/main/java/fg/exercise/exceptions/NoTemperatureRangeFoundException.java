package fg.exercise.exceptions;


public class NoTemperatureRangeFoundException extends BadRequestException {

    public NoTemperatureRangeFoundException() {
        this.code = 700;
        this.description = "There were no records that would form a temperature range satisfying the query.";
    }
}
