package fg.exercise.services;

import fg.exercise.apis.TemperaturesApiDelegate;
import fg.exercise.exceptions.TemperatureNotFoundException;
import fg.exercise.mappers.TemperatureModelMapper;
import fg.exercise.models.dtos.GetTemperatureDto;
import fg.exercise.models.dtos.PostTemperatureDto;
import fg.exercise.models.dtos.PutTemperatureDto;
import fg.exercise.models.entities.Temperature;
import fg.exercise.repositories.TemperatureRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class TemperaturesService implements TemperaturesApiDelegate {

    private final TemperatureRepository temperatureRepository;
    private final TemperatureModelMapper temperatureModelMapper;


    public TemperaturesService(TemperatureRepository temperatureRepository, TemperatureModelMapper temperatureModelMapper) {
        this.temperatureRepository = temperatureRepository;
        this.temperatureModelMapper = temperatureModelMapper;
    }


    @Override
    public ResponseEntity<List<GetTemperatureDto>> temperaturesGet() throws Exception {
        return ResponseEntity.ok(temperatureRepository.findAll().stream().map(temperatureModelMapper::convertToDto).collect(Collectors.toList()));
    }


    @Override
    public ResponseEntity<Void> temperaturesIdDelete(Long id) throws Exception {
        temperatureRepository.deleteById(id);
        return ResponseEntity.of(Optional.empty());
    }


    @Override
    public ResponseEntity<GetTemperatureDto> temperaturesIdGet(Long id) throws Exception {
        return ResponseEntity.ok(temperatureRepository.findById(id).map(temperatureModelMapper::convertToDto).orElseThrow(() -> new TemperatureNotFoundException(id)));
    }


    @Override
    public ResponseEntity<GetTemperatureDto> temperaturesIdPut(Long id, PutTemperatureDto putTemperatureDto) throws Exception {
        Optional<Temperature> optionalTemperature = temperatureRepository.findById(id);
        if (optionalTemperature.isPresent()) {
            Temperature temperature = temperatureModelMapper.convertToEntity(putTemperatureDto);
            temperatureRepository.save(temperature);
            return ResponseEntity.ok(temperatureModelMapper.convertToDto(temperature));
        } else {
            return storeAsNew(temperatureModelMapper.convertToEntity(putTemperatureDto));
        }
    }


    @Override
    public ResponseEntity<GetTemperatureDto> temperaturesPost(PostTemperatureDto postTemperatureDto) throws Exception {
        Temperature temperature = temperatureModelMapper.convertToEntity(postTemperatureDto);
        return storeAsNew(temperature);
    }


    private ResponseEntity<GetTemperatureDto> storeAsNew(Temperature temperature) {
        temperatureRepository.save(temperature);
        return ResponseEntity.created(URI.create(getRequest().map(nativeWebRequest -> nativeWebRequest.getContextPath() + "/" + temperature.getId()).orElse(""))).body(temperatureModelMapper.convertToDto(temperature));
    }
}
