package fg.exercise.repositories;

import fg.exercise.models.entities.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface TemperatureRepository extends JpaRepository<Temperature, Long>, PagingAndSortingRepository<Temperature, Long> {

}
