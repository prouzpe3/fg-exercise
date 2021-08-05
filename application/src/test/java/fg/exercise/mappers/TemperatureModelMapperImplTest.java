package fg.exercise.mappers;

import fg.exercise.models.dtos.GetTemperatureDto;
import fg.exercise.models.dtos.PostTemperatureDto;
import fg.exercise.models.dtos.PutTemperatureDto;
import fg.exercise.models.entities.Temperature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TemperatureModelMapperImplTest {

    private final TemperatureModelMapper temperatureModelMapper = new TemperatureModelMapperImpl();


    @Test
    public void convertToEntity_postDto_shouldSetAllParams() {
        Double temp = 12.4d;
        PostTemperatureDto postTemperatureDto = new PostTemperatureDto();
        postTemperatureDto.setTimestamp("2020-02-10 10:24:12");
        postTemperatureDto.setTemperature(temp);

        Temperature temperature = temperatureModelMapper.convertToEntity(postTemperatureDto);
        assertEquals(null, temperature.getId());
        assertEquals(temp, temperature.getTemperature());
        assertEquals(LocalDate.of(2020, 2, 10), temperature.getLocalDate());
        assertEquals(LocalTime.of(10, 24, 12), temperature.getLocalTime());
    }


    @Test
    public void convertToEntity_putDto_shouldSetAllParamsIncludingId() {
        Long id = 12L;
        Double temp = 14.8d;
        PutTemperatureDto putTemperatureDto = new PutTemperatureDto();
        putTemperatureDto.setTimestamp("2020-02-10 10:24:12");
        putTemperatureDto.setTemperature(temp);

        Temperature temperature = temperatureModelMapper.convertToEntity(id, putTemperatureDto);
        assertEquals(id, temperature.getId());
        assertEquals(temp, temperature.getTemperature());
        assertEquals(LocalDate.of(2020, 2, 10), temperature.getLocalDate());
        assertEquals(LocalTime.of(10, 24, 12), temperature.getLocalTime());
    }


    @Test
    public void convertToDto_shouldSetAllParams() {
        Long id = 12L;
        Double temp = 14.8d;
        Temperature temperature = new Temperature();
        temperature.setId(id);
        temperature.setLocalDate(LocalDate.of(2020, 2, 10));
        temperature.setLocalTime(LocalTime.of(10, 24, 12));
        temperature.setTemperature(temp);

        GetTemperatureDto dto = temperatureModelMapper.convertToDto(temperature);
        assertEquals(id, dto.getId());
        assertEquals(temp, dto.getTemperature());
        assertEquals("2020-02-10 10:24:12", dto.getTimestamp());
    }

}
