package fg.exercise.mappers;

import fg.exercise.models.dtos.GetTemperatureRangeDto;
import fg.exercise.models.entities.Temperature;
import org.springframework.stereotype.Component;


@Component
public class TemperatureRangeModelMapperImpl implements TemperatureRangeModelMapper {

    @Override
    public GetTemperatureRangeDto convertToDto(Temperature initialTemperature, Temperature finalTemperature) {
        GetTemperatureRangeDto getTemperatureRangeDto = new GetTemperatureRangeDto();
        getTemperatureRangeDto.setTimestampFrom(TimestampConverter.dateAndTimeToTimestamp(initialTemperature.getLocalDate(), initialTemperature.getLocalTime()));
        getTemperatureRangeDto.setTimestampTo(TimestampConverter.dateAndTimeToTimestamp(finalTemperature.getLocalDate(), finalTemperature.getLocalTime()));
        return getTemperatureRangeDto;
    }
}
