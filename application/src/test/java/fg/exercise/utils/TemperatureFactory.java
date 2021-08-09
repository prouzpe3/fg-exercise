package fg.exercise.utils;

import fg.exercise.models.entities.Temperature;

import java.time.LocalDate;
import java.time.LocalTime;


public class TemperatureFactory {

    public static Temperature createNewTemperature(Double temperature, int year, int month, int day, int hour, int minute) {
        Temperature out = new Temperature();
        out.setTemperature(temperature);
        out.setLocalDate(LocalDate.of(year, month, day));
        out.setLocalTime(LocalTime.of(hour, minute, 0));
        return out;
    }

    public static Temperature createTemperature(Long id, Double temperature, LocalDate date, LocalTime time) {
        Temperature out = new Temperature();
        out.setId(id);
        out.setTemperature(temperature);
        out.setLocalDate(date);
        out.setLocalTime(time);
        return out;
    }


    public static Temperature createRandomTemperature() {
        return createTemperature((long) (Math.random() * 100), (Math.random() * 100), LocalDate.now(), LocalTime.now());
    }
}
