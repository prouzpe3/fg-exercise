package fg.exercise.repositories;

import fg.exercise.models.entities.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalTime;
import java.util.List;


public interface TemperatureRepository extends JpaRepository<Temperature, Long>, PagingAndSortingRepository<Temperature, Long> {

    @Query("select id, localDate, localTime, temperature from Temperature where :lBound <= localTime and localTime <= :uBound order by localDate")
    List<Temperature> findOrdered(LocalTime lBound, LocalTime uBound);
}
