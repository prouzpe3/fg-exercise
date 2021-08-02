package fg.exercise.models.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Double temperature;

    private LocalDate localDate;

    private LocalTime localTime;


    public Temperature() {
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Double getTemperature() {
        return temperature;
    }


    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }


    public LocalDate getLocalDate() {
        return localDate;
    }


    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }


    public LocalTime getLocalTime() {
        return localTime;
    }


    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
}
