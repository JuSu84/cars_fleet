package pl.sda.projekt.cars_fleet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sda.projekt.cars_fleet.Services.EmployeeService;
import pl.sda.projekt.cars_fleet.Services.EmailServiceImpl;
import pl.sda.projekt.cars_fleet.repository.CarRepository;
import pl.sda.projekt.cars_fleet.repository.EmployeeRepository;
import pl.sda.projekt.cars_fleet.repository.RoleRepository;
import pl.sda.projekt.cars_fleet.repository.TaskRepository;

@Component
public class DataSetup implements CommandLineRunner {



    @Autowired
    private EmailServiceImpl emailServiceImpl;
    private CarRepository carRepository;
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;
    private EmployeeService employeeService;
    private TaskRepository taskRepository;

    @Autowired
    public DataSetup(CarRepository carRepository, EmployeeRepository employeeRepository, RoleRepository roleRepository, EmployeeService employeeService, TaskRepository taskRepository) {
        this.carRepository = carRepository;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.employeeService = employeeService;
        this.taskRepository = taskRepository;
    }



    @Override
    public void run (String... args) throws Exception {


    }
}
