package pl.sda.projekt.cars_fleet.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.projekt.cars_fleet.model.Tasks;

public interface TaskRepository extends CrudRepository<Tasks, Long> {
}
