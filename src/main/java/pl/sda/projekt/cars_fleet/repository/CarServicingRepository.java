package pl.sda.projekt.cars_fleet.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.projekt.cars_fleet.model.CarServicing;

public interface CarServicingRepository extends CrudRepository<CarServicing, Long> {
}
