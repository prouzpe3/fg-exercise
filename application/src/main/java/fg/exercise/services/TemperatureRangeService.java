package fg.exercise.services;

import fg.exercise.models.entities.Temperature;

import java.util.List;


// TODO PPR - is there any reason for having interfaces with exactly single implementation?
// TODO PPR - JavaDoc documentation is missing entirely :(
public interface TemperatureRangeService {

    // TODO PPR - regarding naming - I recommend avoid duplication of names used in the service name
    // TODO PPR - I would also recommend using LocalTime for specifying time constraints even if the assignment stated using only hour resolution
    // TODO PPR - using @Nonnull and @Nullable annotation would help to determine mandatory and optional arguments here
    // TODO PPR - shouldn't this be named findLongestSequence as well? It does the same thing, doesn't it?
    List<Temperature> temperatureRangeGet(Double tempLBound, Double tempUBound, Integer timeLBound, Integer timeUBound) throws Exception;

    // TODO PPR - JavaDoc documentation is missing :(
    List<Temperature> findLongestSequence(List<Temperature> temperatureList, Double tempLBound, Double tempUBound) throws Exception;

}
