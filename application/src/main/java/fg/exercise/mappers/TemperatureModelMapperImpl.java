package fg.exercise.mappers;

import fg.exercise.models.dtos.GetTemperatureDto;
import fg.exercise.models.dtos.PostTemperatureDto;
import fg.exercise.models.dtos.PutTemperatureDto;
import fg.exercise.models.entities.Temperature;
import org.springframework.stereotype.Component;


@Component
public class TemperatureModelMapperImpl implements TemperatureModelMapper {

    @Override
    public Temperature convertToEntity(PostTemperatureDto postTemperatureDto) {
        Temperature temperature = new Temperature();
        temperature.setTemperature(postTemperatureDto.getTemperature());
        temperature.setLocalDate(TimestampConverter.timestampToDate(postTemperatureDto.getTimestamp()));
        temperature.setLocalTime(TimestampConverter.timestampToTime(postTemperatureDto.getTimestamp()));
        return temperature;
    }


    @Override
    public Temperature convertToEntity(PutTemperatureDto putTemperatureDto) {
        Temperature temperature = new Temperature();
        temperature.setTemperature(putTemperatureDto.getTemperature());
        temperature.setLocalDate(TimestampConverter.timestampToDate(putTemperatureDto.getTimestamp()));
        temperature.setLocalTime(TimestampConverter.timestampToTime(putTemperatureDto.getTimestamp()));
        return temperature;
    }


    @Override
    public GetTemperatureDto convertToDto(Temperature temperature) {
        GetTemperatureDto getTemperatureDto = new GetTemperatureDto();
        getTemperatureDto.setId(temperature.getId());
        getTemperatureDto.setTimestamp(TimestampConverter.dateAndTimeToTimestamp(temperature.getLocalDate(), temperature.getLocalTime()));
        getTemperatureDto.setTemperature(temperature.getTemperature());
        return getTemperatureDto;
    }
}
