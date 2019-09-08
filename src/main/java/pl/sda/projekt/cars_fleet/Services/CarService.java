package pl.sda.projekt.cars_fleet.Services;

import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.model.Car;
import pl.sda.projekt.cars_fleet.repository.CarRepository;

@Service
public class CarService {

    private CarRepository carRepository;


    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addNewCar(Car car) {
        return carRepository.save(car);
    }
}
