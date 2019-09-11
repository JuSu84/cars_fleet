package pl.sda.projekt.cars_fleet.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.projekt.cars_fleet.model.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
}
