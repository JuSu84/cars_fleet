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


    private EmployeeService employeeService;
    private RoleRepository roleRepository;

    public EmployeeController(EmployeeService employeeService, RoleRepository roleRepository) {
        this.employeeService = employeeService;
        this.roleRepository = roleRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addEmployee")
    public Employee addNewEmployee(@RequestBody Employee employee) {
        return employeeService.addNewEmployee(employee);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")
    @GetMapping("/getEmployeeById{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getEmployeesByLastName{lastname}")
    public List<Employee> getEmployeeByLastName(@PathVariable("lastname") String lastName) {
        return employeeService.getEmployeeByLastName(lastName.toLowerCase());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getEmployeesByFirstName{firstname}")
    public List<Employee> getEmployeeByFirstName(@PathVariable("firstname") String firstName) {
        return employeeService.getEmployeeByFirstName(firstName.toLowerCase());
    }

    @DeleteMapping("/deleteEmployeeById{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("/updateEmployeeById{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @PutMapping("/addAdminRoleById{id}")
    public Employee addAdminRole(@PathVariable("id") Long id) {
        return employeeService.setAdminRole(id);
    }

    @PutMapping("/add_user_role{id}")
    public Employee addUserRole(@PathVariable("id") Long id) {
        return employeeService.setOnlyUserRole(id);
    }

    @PutMapping("/addCarUnitToEmployee{carId},{empId}")
    public Employee addCarUnitToEmployee(@PathVariable("carId") Long carId, @PathVariable("empId") Long empId ) {

      return employeeService.addCarUnitToEmployee(carId, empId);
    }

}
