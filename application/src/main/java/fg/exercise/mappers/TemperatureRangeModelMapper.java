package fg.exercise.mappers;

import fg.exercise.models.dtos.GetTemperatureRangeDto;
import fg.exercise.models.entities.Temperature;


public interface TemperatureRangeModelMapper {

    GetTemperatureRangeDto convertToDto(Temperature initialTemperature, Temperature finalTemperature);

}
