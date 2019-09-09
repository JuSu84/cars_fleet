package pl.sda.projekt.cars_fleet.Services;

import com.google.common.collect.Lists;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.model.Car;
import pl.sda.projekt.cars_fleet.model.CarUnit;
import pl.sda.projekt.cars_fleet.model.Employee;
import pl.sda.projekt.cars_fleet.repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    private Employee createNewEmployee(Employee employee){
        Employee result = new Employee();
        result.setFirstName(employee.getFirstName());
        result.setLastName(employee.getLastName());

        return result;
    }

    public Employee addNewEmployee(Employee employee){
        return employeeRepository.save(createNewEmployee(employee));
    }

    public List<Employee> getAllEmployees() {
        return Lists.newArrayList(employeeRepository.findAll());
    }

    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id, Employee.class.getName());
        }
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Employee.class.getName()));
    }

    public Employee getEmployeeByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName)
                .orElseThrow(() -> new ObjectNotFoundException(lastName, Employee.class.getName()));
    }

    public Employee getEmployeeByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName)
                .orElseThrow(() -> new ObjectNotFoundException(firstName, Employee.class.getName()));
    }

    public Employee getEmployeeByFirstNameAndLastName(String firstName, String lastName) {
        return employeeRepository.findByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(() -> new ObjectNotFoundException( lastName, Employee.class.getName()));
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee foundEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Car.class.getName()));

        foundEmployee.setFirstName(employee.getFirstName());
        foundEmployee.setLastName(employee.getLastName());

        return employeeRepository.save(foundEmployee);
    }
}
