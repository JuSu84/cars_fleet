package pl.sda.projekt.cars_fleet.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.projekt.cars_fleet.model.Car;
import pl.sda.projekt.cars_fleet.model.CarUnit;

import java.util.List;

public interface CarUnitRepository extends CrudRepository<CarUnit, Long> {

    List<CarUnit> findAllByCar(Car car);
}
