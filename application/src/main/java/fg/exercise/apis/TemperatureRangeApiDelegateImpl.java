package fg.exercise.apis;

import fg.exercise.mappers.TemperatureRangeModelMapper;
import fg.exercise.models.dtos.GetTemperatureRangeDto;
import fg.exercise.models.entities.Temperature;
import fg.exercise.services.TemperatureRangeService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TemperatureRangeApiDelegateImpl implements TemperatureRangeApiDelegate {

    private final TemperatureRangeService temperatureRangeService;
    private final TemperatureRangeModelMapper temperatureRangeModelMapper;


    public TemperatureRangeApiDelegateImpl(TemperatureRangeService temperatureRangeService, TemperatureRangeModelMapper temperatureRangeModelMapper) {
        this.temperatureRangeService = temperatureRangeService;
        this.temperatureRangeModelMapper = temperatureRangeModelMapper;
    }


    @Override
    public ResponseEntity<GetTemperatureRangeDto> temperatureRangeGet(Double tempLBound, Double tempUBound, Integer timeLBound, Integer timeUBound) throws Exception {
        List<Temperature> longestSequence = temperatureRangeService.temperatureRangeGet(tempLBound, tempUBound, timeLBound, timeUBound);
        return ResponseEntity.ok(temperatureRangeModelMapper.convertToDto(longestSequence.get(0), longestSequence.get(1)));
    }
}
