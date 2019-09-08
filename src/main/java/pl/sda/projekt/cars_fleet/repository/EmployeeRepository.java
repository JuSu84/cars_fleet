package pl.sda.projekt.cars_fleet.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.projekt.cars_fleet.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
