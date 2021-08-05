package fg.exercise.services;

import fg.exercise.models.entities.Temperature;

import java.util.List;


public interface TemperatureRangeService {

    List<Temperature> temperatureRangeGet(Double tempLBound, Double tempUBound, Integer timeLBound, Integer timeUBound) throws Exception;


    List<Temperature> findLongestSequence(List<Temperature> temperatureList, Double tempLBound, Double tempUBound) throws Exception;

}
