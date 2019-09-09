package pl.sda.projekt.cars_fleet.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.projekt.cars_fleet.model.Insurance;

public interface CarInsuranceRepository extends CrudRepository<Insurance, Long> {

}
