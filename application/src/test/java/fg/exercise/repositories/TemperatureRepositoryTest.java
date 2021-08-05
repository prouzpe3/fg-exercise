package fg.exercise.repositories;

import fg.exercise.models.entities.Temperature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.time.LocalTime;

import static fg.exercise.utils.TemperatureFactory.createTemperature;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class TemperatureRepositoryTest {

    @Autowired
    private TemperatureRepository temperatureRepository;


    private Temperature temperature1;
    private Temperature temperature2;


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
        temperature1
                = setupTemperature(id++, 10d,
                LocalDate.of(2021, 8, 4),
                LocalTime.of(10, 0, 0));
        temperature2
                = setupTemperature(id++, 15d,
                LocalDate.of(2021, 8, 4),
                LocalTime.of(15, 0, 0));
    }


    private Temperature setupTemperature(Long id, Double temperature, LocalDate date, LocalTime time) {
        Temperature temperature3 = createTemperature(id, temperature, date, time);
        return temperatureRepository.save(temperature3);
    }


    @Test
    public void findTemperatureByLocalDateAndLocalTime_exactDateTime_shouldReturnOne() {
        assertTrue(temperatureRepository.findTemperatureByLocalDateAndLocalTime(LocalDate.of(2021, 8, 4), LocalTime.of(10, 0, 0)).isPresent());
    }


    @Test
    public void findTemperatureByLocalDateAndLocalTime_noRecord_shouldReturnNone() {
        assertFalse(temperatureRepository.findTemperatureByLocalDateAndLocalTime(LocalDate.of(2021, 10, 4), LocalTime.of(10, 0, 0)).isPresent());
    }


    @Test
    public void save_duplicateDateTime_shouldThrow() {
        Temperature duplicateTemperature = createTemperature(null, 10d, LocalDate.of(2021, 8, 4), LocalTime.of(10, 0, 0));
        assertThrows(DataIntegrityViolationException.class, () ->
                temperatureRepository.save(duplicateTemperature));
    }


    @Test
    public void save_duplicateTime_shouldSave() {
        Temperature temperature = createTemperature(null, 10d, LocalDate.of(2021, 10, 4), LocalTime.of(10, 0, 0));
        temperatureRepository.save(temperature);
    }


    @Test
    public void save_duplicateDate_shouldSave() {
        Temperature temperature = createTemperature(null, 10d, LocalDate.of(2021, 8, 4), LocalTime.of(11, 0, 0));
        temperatureRepository.save(temperature);
    }
}