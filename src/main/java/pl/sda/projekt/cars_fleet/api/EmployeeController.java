package pl.sda.projekt.cars_fleet.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import pl.sda.projekt.cars_fleet.Services.EmployeeService;
import pl.sda.projekt.cars_fleet.model.Employee;
import pl.sda.projekt.cars_fleet.model.Role;
import pl.sda.projekt.cars_fleet.repository.RoleRepository;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/employees/")
public class EmployeeController {


    private SecurityContext securityContext;

    private EmployeeService employeeService;
    private RoleRepository roleRepository;

    public EmployeeController(EmployeeService employeeService, RoleRepository roleRepository) {
        this.employeeService = employeeService;
        this.roleRepository = roleRepository;
    }

//    public EmployeeController(EmployeeService employeeService) {
//        this.employeeService = employeeService;
//    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Employee addNewEmployee(@RequestBody Employee employee) {
        return employeeService.addNewEmployee(employee);
    }

    @PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }


    @GetMapping("/lastname{lastname}")
    public List<Employee> getEmployeeByLastName(@PathVariable("lastname") String lastName) {
        return employeeService.getEmployeeByLastName(lastName.toLowerCase());
    }

    @GetMapping("/firstname{firstname}")
    public List<Employee> getEmployeeByFirstName(@PathVariable("firstname") String firstName) {
        return employeeService.getEmployeeByFirstName(firstName.toLowerCase());
    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/update{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @PutMapping("/add_admin_role{id}")
    public Employee addAdminRole(@PathVariable("id") Long id) {
        return employeeService.setAdminRole(id);

    }

    @PutMapping("/add_user_role{id}")
    public Employee addUserRole(@PathVariable("id") Long id) {
        return employeeService.setOnlyUserRole(id);

    }

}
