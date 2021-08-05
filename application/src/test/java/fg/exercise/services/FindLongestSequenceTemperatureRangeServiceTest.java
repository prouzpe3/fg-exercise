package fg.exercise.services;

import fg.exercise.exceptions.NoTemperatureRangeFoundException;
import fg.exercise.models.entities.Temperature;
import fg.exercise.utils.TemperatureFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class FindLongestSequenceTemperatureRangeServiceTest {

    private TemperatureRangeServiceImpl temperatureRangeService = new TemperatureRangeServiceImpl(null);


    private final Double lowerBound = 10d, upperBound = 20d;
    private Vector<Temperature> temperatureList;
    private Temperature firstOfSequence;
    private Temperature lastOfSequence;


    @BeforeEach
    void setUp() {
        temperatureList = new Vector<>();
        firstOfSequence = TemperatureFactory.createTemperature(null, 15d, LocalDate.of(2020, 1, 1), LocalTime.of(0, 0, 0));
        lastOfSequence = TemperatureFactory.createTemperature(null, 12d, LocalDate.of(2025, 1, 1), LocalTime.of(0, 0, 0));
        temperatureList.add(0, firstOfSequence);
        temperatureList.add(1, lastOfSequence);
    }


    @Test
    void findLongestSequence_longestAtStartOfList_shouldReturnCorrectSequence() {
        temperatureList.add(1, TemperatureFactory.createTemperature(null, 11d, LocalDate.of(2024, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(3, TemperatureFactory.createTemperature(null, 27d, LocalDate.of(2026, 1, 1), LocalTime.of(0, 0, 0)));
        List<Temperature> longestSequence = temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound);
        assertEquals(firstOfSequence, longestSequence.get(0));
        assertEquals(lastOfSequence, longestSequence.get(1));
    }


    @Test
    void findLongestSequence_longestAtEndOfList_shouldReturnCorrectSequence() {
        temperatureList.add(0, TemperatureFactory.createTemperature(null, 27d, LocalDate.of(2019, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(2, TemperatureFactory.createTemperature(null, 11d, LocalDate.of(2024, 1, 1), LocalTime.of(0, 0, 0)));
        List<Temperature> longestSequence = temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound);
        assertEquals(firstOfSequence, longestSequence.get(0));
        assertEquals(lastOfSequence, longestSequence.get(1));
    }


    @Test
    void findLongestSequence_longestAtMiddleOfList_shouldReturnCorrectSequence() {
        temperatureList.add(0, TemperatureFactory.createTemperature(null, 27d, LocalDate.of(2019, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(2, TemperatureFactory.createTemperature(null, 11d, LocalDate.of(2024, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(4, TemperatureFactory.createTemperature(null, 27d, LocalDate.of(2026, 1, 1), LocalTime.of(0, 0, 0)));
        List<Temperature> longestSequence = temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound);
        assertEquals(firstOfSequence, longestSequence.get(0));
        assertEquals(lastOfSequence, longestSequence.get(1));
    }


    @Test
    void findLongestSequence_longestAtMiddleOfList_secondSequence_shouldReturnCorrectSequence() {
        temperatureList.add(0, TemperatureFactory.createTemperature(null, 15d, LocalDate.of(2016, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(1, TemperatureFactory.createTemperature(null, 14d, LocalDate.of(2017, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(2, TemperatureFactory.createTemperature(null, 13d, LocalDate.of(2018, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(3, TemperatureFactory.createTemperature(null, 2d, LocalDate.of(2019, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(TemperatureFactory.createTemperature(null, 27d, LocalDate.of(2026, 1, 1), LocalTime.of(0, 0, 0)));
        List<Temperature> longestSequence = temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound);
        System.out.println(longestSequence.get(0).getLocalDate().getYear());
        assertEquals(firstOfSequence, longestSequence.get(0));
        assertEquals(lastOfSequence, longestSequence.get(1));
    }


    @Test
    void findLongestSequence_longestAtMiddleOfList_thirdSequence_shouldReturnCorrectSequence() {
        temperatureList.add(0, TemperatureFactory.createTemperature(null, 15d, LocalDate.of(2012, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(1, TemperatureFactory.createTemperature(null, 14d, LocalDate.of(2013, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(2, TemperatureFactory.createTemperature(null, 39d, LocalDate.of(2015, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(3, TemperatureFactory.createTemperature(null, 15d, LocalDate.of(2016, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(4, TemperatureFactory.createTemperature(null, 14d, LocalDate.of(2017, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(5, TemperatureFactory.createTemperature(null, 13d, LocalDate.of(2018, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(6, TemperatureFactory.createTemperature(null, 2d, LocalDate.of(2019, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(TemperatureFactory.createTemperature(null, 27d, LocalDate.of(2026, 1, 1), LocalTime.of(0, 0, 0)));
        List<Temperature> longestSequence = temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound);
        System.out.println(longestSequence.get(0).getLocalDate().getYear());
        assertEquals(firstOfSequence, longestSequence.get(0));
        assertEquals(lastOfSequence, longestSequence.get(1));
    }


    @Test
    void findLongestSequence_oneItem_shouldThrow() {
        temperatureList.clear();
        temperatureList.add(TemperatureFactory.createTemperature(null, 12d, LocalDate.of(2024, 1, 1), LocalTime.of(0, 0, 0)));
        assertThrows(NoTemperatureRangeFoundException.class, () ->
                temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound));
    }


    @Test
    void findLongestSequence_noSequence_shouldThrow() {
        temperatureList.clear();
        temperatureList.add(TemperatureFactory.createTemperature(null, 8d, LocalDate.of(2024, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(TemperatureFactory.createTemperature(null, 27d, LocalDate.of(2026, 1, 1), LocalTime.of(0, 0, 0)));
        assertThrows(NoTemperatureRangeFoundException.class, () ->
                temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound));
    }
}
