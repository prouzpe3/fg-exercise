package fg.exercise.models.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "local_date", "local_time" }) })
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Double temperature;

    @Column(name = "local_date", nullable = false)
    private LocalDate localDate;

    @Column(name = "local_time", nullable = false)
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
