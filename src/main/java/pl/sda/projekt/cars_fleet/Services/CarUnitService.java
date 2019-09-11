package pl.sda.projekt.cars_fleet.Services;

import com.google.common.collect.Lists;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.dto.CarUnitForm;
import pl.sda.projekt.cars_fleet.model.Car;
import pl.sda.projekt.cars_fleet.model.CarServicing;
import pl.sda.projekt.cars_fleet.model.CarUnit;
import pl.sda.projekt.cars_fleet.model.Insurance;
import pl.sda.projekt.cars_fleet.repository.CarRepository;
import pl.sda.projekt.cars_fleet.repository.CarUnitRepository;

import java.util.List;

@Service
public class CarUnitService {

    private CarService carService;
    private CarRepository carRepository;
    private CarUnitRepository carUnitRepository;
    private CarInsuranceService insuranceService;
    private CarServicingService carServicingService;

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);


    public CarUnitService(CarService carService, CarRepository carRepository, CarUnitRepository carUnitRepository, CarInsuranceService insuranceService, CarServicingService carServicingService) {
        this.carService = carService;
        this.carRepository = carRepository;
        this.carUnitRepository = carUnitRepository;
        this.insuranceService = insuranceService;
        this.carServicingService = carServicingService;
    }

    private CarUnit createNewCarUnit(CarUnitForm form) {


        Car car = carService.addNewCar(new Car(form.getMark(), form.getModel()));

        Insurance insurance = insuranceService.addNewInsurance(new Insurance(form.getInsuranceDate(),form.getInsurancePrice(),form.getInstalment()));
        CarServicing carServicing = carServicingService.addNewCarServicing(new CarServicing(form.getLastServiceDate(),form.getMileage()));
        CarUnit result = new CarUnit();

        result.setRegistration(form.getRegistration());
        result.setInsurance(insurance);
        result.setCarServicing(carServicing);

        result.setCar(car);
        result.getCar().addCarUnitToList(result);
        return result;
    }

    public CarUnit addNewCarUnit(CarUnitForm carForm) {
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