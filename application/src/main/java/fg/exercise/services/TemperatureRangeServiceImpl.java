package fg.exercise.services;

import fg.exercise.exceptions.NoTemperatureRangeFoundException;
import fg.exercise.models.entities.Temperature;
import fg.exercise.repositories.TemperatureRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class TemperatureRangeServiceImpl implements TemperatureRangeService {

    private final TemperatureRepository temperatureRepository;


    public TemperatureRangeServiceImpl(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }


    @Override
    public List<Temperature> temperatureRangeGet(Double tempLBound, Double tempUBound, Integer timeLBound, Integer timeUBound) {
        // TODO PPR - what if there are billions of measurements that don't fit into the memory - is there any solution for this?
        List<Temperature> temperatureList
                = temperatureRepository.findOrdered(
                Optional.ofNullable(timeLBound).map(tlb -> LocalTime.of(tlb, 0)).orElse(LocalTime.MIN),
                Optional.ofNullable(timeUBound).map(tub -> LocalTime.of(tub, 0)).orElse(LocalTime.MAX)
        );
        return findLongestSequence(temperatureList, tempLBound, tempUBound);
    }


    @Override
    public List<Temperature> findLongestSequence(List<Temperature> temperatureList, Double tempLBound, Double tempUBound) {
        // TODO PPR - this algorithm or at least variables should be somehow documented, it's not clear on first sight what how it works
        // TODO PPR - it would also help to envelop initial + final temparature to inner working DTO, that would have range length compute function inside, it would make this code much more readable
        Temperature initialTemperature = null, lastInitialTemperature = null, lastFinalTemperature = null, previousTemperature = null;
        // iterate through ascending ordered temperatures
        for (Temperature temperature : temperatureList) {
            // when temperature is outside bounds
            if (tempLBound > temperature.getTemperature() || temperature.getTemperature() > tempUBound) {
                // we have already set initial temperature
                if (initialTemperature != null) {
                    // there is no previous range present
                    if (lastInitialTemperature == null) {
                        // remember current range
                        lastInitialTemperature = initialTemperature;
                        lastFinalTemperature = previousTemperature;
                    } else {
                        // compare last computed range with current one and rewrite with current only if it has wider span
                        if (getRangeLength(initialTemperature, previousTemperature) > getRangeLength(lastInitialTemperature, lastFinalTemperature)) {
                            lastInitialTemperature = initialTemperature;
                            lastFinalTemperature = previousTemperature;
                        }
                    }
                }
                // reset initial temperature - we need to start range again
                initialTemperature = null;
                continue;
            }
            // remember initial temperature if none is present
            if (initialTemperature == null) {
                initialTemperature = temperature;
            }
            // remember last examined temperature
            previousTemperature = temperature;
        }
        // if we have initial temperature set and it's not the single temperature (TODO PPR this would be better checked at the start of the method with quick returning "no result")
        if (initialTemperature != null && initialTemperature != previousTemperature) {
            // TODO PPR - this block repeats the logic within previous for ... loop - consider refactoring into shared code
            // if there was no previous range
            if (lastInitialTemperature == null) {
                // init this range as the final one
                lastInitialTemperature = initialTemperature;
                lastFinalTemperature = previousTemperature;
            } else {
                // compare current range with previous one and choose the wider one as the winner
                if (getRangeLength(initialTemperature, previousTemperature) > getRangeLength(lastInitialTemperature, lastFinalTemperature)) {
                    lastInitialTemperature = initialTemperature;
                    lastFinalTemperature = previousTemperature;
                }
            }
        }
        // when no result is found report an exception
        if (lastInitialTemperature == null || lastFinalTemperature == null) {
            throw new NoTemperatureRangeFoundException();
        }
        // TODO PPR - also this would take advantage of the Range DTO if it existed
        // else return widest range
        return Arrays.asList(lastInitialTemperature, lastFinalTemperature);
    }


    private long getRangeLength(Temperature initialTemperature, Temperature finalTemperature) {
        return initialTemperature.getLocalDate().atTime(initialTemperature.getLocalTime())
                .until(finalTemperature.getLocalDate().atTime(finalTemperature.getLocalTime()), ChronoUnit.MILLIS);
    }
}
