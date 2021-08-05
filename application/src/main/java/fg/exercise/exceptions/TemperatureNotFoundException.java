package fg.exercise.exceptions;


public class TemperatureNotFoundException extends NotFoundException {

    public TemperatureNotFoundException(Long id) {
        this.code = 400;
        this.description = String.format("Temperature with id [%d] could not be found.", id);
    }
}
