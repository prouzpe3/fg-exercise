package fg.exercise.exceptions;


public class TemperatureNotFoundException extends NotFoundException {

    public TemperatureNotFoundException(Long id) {
        this.code = 400;
        this.description = "Temperature with id [" + id + "] could not be found.";
    }
}
