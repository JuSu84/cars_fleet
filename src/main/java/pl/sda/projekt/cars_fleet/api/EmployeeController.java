package pl.sda.projekt.cars_fleet.api;

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

    @PostMapping
    public Employee addNewEmployee(@RequestBody Employee employee){
        return employeeService.addNewEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("{id}")
    public Employee getEmployeeUnitById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }

//    @GetMapping("{lastname}")
//    public Employee getEmployeeByLastName(@PathVariable("lastname") String lastName) {
//        return employeeService.getEmployeeByLastName(lastName);
//    }
//
//    @GetMapping("{firstname}")
//    public Employee getEmployeeByFirstName(@PathVariable("firstname") String firstName) {
//        return employeeService.getEmployeeByFirstName(firstName);
//    }

    @DeleteMapping("{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @PutMapping("{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(id, employee);
    }

}
