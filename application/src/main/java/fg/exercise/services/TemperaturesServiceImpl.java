package fg.exercise.services;

import fg.exercise.exceptions.TemperatureForGivenTimestampAlreadyExistsException;
import fg.exercise.exceptions.TemperatureNotFoundException;
import fg.exercise.mappers.TimestampConverter;
import fg.exercise.models.entities.Temperature;
import fg.exercise.repositories.TemperatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TemperaturesServiceImpl implements TemperaturesService {

    private final TemperatureRepository temperatureRepository;


    public TemperaturesServiceImpl(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }


    @Override
    public List<Temperature> temperaturesGet() throws Exception {
        return temperatureRepository.findAll();
    }


    @Override
    public void temperaturesIdDelete(Long id) throws Exception {
        temperatureRepository.deleteById(id);
    }


    @Override
    public Temperature temperaturesIdGet(Long id) throws Exception {
        return temperatureRepository.findById(id).orElseThrow(() -> new TemperatureNotFoundException(id));
    }


    @Override
    public Temperature temperaturesIdPut(Long id, Temperature temperature) throws Exception {
        Optional<Temperature> optionalTemperature = temperatureRepository.findById(id);
        if (!optionalTemperature.isPresent()) {
            throw new TemperatureNotFoundException(id);
        }
        throwIfTemperatureWithGivenDateTimeAlreadyExists(temperature);
        return temperatureRepository.save(temperature);
    }


    @Override
    public Temperature temperaturesPost(Temperature temperature) throws Exception {
        throwIfTemperatureWithGivenDateTimeAlreadyExists(temperature);
        return temperatureRepository.save(temperature);
    }


    private void throwIfTemperatureWithGivenDateTimeAlreadyExists(Temperature temperature) {
        Optional<Temperature> entityFromDb = temperatureRepository.findTemperatureByLocalDateAndLocalTime(temperature.getLocalDate(), temperature.getLocalTime());
        if (entityFromDb.isPresent()) {
            if (temperature.getId() == null || !entityFromDb.get().getId().equals(temperature.getId())) {
                throw new TemperatureForGivenTimestampAlreadyExistsException(TimestampConverter.dateAndTimeToTimestamp(temperature.getLocalDate(), temperature.getLocalTime()));
            }
        }
    }
}
