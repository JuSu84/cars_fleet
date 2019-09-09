package pl.sda.projekt.cars_fleet.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.projekt.cars_fleet.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Optional<Employee> findByLastName(String lastName);
    Optional<Employee> findByFirstName(String firstName);
    Optional<Employee> findByFirstNameAndLastName (String firstName, String lastName);

}
