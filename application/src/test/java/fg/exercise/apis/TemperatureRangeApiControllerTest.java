package fg.exercise.apis;

import fg.exercise.exceptions.NoTemperatureRangeFoundException;
import fg.exercise.exceptions.TemperatureForGivenTimestampAlreadyExistsException;
import fg.exercise.exceptions.TemperatureNotFoundException;
import fg.exercise.mappers.TimestampConverter;
import fg.exercise.models.entities.Temperature;
import fg.exercise.repositories.TemperatureRepository;
import fg.exercise.services.TemperatureRangeService;
import fg.exercise.services.TemperaturesService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static fg.exercise.utils.TemperatureFactory.createRandomTemperature;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class TemperatureRangeApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TemperatureRangeApiDelegate temperatureRangeApiDelegate;

    @MockBean
    private TemperatureRangeService temperatureRangeService;
    @MockBean
    private TemperatureRepository temperatureRepository;


    @Test
    void getTemperatureRange_existingRange_shouldReturn200() throws Exception {
        List<Temperature> temperatureList = new ArrayList<>();
        temperatureList.add(createRandomTemperature());
        temperatureList.add(createRandomTemperature());
        when(temperatureRangeService.temperatureRangeGet(any(), any(), any(), any())).thenReturn(temperatureList);
        mockMvc.perform(get("/FG-Exercise/temperature-range?tempLBound=10.5&tempUBound=20&timeLBound=10&timeUBound=15")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }


    @Test
    void getTemperatureRange_noRange_shouldReturn400() throws Exception {
        when(temperatureRangeService.temperatureRangeGet(any(), any(), any(), any())).thenAnswer(invocationOnMock -> {
            throw new NoTemperatureRangeFoundException();
        });
        mockMvc.perform(get("/FG-Exercise/temperature-range?tempLBound=10.5&tempUBound=20&timeLBound=10&timeUBound=15")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }
}
