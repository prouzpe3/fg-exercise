package fg.exercise.apis;

import fg.exercise.mappers.TemperatureModelMapper;
import fg.exercise.models.dtos.GetTemperatureDto;
import fg.exercise.models.dtos.PostTemperatureDto;
import fg.exercise.models.dtos.PutTemperatureDto;
import fg.exercise.models.entities.Temperature;
import fg.exercise.services.TemperaturesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class TemperaturesApiDelegateImpl implements TemperaturesApiDelegate {

    private final TemperaturesService temperaturesService;
    private final TemperatureModelMapper temperatureModelMapper;


    public TemperaturesApiDelegateImpl(TemperaturesService temperaturesService, TemperatureModelMapper temperatureModelMapper) {
        this.temperaturesService = temperaturesService;
        this.temperatureModelMapper = temperatureModelMapper;
    }


    @Override
    public ResponseEntity<List<GetTemperatureDto>> temperaturesGet() throws Exception {
        return ResponseEntity.ok(temperaturesService.temperaturesGet().stream().map(temperatureModelMapper::convertToDto).collect(Collectors.toList()));
    }


    @Override
    public ResponseEntity<Void> temperaturesIdDelete(Long id) throws Exception {
        temperaturesService.temperaturesIdDelete(id);
        return ResponseEntity.of(Optional.empty());
    }


    @Override
    public ResponseEntity<GetTemperatureDto> temperaturesIdGet(Long id) throws Exception {
        return ResponseEntity.ok(temperatureModelMapper.convertToDto(temperaturesService.temperaturesIdGet(id)));
    }


    @Override
    public ResponseEntity<GetTemperatureDto> temperaturesIdPut(Long id, PutTemperatureDto putTemperatureDto) throws Exception {
        Temperature temperature = temperatureModelMapper.convertToEntity(id, putTemperatureDto);
        return ResponseEntity.ok(temperatureModelMapper.convertToDto(temperaturesService.temperaturesIdPut(id, temperature)));
    }


    @Override
    public ResponseEntity<GetTemperatureDto> temperaturesPost(PostTemperatureDto postTemperatureDto) throws Exception {
        Temperature temperature = temperaturesService.temperaturesPost(temperatureModelMapper.convertToEntity(postTemperatureDto));
        return ResponseEntity.created(
                URI.create(getRequest().map(nativeWebRequest -> nativeWebRequest.getContextPath() + "/" + temperature.getId()).orElse("")))
                .body(temperatureModelMapper.convertToDto(temperature));
    }
}
