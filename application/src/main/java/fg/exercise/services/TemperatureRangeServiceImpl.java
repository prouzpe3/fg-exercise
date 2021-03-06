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
        List<Temperature> temperatureList
                = temperatureRepository.findOrdered(
                Optional.ofNullable(timeLBound).map(tlb -> LocalTime.of(tlb, 0)).orElse(LocalTime.MIN),
                Optional.ofNullable(timeUBound).map(tub -> LocalTime.of(tub, 0)).orElse(LocalTime.MAX)
        );
        return findLongestSequence(temperatureList, tempLBound, tempUBound);
    }


    @Override
    public List<Temperature> findLongestSequence(List<Temperature> temperatureList, Double tempLBound, Double tempUBound) {
        Temperature initialTemperature = null, lastInitialTemperature = null, lastFinalTemperature = null, previousTemperature = null;
        for (Temperature temperature : temperatureList) {
            if (tempLBound > temperature.getTemperature() || temperature.getTemperature() > tempUBound) {
                if (initialTemperature != null) {
                    if (lastInitialTemperature == null) {
                        lastInitialTemperature = initialTemperature;
                        lastFinalTemperature = previousTemperature;
                    } else {
                        if (getRangeLength(initialTemperature, previousTemperature) > getRangeLength(lastInitialTemperature, lastFinalTemperature)) {
                            lastInitialTemperature = initialTemperature;
                            lastFinalTemperature = previousTemperature;
                        }
                    }
                }
                initialTemperature = null;
                continue;
            }
            if (initialTemperature == null) {
                initialTemperature = temperature;
            }
            previousTemperature = temperature;
        }
        if (initialTemperature != null && initialTemperature != previousTemperature) {
            if (lastInitialTemperature == null) {
                lastInitialTemperature = initialTemperature;
                lastFinalTemperature = previousTemperature;
            } else {
                if (getRangeLength(initialTemperature, previousTemperature) > getRangeLength(lastInitialTemperature, lastFinalTemperature)) {
                    lastInitialTemperature = initialTemperature;
                    lastFinalTemperature = previousTemperature;
                }
            }
        }
        if (lastInitialTemperature == null || lastFinalTemperature == null) {
            throw new NoTemperatureRangeFoundException();
        }
        return Arrays.asList(lastInitialTemperature, lastFinalTemperature);
    }


    private long getRangeLength(Temperature initialTemperature, Temperature finalTemperature) {
        return initialTemperature.getLocalDate().atTime(initialTemperature.getLocalTime())
                .until(finalTemperature.getLocalDate().atTime(finalTemperature.getLocalTime()), ChronoUnit.MILLIS);
    }
}
