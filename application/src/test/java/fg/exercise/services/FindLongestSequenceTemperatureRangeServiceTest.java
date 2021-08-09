package fg.exercise.services;

import fg.exercise.exceptions.NoTemperatureRangeFoundException;
import fg.exercise.models.entities.Temperature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

import static fg.exercise.utils.TemperatureFactory.createNewTemperature;
import static fg.exercise.utils.TemperatureFactory.createTemperature;
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
        firstOfSequence = createTemperature(null, 15d, LocalDate.of(2020, 1, 1), LocalTime.of(0, 0, 0));
        lastOfSequence = createTemperature(null, 12d, LocalDate.of(2025, 1, 1), LocalTime.of(0, 0, 0));
        temperatureList.add(0, firstOfSequence);
        temperatureList.add(1, lastOfSequence);
    }


    @Test
    void findLongestSequence_noVolatileList_shouldReturnCorrectSequence() {
        final List<Temperature> temperatureList = createNonVolatileTemperatureListFor(20);

        assertSequenceIs(
            LocalDateTime.of(2020, 1, 1, 10, 0, 0),
            LocalDateTime.of(2020, 1, 20, 17, 0, 0),
            temperatureRangeService.findLongestSequence(temperatureList, 15d, 28d)
        );
    }

    @Test
    void findLongestSequence_highVolatileList_shouldReturnCorrectSequence() {
        final List<Temperature> temperatureList = createNonVolatileTemperatureListFor(20);
        temperatureList.add(createNewTemperature(40d, 2020, 1, 5, 12, 15));
        temperatureList.add(createNewTemperature(40d, 2020, 1, 15, 12, 15));
        temperatureList.sort(Comparator.comparing(Temperature::getLocalDate).thenComparing(Temperature::getLocalTime));

        assertSequenceIs(
            LocalDateTime.of(2020, 1, 5, 12, 30, 0),
            LocalDateTime.of(2020, 1, 15, 12, 0, 0),
            temperatureRangeService.findLongestSequence(temperatureList, 15d, 28d)
        );
    }

    @Test
    void findLongestSequence_longestAtStartOfList_shouldReturnCorrectSequence() {
        temperatureList.add(1, createTemperature(null, 11d, LocalDate.of(2024, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(3, createTemperature(null, 27d, LocalDate.of(2026, 1, 1), LocalTime.of(0, 0, 0)));
        List<Temperature> longestSequence = temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound);
        assertEquals(firstOfSequence, longestSequence.get(0));
        assertEquals(lastOfSequence, longestSequence.get(1));
    }


    @Test
    void findLongestSequence_longestAtEndOfList_shouldReturnCorrectSequence() {
        temperatureList.add(0, createTemperature(null, 27d, LocalDate.of(2019, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(2, createTemperature(null, 11d, LocalDate.of(2024, 1, 1), LocalTime.of(0, 0, 0)));
        List<Temperature> longestSequence = temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound);
        assertEquals(firstOfSequence, longestSequence.get(0));
        assertEquals(lastOfSequence, longestSequence.get(1));
    }


    @Test
    void findLongestSequence_longestAtMiddleOfList_shouldReturnCorrectSequence() {
        temperatureList.add(0, createTemperature(null, 27d, LocalDate.of(2019, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(2, createTemperature(null, 11d, LocalDate.of(2024, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(4, createTemperature(null, 27d, LocalDate.of(2026, 1, 1), LocalTime.of(0, 0, 0)));
        List<Temperature> longestSequence = temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound);
        assertEquals(firstOfSequence, longestSequence.get(0));
        assertEquals(lastOfSequence, longestSequence.get(1));
    }


    @Test
    void findLongestSequence_longestAtMiddleOfList_secondSequence_shouldReturnCorrectSequence() {
        temperatureList.add(0, createTemperature(null, 15d, LocalDate.of(2016, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(1, createTemperature(null, 14d, LocalDate.of(2017, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(2, createTemperature(null, 13d, LocalDate.of(2018, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(3, createTemperature(null, 2d, LocalDate.of(2019, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(createTemperature(null, 27d, LocalDate.of(2026, 1, 1), LocalTime.of(0, 0, 0)));
        List<Temperature> longestSequence = temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound);
        System.out.println(longestSequence.get(0).getLocalDate().getYear());
        assertEquals(firstOfSequence, longestSequence.get(0));
        assertEquals(lastOfSequence, longestSequence.get(1));
    }


    @Test
    void findLongestSequence_longestAtMiddleOfList_thirdSequence_shouldReturnCorrectSequence() {
        temperatureList.add(0, createTemperature(null, 15d, LocalDate.of(2012, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(1, createTemperature(null, 14d, LocalDate.of(2013, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(2, createTemperature(null, 39d, LocalDate.of(2015, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(3, createTemperature(null, 15d, LocalDate.of(2016, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(4, createTemperature(null, 14d, LocalDate.of(2017, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(5, createTemperature(null, 13d, LocalDate.of(2018, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(6, createTemperature(null, 2d, LocalDate.of(2019, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(createTemperature(null, 27d, LocalDate.of(2026, 1, 1), LocalTime.of(0, 0, 0)));
        List<Temperature> longestSequence = temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound);
        System.out.println(longestSequence.get(0).getLocalDate().getYear());
        assertEquals(firstOfSequence, longestSequence.get(0));
        assertEquals(lastOfSequence, longestSequence.get(1));
    }


    @Test
    void findLongestSequence_oneItem_shouldThrow() {
        temperatureList.clear();
        temperatureList.add(createTemperature(null, 12d, LocalDate.of(2024, 1, 1), LocalTime.of(0, 0, 0)));
        assertThrows(NoTemperatureRangeFoundException.class, () ->
                temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound));
    }


    @Test
    void findLongestSequence_noSequence_shouldThrow() {
        temperatureList.clear();
        temperatureList.add(createTemperature(null, 8d, LocalDate.of(2024, 1, 1), LocalTime.of(0, 0, 0)));
        temperatureList.add(createTemperature(null, 27d, LocalDate.of(2026, 1, 1), LocalTime.of(0, 0, 0)));
        assertThrows(NoTemperatureRangeFoundException.class, () ->
                temperatureRangeService.findLongestSequence(temperatureList, lowerBound, upperBound));
    }

    private static List<Temperature> createNonVolatileTemperatureListFor(int days) {
        final List<Temperature> temperatureList = new ArrayList<>(days * 20);
        for (int i = 0; i < days; i++) {
            temperatureList.add(createNewTemperature(20d, 2020, 1, i + 1, 10, 0));
            temperatureList.add(createNewTemperature(21d, 2020, 1, i + 1, 10, 30));
            temperatureList.add(createNewTemperature(22d, 2020, 1, i + 1, 11, 0));
            temperatureList.add(createNewTemperature(23d, 2020, 1, i + 1, 11, 30));
            temperatureList.add(createNewTemperature(24d, 2020, 1, i + 1, 12, 0));
            temperatureList.add(createNewTemperature(25d, 2020, 1, i + 1, 12, 30));
            temperatureList.add(createNewTemperature(26d, 2020, 1, i + 1, 13, 0));
            temperatureList.add(createNewTemperature(26.5d, 2020, 1, i + 1, 13, 30));
            temperatureList.add(createNewTemperature(26d, 2020, 1, i + 1, 14, 0));
            temperatureList.add(createNewTemperature(25.5d, 2020, 1, i + 1, 14, 30));
            temperatureList.add(createNewTemperature(24d, 2020, 1, i + 1, 15, 0));
            temperatureList.add(createNewTemperature(23d, 2020, 1, i + 1, 15, 30));
            temperatureList.add(createNewTemperature(22d, 2020, 1, i + 1, 16, 0));
            temperatureList.add(createNewTemperature(21d, 2020, 1, i + 1, 16, 30));
            temperatureList.add(createNewTemperature(20d, 2020, 1, i + 1, 17, 0));
        }
        return temperatureList;
    }

    private static void assertSequenceIs(LocalDateTime startDateAndTime, LocalDateTime endDateAndTime, List<Temperature> longestSequence) {
        final LocalDateTime actualStartDateTime = longestSequence.get(0).getLocalDate().atTime(longestSequence.get(0).getLocalTime());
        assertEquals(startDateAndTime, actualStartDateTime, "Start date and time is expected to be " + startDateAndTime + " but is " + actualStartDateTime);
        final LocalDateTime actualEndDateTime = longestSequence.get(longestSequence.size() - 1).getLocalDate().atTime(longestSequence.get(longestSequence.size() - 1).getLocalTime());
        assertEquals(endDateAndTime, actualEndDateTime, "End date and time is expected to be " + endDateAndTime + " but is " + actualEndDateTime);
    }

}
