package fg.exercise.mappers;

import fg.exercise.models.dtos.GetTemperatureRangeDto;
import fg.exercise.models.entities.Temperature;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemperatureRangeModelMapperImplTest {

    private final TemperatureRangeModelMapper temperatureRangeModelMapper = new TemperatureRangeModelMapperImpl();


    @Test
    public void convertToDto_shouldSetBothDateTimes() {
        Long id = 12L;
        Double temp = 14.8d;
        Temperature temperature1 = new Temperature();
        temperature1.setId(id);
        temperature1.setLocalDate(LocalDate.of(2020, 2, 10));
        temperature1.setLocalTime(LocalTime.of(10, 24, 12));
        temperature1.setTemperature(temp);

        Temperature temperature2 = new Temperature();
        temperature2.setId(id);
        temperature2.setLocalDate(LocalDate.of(2022, 2, 10));
        temperature2.setLocalTime(LocalTime.of(10, 24, 12));
        temperature2.setTemperature(temp);

        GetTemperatureRangeDto dto = temperatureRangeModelMapper.convertToDto(temperature1, temperature2);
        assertEquals("2020-02-10 10:24:12", dto.getTimestampFrom());
        assertEquals("2022-02-10 10:24:12", dto.getTimestampTo());
    }

}
