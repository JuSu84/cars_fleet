package pl.sda.projekt.cars_fleet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sda.projekt.cars_fleet.model.Car;
import pl.sda.projekt.cars_fleet.model.Employee;
import pl.sda.projekt.cars_fleet.model.Role;
import pl.sda.projekt.cars_fleet.repository.CarRepository;
import pl.sda.projekt.cars_fleet.repository.EmployeeRepository;
import pl.sda.projekt.cars_fleet.repository.RoleRepository;

import java.util.stream.IntStream;

@Component
public class DataSetup implements CommandLineRunner {

    private CarRepository carRepository;
    private EmployeeRepository employeeRepository;
    private RoleRepository roleRepository;

    @Autowired
    public DataSetup(CarRepository carRepository, EmployeeRepository employeeRepository, RoleRepository roleRepository) {
        this.carRepository = carRepository;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
    }





    @Override
    public void run (String... args) throws Exception {
        String[] models = {"Mondeo", "Mustang", "Fiesta", "Focus"};
        String[] names = {"Tomek", "Romek", "Wojtek", "Irek"};
        String[] lastNames= {"Kowalski", "Tatarak", "Noga", "Pietruszka"};



        IntStream.range(0, 4).forEach((i) -> {
            Car car = new Car();
            car.setModel(models[i]);
            car.setMark("ford");
           // carRepository.save(car);

        });

        IntStream.range(0, 4).forEach((i) -> {


            Employee employee = new Employee();
            employee.setFirstName(names[i]);
            employee.setLastName(lastNames[i]);
           // employeeRepository.save(employee);
        });



    }
}
