package fg.exercise.apis;

import fg.exercise.exceptions.TemperatureForGivenTimestampAlreadyExistsException;
import fg.exercise.mappers.TimestampConverter;
import fg.exercise.models.entities.Temperature;
import fg.exercise.repositories.TemperatureRepository;
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

import static fg.exercise.utils.TemperatureFactory.createRandomTemperature;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
class TemperaturesApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TemperaturesApiDelegate temperaturesApiDelegate;

    @MockBean
    private TemperaturesService temperaturesService;
    @MockBean
    private TemperatureRepository temperatureRepository;


    @Test
    void postTemperature_validInput_shouldReturn200() throws Exception {
        when(temperaturesService.temperaturesPost(any())).thenReturn(createRandomTemperature());
        mockMvc.perform(post("/FG-Exercise/temperatures")
                .content("{\"timestamp\": \"2021-08-04 16:00:02\",\"temperature\": 10}")
                .contentType("application/json"))
                .andExpect(status().isCreated());
    }


    @Test
    void postTemperature_validInput_shouldSetLocationHeader() throws Exception {
        when(temperaturesService.temperaturesPost(any())).thenReturn(createRandomTemperature());
        mockMvc.perform(post("/FG-Exercise/temperatures")
                .content("{\"timestamp\": \"2021-08-04 16:00:02\",\"temperature\": 10}")
                .contentType("application/json"))
                .andExpect(header().exists("Location"));
    }


    @Test
    void postTemperature_missingTemperature_shouldReturn400() throws Exception {
        when(temperaturesService.temperaturesPost(any())).thenReturn(createRandomTemperature());
        mockMvc.perform(post("/FG-Exercise/temperatures")
                .content("{\"timestamp\": \"2021-08-04 16:00:02\"}")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void postTemperature_missingTimestamp_shouldReturn400() throws Exception {
        when(temperaturesService.temperaturesPost(any())).thenReturn(createRandomTemperature());
        mockMvc.perform(post("/FG-Exercise/temperatures")
                .content("{\"temperature\": 10}")
                .contentType("application/json"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void postTemperature_shouldMapToBusinessModel() throws Exception {
        when(temperaturesService.temperaturesPost(any())).thenReturn(createRandomTemperature());
        mockMvc.perform(post("/FG-Exercise/temperatures")
                .content("{\"timestamp\": \"2021-08-04 16:00:02\",\"temperature\": 10}")
                .contentType("application/json"))
                .andExpect(status().isCreated());

        ArgumentCaptor<Temperature> temperatureCaptor = ArgumentCaptor.forClass(Temperature.class);
        verify(temperaturesService, times(1)).temperaturesPost(temperatureCaptor.capture());
        assertEquals(10d, temperatureCaptor.getValue().getTemperature());
        assertEquals(LocalDate.of(2021, 8, 4), temperatureCaptor.getValue().getLocalDate());
        assertEquals(LocalTime.of(16, 0, 2), temperatureCaptor.getValue().getLocalTime());
    }


    @Test
    void postTemperature_shouldReturnCreatedTemperature() throws Exception {
        Temperature temperature = createRandomTemperature();
        String resource = "{\"timestamp\":\"" + TimestampConverter.dateAndTimeToTimestamp(temperature.getLocalDate(), temperature.getLocalTime()) + "\",\"temperature\":" + temperature.getTemperature() + "}";
        when(temperaturesService.temperaturesPost(any())).thenReturn(temperature);
        MvcResult mvcResult = mockMvc.perform(post("/FG-Exercise/temperatures")
                .content(resource)
                .contentType("application/json"))
                .andReturn();
        String actualResponseBody = mvcResult.getResponse().getContentAsString();
        assertTrue(actualResponseBody.contains(TimestampConverter.dateAndTimeToTimestamp(temperature.getLocalDate(), temperature.getLocalTime())));
        assertTrue(actualResponseBody.contains(temperature.getTemperature().toString()));
    }


    @Test
    void postTemperature_alreadyExistingValue_shouldReturn400() throws Exception {
        Temperature temperature = createRandomTemperature();
        String timestamp = TimestampConverter.dateAndTimeToTimestamp(temperature.getLocalDate(), temperature.getLocalTime());
        String resource = "{\"timestamp\":\"" + timestamp + "\",\"temperature\":" + temperature.getTemperature() + "}";
        when(temperaturesService.temperaturesPost(any())).thenAnswer(invocationOnMock -> {
            throw new TemperatureForGivenTimestampAlreadyExistsException(timestamp);
        });
        MvcResult mvcResult = mockMvc.perform(post("/FG-Exercise/temperatures")
                .content(resource)
                .contentType("application/json"))
                .andExpect(status().isBadRequest())
                .andReturn();

        assertTrue(mvcResult.getResponse().getContentAsString().contains(
                "A temperature record with given timestamp [" + timestamp + "] already exists."));
    }
}
