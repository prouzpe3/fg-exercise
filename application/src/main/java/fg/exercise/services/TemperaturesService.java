package fg.exercise.services;

import fg.exercise.models.entities.Temperature;

import java.util.List;


public interface TemperaturesService {

    List<Temperature> temperaturesGet() throws Exception;


    void temperaturesIdDelete(Long id) throws Exception;


    Temperature temperaturesIdGet(Long id) throws Exception;


    Temperature temperaturesIdPut(Long id, Temperature putTemperatureDto) throws Exception;


    Temperature temperaturesPost(Temperature postTemperatureDto) throws Exception;
}
