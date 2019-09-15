package pl.sda.projekt.cars_fleet.Services;

import com.google.common.collect.Lists;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.model.Employee;
import pl.sda.projekt.cars_fleet.model.Login;
import pl.sda.projekt.cars_fleet.model.Role;
import pl.sda.projekt.cars_fleet.repository.EmployeeRepository;
import pl.sda.projekt.cars_fleet.repository.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public Employee addNewEmployee(Employee employee){
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        Role userRole = roleRepository.findByRole("ADMIN");
        employee.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        employee.setActive(1);
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

    public Employee findByLogin(String login) {
        return employeeRepository.findByLogin(login);
    }

    public Employee updateEmployee(Long id, Employee employee) {
        Employee foundEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Employee.class.getName()));


        foundEmployee.setFirstName(employee.getFirstName());
        foundEmployee.setLastName(employee.getLastName());

        return employeeRepository.save(foundEmployee);
    }

    public Employee validateUser(Login login) {

        Employee employee= employeeRepository.findByLogin(login.getUsername()) ;

        if (bCryptPasswordEncoder.matches(login.getPassword(), employee.getPassword())) {
            return employee;
        } else return null;
    }
}
