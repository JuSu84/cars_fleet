package pl.sda.projekt.cars_fleet.api;

import org.springframework.web.bind.annotation.*;
import pl.sda.projekt.cars_fleet.Services.CarService;
import pl.sda.projekt.cars_fleet.model.Car;

import java.util.List;

@RestController
@RequestMapping("/cars/")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public Car addNewCar(@RequestBody Car car) {
        return  carService.addNewCar(car);
    }

    @GetMapping
    public List<Car> getAllCars(){
        return carService.getAllCars();
    }

    @GetMapping("{id}")
    public Car getCarById(@PathVariable("id") Long id) {
        return carService.getCarById(id);
    }

    @PutMapping("{mark},{model}")
    public Car updateMessage(@PathVariable("mark") String mark,@PathVariable("model") String model, @RequestBody Car car) {
        return carService.updateCar(mark,model, car);
    }

    @DeleteMapping("{id}")
    public void deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
    }
//



}
