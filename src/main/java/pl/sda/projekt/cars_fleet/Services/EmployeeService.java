package pl.sda.projekt.cars_fleet.Services;

import com.google.common.collect.Lists;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.model.*;
import pl.sda.projekt.cars_fleet.repository.CarUnitRepository;
import pl.sda.projekt.cars_fleet.repository.EmployeeRepository;
import pl.sda.projekt.cars_fleet.repository.RoleRepository;

import java.util.*;

@Service
public class EmployeeService {


    @Autowired
    private EmailServiceImpl emailServiceImpl;
    private EmployeeRepository employeeRepository;
    private CarUnitRepository carUnitRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EmployeeService(EmailServiceImpl emailServiceImpl, EmployeeRepository employeeRepository, CarUnitRepository carUnitRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.emailServiceImpl = emailServiceImpl;
        this.employeeRepository = employeeRepository;
        this.carUnitRepository = carUnitRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }





    public Employee addNewEmployee(Employee employee) {

        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        Role userRole = roleRepository.findByRole("ROLE_USER");
        userRole.setEmployees(new HashSet<Employee>(Arrays.asList(employee)));
        employee.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        employee.setActive(1);
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return Lists.newArrayList(employeeRepository.findAll());
    }

    public void deleteEmployee(Long id) {
        if (employeeRepository.existsById(id)) {

           Employee employee =  employeeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id, Employee.class.getName()));
                    employee.getRoles().clear();
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

        foundEmployee.setLogin(employee.getLogin());
        foundEmployee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        foundEmployee.setFirstName(employee.getFirstName());
        foundEmployee.setLastName(employee.getLastName());
        foundEmployee.setRoles(employee.getRoles());

        return employeeRepository.save(foundEmployee);
    }

    public Employee validateUser(Login login) {

        Employee employee = employeeRepository.findByLogin(login.getUsername());

        if (bCryptPasswordEncoder.matches(login.getPassword(), employee.getPassword())) {
            return employee;
        } else return null;
    }


    public Employee setAdminRole(Long id) {
        Employee foundEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Employee.class.getName()));

        foundEmployee.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRole("ROLE_ADMIN"))));

        return employeeRepository.save(foundEmployee);
    }

    public Employee setOnlyUserRole(Long id) {
        Employee foundEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Employee.class.getName()));

        foundEmployee.setRoles(new HashSet<Role>(Arrays.asList(roleRepository.findByRole("ROLE_USER"))));

        return employeeRepository.save(foundEmployee);
    }

    public Employee addCarUnitToEmployee(Long carId, Long empId) {
        Employee foundEmployee = employeeRepository.findById(empId)
                .orElseThrow(() -> new ObjectNotFoundException(empId, Employee.class.getName()));

        CarUnit foundCarUnit = carUnitRepository.findById(carId)
                .orElseThrow(() -> new ObjectNotFoundException(carId, Employee.class.getName()));

        foundEmployee.setCarUnit(foundCarUnit);

        foundCarUnit.setEmployee(foundEmployee);
        carUnitRepository.save(foundCarUnit);
        for (Task task: foundCarUnit.getTaskSet()) {
            emailServiceImpl.sendMail(foundEmployee, task);
        }

      return    employeeRepository.save(foundEmployee);

    }
}
