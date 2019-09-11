package pl.sda.projekt.cars_fleet.Services;

import com.google.common.collect.Lists;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.model.Car;
import pl.sda.projekt.cars_fleet.model.Employee;
import pl.sda.projekt.cars_fleet.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;


    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }


    public Employee addNewEmployee(Employee employee){
        return employeeRepository.save(employee);
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

    public List<Employee> getEmployeeByLastName(String lastName) {
       return Lists.newArrayList(employeeRepository.findAllByLastName(lastName));

    }

    public List<Employee> getEmployeeByFirstName(String firstName) {
        return Lists.newArrayList(employeeRepository.findAllByFirstName(firstName));
    }

    public List<Employee> getEmployeeByFirstNameAndLastName(String firstName, String lastName) {
        return Lists.newArrayList(employeeRepository.findAllByFirstNameAndLastName(firstName, lastName));

    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee foundEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Employee.class.getName()));

        foundEmployee.setFirstName(employee.getFirstName());
        foundEmployee.setLastName(employee.getLastName());

        return employeeRepository.save(foundEmployee);
    }
}
