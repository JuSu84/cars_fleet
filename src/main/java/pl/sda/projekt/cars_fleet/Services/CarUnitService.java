package pl.sda.projekt.cars_fleet.Services;

import com.google.common.collect.Lists;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.dto.CarForm;
import pl.sda.projekt.cars_fleet.model.Car;
import pl.sda.projekt.cars_fleet.model.CarUnit;
import pl.sda.projekt.cars_fleet.repository.CarRepository;
import pl.sda.projekt.cars_fleet.repository.CarUnitRepository;

import java.util.List;

@Service
public class CarUnitService {

    private CarService carService;
    private CarRepository carRepository;
    private CarUnitRepository carUnitRepository;

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);

    public CarUnitService(CarService carService, CarRepository carRepository, CarUnitRepository carUnitRepository) {
        this.carService = carService;
        this.carRepository = carRepository;
        this.carUnitRepository = carUnitRepository;
    }

    private CarUnit createNewCarUnit(CarForm form) {

        Car car = carService.addNewCar(new Car(form.getMark(), form.getModel()));

        CarUnit result = new CarUnit();
        result.setCar(car);
        result.setRegistration(form.getRegistration());
        result.setMileage(form.getMileage());
        result.setLastServiceDate(form.getLastServiceDate());
        result.setInstalment(form.getInstalment());

        return result;
    }

    public CarUnit addNewCarUnit(CarForm carForm) {
        return carUnitRepository.save(createNewCarUnit(carForm));
    }


    public List<CarUnit> getAllCarUnits() {
        return Lists.newArrayList(carUnitRepository.findAll());
    }

    public CarUnit getCarUnitById(Long id) {
        return carUnitRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(id, CarUnit.class.getName()));
    }

    public List<CarUnit> getListOfCarUnitsByMark(String mark) {
        Car car = carRepository.findByMark(mark).orElseThrow(()-> new ObjectNotFoundException(mark, CarUnit.class.getName()));
        return  carUnitRepository.findAllByCar(car);
    }
}
