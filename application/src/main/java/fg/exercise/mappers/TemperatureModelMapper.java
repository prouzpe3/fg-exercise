package fg.exercise.mappers;

import fg.exercise.models.dtos.GetTemperatureDto;
import fg.exercise.models.dtos.PostTemperatureDto;
import fg.exercise.models.dtos.PutTemperatureDto;
import fg.exercise.models.entities.Temperature;


public interface TemperatureModelMapper {

    Temperature convertToEntity(PostTemperatureDto postTemperatureDto);


    Temperature convertToEntity(PutTemperatureDto putTemperatureDto);


    GetTemperatureDto convertToDto(Temperature temperature);
}
