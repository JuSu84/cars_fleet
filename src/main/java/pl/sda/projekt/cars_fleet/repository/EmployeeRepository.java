package pl.sda.projekt.cars_fleet.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.sda.projekt.cars_fleet.model.Employee;

import java.util.List;
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAllByLastName(String lastName);
    List<Employee> findAllByFirstName(String firstName);
    List<Employee> findAllByFirstNameAndLastName(String firstName, String lastName);
    Employee findByLogin(String login);

}
