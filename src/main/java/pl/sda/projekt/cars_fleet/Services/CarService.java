package pl.sda.projekt.cars_fleet.Services;

import com.google.common.collect.Lists;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.model.Car;
import pl.sda.projekt.cars_fleet.repository.CarRepository;
import pl.sda.projekt.cars_fleet.repository.CarUnitRepository;

import java.util.List;

@Service
public class CarService {

    private CarRepository carRepository;
    private CarUnitRepository carUnitRepository;

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    public CarService(CarRepository carRepository, CarUnitRepository carUnitRepository) {
        this.carRepository = carRepository;
        this.carUnitRepository = carUnitRepository;
    }

    public List<Car> getAllCars() {
        logger.info("Getting list of all cars.");
        return Lists.newArrayList(carRepository.findAll());
    }

    public Car addNewCar(Car car) {
        if (getAllCars().contains(car)) {
            Car savedCar = carRepository.findByMarkAndModel(car.getMark().toLowerCase(), car.getModel().toLowerCase()).get();

            return savedCar;
        }
        return carRepository.save(car);
    }


    public Car getCarById(long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Car.class.getName()));
    }

    public Car getCarByModelAndMark(String mark, String model) {
        return carRepository.findByMarkAndModel(mark, model)
                .orElseThrow(() -> new ObjectNotFoundException(model, Car.class.getName()));
    }

    public Car updateCar(String mark, String model, Car car) {
        Car foundCar = carRepository.findByMarkAndModel(mark, model)
                .orElseThrow(() -> new ObjectNotFoundException(model, Car.class.getName()));

        foundCar.setMark(car.getMark());
        foundCar.setModel(car.getModel());

        return carRepository.save(foundCar);
    }

    public Car updateCar(Long id, Car car) {
        Car foundCar = carRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Car.class.getName()));

        foundCar.setMark(car.getMark());
        foundCar.setModel(car.getModel());

        return carRepository.save(foundCar);
    }

    public void deleteCar(Long id) {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
        } else {
            throw new ObjectNotFoundException(id, Car.class.getName());
        }
    }

}
