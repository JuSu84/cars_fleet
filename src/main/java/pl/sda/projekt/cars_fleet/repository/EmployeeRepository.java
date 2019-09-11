package pl.sda.projekt.cars_fleet.repository;

import org.springframework.data.repository.CrudRepository;
import pl.sda.projekt.cars_fleet.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAllByLastName(String lastName);
    List<Employee> findAllByFirstName(String firstName);
    List<Employee> findAllByFirstNameAndLastName (String firstName, String lastName);

}
