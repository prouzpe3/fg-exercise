package fg.exercise.repositories;

import fg.exercise.models.entities.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalTime;
import java.util.List;


public interface TemperatureRepository extends JpaRepository<Temperature, Long>, PagingAndSortingRepository<Temperature, Long> {

    @Query("select t from Temperature t " +
            "where (:lBound <= :uBound and :lBound <= t.localTime and t.localTime <= :uBound) " +
                "or (:lBound > :uBound and :lBound <= t.localTime and t.localTime >= :uBound) " +
                "or (:lBound > :uBound and t.localTime <= :lBound and :uBound >= t.localTime) " +
            "order by t.localDate asc, t.localTime asc")
    List<Temperature> findOrdered(LocalTime lBound, LocalTime uBound);
}
