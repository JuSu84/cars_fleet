package pl.sda.projekt.cars_fleet.Services;

import com.google.common.collect.Lists;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.dto.CarUnitForm;
import pl.sda.projekt.cars_fleet.model.*;
import pl.sda.projekt.cars_fleet.repository.CarInsuranceRepository;
import pl.sda.projekt.cars_fleet.repository.CarRepository;
import pl.sda.projekt.cars_fleet.repository.CarUnitRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarUnitService {

    private CarService carService;
    private CarRepository carRepository;
    private CarUnitRepository carUnitRepository;
    private CarInsuranceService insuranceService;
    private CarServicingService carServicingService;
    private CarInsuranceRepository insuranceRepository;
    private TaskService taskService;

    private static final Logger logger = LoggerFactory.getLogger(CarService.class);


    public CarUnitService(CarService carService, CarRepository carRepository, CarUnitRepository carUnitRepository, CarInsuranceService insuranceService, CarServicingService carServicingService, CarInsuranceRepository insuranceRepository, TaskService taskService) {
        this.carService = carService;
        this.carRepository = carRepository;
        this.carUnitRepository = carUnitRepository;
        this.insuranceService = insuranceService;
        this.carServicingService = carServicingService;
        this.insuranceRepository = insuranceRepository;
        this.taskService = taskService;
    }


    private CarUnit createNewCarUnit(CarUnitForm form) {


        Car car = carService.addNewCar(new Car(form.getMark(), form.getModel()));
        CarUnit result = new CarUnit();

        result.setRegistration(form.getRegistration());

        result.setCar(car);
        result.getCar().addCarUnitToList(result);
        return result;
    }

    public CarUnit addNewCarUnit(CarUnitForm carForm) {
        return carUnitRepository.save(createNewCarUnit(carForm));
    }

    public CarUnit saveCarUnit(CarUnit carUnit) {
        return carUnitRepository.save(carUnit);
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

    public Insurance addNewInsuranceToCar(Long id, Insurance insurance) {
        CarUnit result = carUnitRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(id, CarUnit.class.getName()));
        result.setInsurance(insuranceService.addNewInsurance(insurance));
        insurance.setCarUnit(getCarUnitById(id));
        carUnitRepository.save(result);
        taskService.generateInsuranceTask(id);
        return insurance;
    }

    public CarServicing addNewCarServicingToCar(Long id, CarServicing carServicing) {
        CarUnit result = carUnitRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException(id, CarUnit.class.getName()));
        result.setCarServicing(carServicingService.addNewCarServicing(carServicing));
        carServicing.setCarUnit(getCarUnitById(id));
        carUnitRepository.save(result);
        taskService.generateCarServicingTask(id);
        return carServicing;
    }

    public Task addTaskToCar(Long id, Task task) {
        CarUnit carUnit = getCarUnitById(id);
        Task savedTask = new Task();
        savedTask.setCarUnit(carUnit);
        savedTask.setTaskDeadline(task.getTaskDeadline());
        savedTask.setDone(false);
        savedTask.setTaskName(task.getTaskName());
        carUnit.getTaskSet().add(task);

        saveCarUnit(carUnit);
        return taskService.saveTask(task);
    }

    public Task updateTaskAsDone(Long id) {
       Task doneTask = taskService.getTaskById(id);
       doneTask.setDoneDate(LocalDate.now());
       doneTask.setDone(true);
      return taskService.saveTask(doneTask);

    }
}
