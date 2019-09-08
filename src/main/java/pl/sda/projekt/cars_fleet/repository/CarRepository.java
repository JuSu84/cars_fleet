package pl.sda.projekt.cars_fleet.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.projekt.cars_fleet.model.Car;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car,Long> {

    Optional<Car> findByModel( String mark);
}
