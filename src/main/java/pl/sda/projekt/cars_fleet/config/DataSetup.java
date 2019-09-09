package pl.sda.projekt.cars_fleet.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.sda.projekt.cars_fleet.model.Car;
import pl.sda.projekt.cars_fleet.repository.CarRepository;

import java.util.stream.IntStream;

import static java.lang.String.format;

@Component
public class DataSetup implements CommandLineRunner {

    private CarRepository carRepository;

    @Autowired
    public DataSetup(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void run (String... args) throws Exception {
        IntStream.range(0,4).forEach((i) -> {
            Car car = new Car();
            car.setModel(format("model%d", i));
            car.setMark(format("mark%d", i));
            carRepository.save(car);
        });
    }
}
