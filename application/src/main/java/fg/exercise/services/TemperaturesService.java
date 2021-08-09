package fg.exercise.services;

import fg.exercise.models.entities.Temperature;

import java.util.List;


// TODO PPR - is there any reason for having interfaces with exactly single implementation?
// TODO PPR - JavaDoc documentation is missing entirely :(
// TODO PPR - regarding naming - I recommend avoid duplication of names used in the service name, my recommendations are above methods
// TODO PPR - avoid returning generic Exception in the method signatures!
public interface TemperaturesService {

    // TODO PPR - recommended name `getAll`
    List<Temperature> temperaturesGet() throws Exception;

    // TODO PPR - deleteById
    void temperaturesIdDelete(Long id) throws Exception;

    // TODO PPR - getById
    Temperature temperaturesIdGet(Long id) throws Exception;

    // TODO PPR - update (id argument is superfluous here - what if Temperature#getId differ from id argument value?!)
    Temperature temperaturesIdPut(Long id, Temperature putTemperatureDto) throws Exception;

    // TODO PPR - insert
    Temperature temperaturesPost(Temperature postTemperatureDto) throws Exception;

}
