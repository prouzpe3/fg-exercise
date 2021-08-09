package fg.exercise.models.entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

// TODO PPR - no documentation
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "local_date", "local_time" }) })
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // TODO PPR - double can loose precision in Java, shouldn't we be using BigDecimal?
    @Column(nullable = false)
    private Double temperature;

    // TODO PPR - why not using LocalDateTime here - by this we would avoid using two columns
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
