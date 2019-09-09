package pl.sda.projekt.cars_fleet.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.projekt.cars_fleet.dto.CarForm;
import pl.sda.projekt.cars_fleet.model.Car;

import java.util.Optional;

public interface CarRepository extends CrudRepository<Car,Long> {

    Optional<Car> findByMark( String mark);

    Optional<Car> findByModel(String model);

    Optional<Car> findByMarkAndModel(String mark, String model);

  boolean existsByModel(String model);
}
