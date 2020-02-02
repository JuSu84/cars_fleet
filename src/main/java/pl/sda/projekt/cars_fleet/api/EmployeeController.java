package pl.sda.projekt.cars_fleet.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;
import pl.sda.projekt.cars_fleet.Services.EmployeeService;
import pl.sda.projekt.cars_fleet.model.Employee;


import java.util.List;


@RestController
@RequestMapping("/employees/")
public class EmployeeController {


    private EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;

    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @GetMapping("/getEmployeesByLastName{lastName}")
    public List<Employee> getEmployeeByLastName(@PathVariable("lastName") String lastName) {
        return employeeService.getEmployeeByLastName(lastName.toLowerCase());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/getEmployeesByFirstName{firstName}")
    public List<Employee> getEmployeeByFirstName(@PathVariable("firstName") String firstName) {
        return employeeService.getEmployeeByFirstName(firstName.toLowerCase());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/deleteEmployeeById{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateEmployeeById{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/addAdminRoleById{id}")
    public Employee addAdminRole(@PathVariable("id") Long id) {
        return employeeService.setAdminRole(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/add_user_role{id}")
    public Employee addUserRole(@PathVariable("id") Long id) {
        return employeeService.setOnlyUserRole(id);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/addCarUnitToEmployee{carId},{empId}")
    public Employee addCarUnitToEmployee(@PathVariable("carId") Long carId, @PathVariable("empId") Long empId) {

        return employeeService.addCarUnitToEmployee(carId, empId);
    }

}
