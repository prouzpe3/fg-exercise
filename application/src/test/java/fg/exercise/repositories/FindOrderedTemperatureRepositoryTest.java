package fg.exercise.repositories;

import fg.exercise.models.entities.Temperature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static fg.exercise.utils.TemperatureFactory.createTemperature;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class FindOrderedTemperatureRepositoryTest {

    @Autowired
    private TemperatureRepository temperatureRepository;


    private Temperature morningTemperature1;
    private Temperature afternoonTemperature1;
    private Temperature eveningTemperature1;
    private Temperature morningTemperature2;
    private Temperature afternoonTemperature2;
    private Temperature eveningTemperature2;


    @BeforeEach
    public void setUp() throws Exception {
        setupTemperatures();
    }


    @AfterEach
    void tearDown() {
        temperatureRepository.deleteAll();
    }


    private void setupTemperatures() {
        long id = 1L;
        morningTemperature1
                = setupTemperature(id++, 10d,
                LocalDate.of(2021, 8, 4),
                LocalTime.of(10, 0, 0));
        afternoonTemperature1
                = setupTemperature(id++, 15d,
                LocalDate.of(2021, 8, 4),
                LocalTime.of(15, 0, 0));
        eveningTemperature1
                = setupTemperature(id++, 20d,
                LocalDate.of(2021, 8, 4),
                LocalTime.of(20, 0, 0));
        morningTemperature2
                = setupTemperature(id++, 10d,
                LocalDate.of(2021, 8, 5),
                LocalTime.of(10, 0, 0));
        afternoonTemperature2
                = setupTemperature(id++, 15d,
                LocalDate.of(2021, 8, 5),
                LocalTime.of(15, 0, 0));
        eveningTemperature2
                = setupTemperature(id++, 20d,
                LocalDate.of(2021, 8, 5),
                LocalTime.of(20, 0, 0));
    }


    private Temperature setupTemperature(Long id, Double temperature, LocalDate date, LocalTime time) {
        Temperature temperature1 = createTemperature(id, temperature, date, time);
        return temperatureRepository.save(temperature1);
    }


    @Test
    public void findOrdered_restrictedRange_shouldReturnOnlyInRange() {
        List<Temperature> temperatureList = temperatureRepository.findOrdered(LocalTime.of(10, 0), LocalTime.of(15, 0));
        assertEquals(4, temperatureList.size());
        // TODO PPR - this could be refactored out to custom assert method that would be much more readable
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(morningTemperature1.getId())));
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(afternoonTemperature1.getId())));
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(morningTemperature2.getId())));
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(afternoonTemperature2.getId())));
    }


    @Test
    public void findOrdered_restrictedRangeSpanningMidnight_morning_shouldReturnOnlyInRange() {
        List<Temperature> temperatureList = temperatureRepository.findOrdered(LocalTime.of(22, 0), LocalTime.of(12, 0));
        assertEquals(2, temperatureList.size());
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(morningTemperature1.getId())));
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(morningTemperature2.getId())));
    }


    @Test
    public void findOrdered_restrictedRangeSpanningMidnight_evening_shouldReturnOnlyInRange() {
        List<Temperature> temperatureList = temperatureRepository.findOrdered(LocalTime.of(17, 0), LocalTime.of(5, 0));
        assertEquals(2, temperatureList.size());
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(eveningTemperature1.getId())));
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(eveningTemperature2.getId())));
    }


    @Test
    public void findOrdered_wholeDay_shouldReturnAll() {
        List<Temperature> temperatureList = temperatureRepository.findOrdered(LocalTime.MIN, LocalTime.MAX);
        assertEquals(6, temperatureList.size());
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(morningTemperature1.getId())));
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(morningTemperature2.getId())));
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(afternoonTemperature1.getId())));
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(afternoonTemperature2.getId())));
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(eveningTemperature1.getId())));
        assertTrue(temperatureList.stream().anyMatch(temperature -> temperature.getId().equals(eveningTemperature2.getId())));
    }
}