package pl.sda.projekt.cars_fleet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sda.projekt.cars_fleet.model.Car;
import pl.sda.projekt.cars_fleet.model.Employee;
import pl.sda.projekt.cars_fleet.repository.CarRepository;
import pl.sda.projekt.cars_fleet.repository.EmployeeRepository;

import java.util.stream.IntStream;

import static java.lang.String.format;

@Component
public class DataSetup implements CommandLineRunner {

    private CarRepository carRepository;
    private EmployeeRepository employeeRepository;

    @Autowired
    public DataSetup(CarRepository carRepository, EmployeeRepository employeeRepository) {
        this.carRepository = carRepository;
        this.employeeRepository = employeeRepository;
    }


    @Override
    public void run (String... args) throws Exception {
        String[] models = {"Mondeo", "Mustang", "Fiesta", "Focus"};
        String[] names = {"Tomek", "Romek", "Wojtek", "Irek"};

        IntStream.range(0, 4).forEach((i) -> {
            Car car = new Car();
            car.setModel(models[i]);
            car.setMark("ford");
            carRepository.save(car);

        });

        IntStream.range(0, 4).forEach((i) -> {


            Employee employee = new Employee();
            employee.setFirstName(names[i]);
            employee.setLastName("Kowalski");
            employeeRepository.save(employee);
        });

    }
}
