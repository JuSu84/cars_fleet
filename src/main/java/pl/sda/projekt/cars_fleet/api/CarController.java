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

    @PostMapping("/addCar")
    public Car addNewCar(@RequestBody Car car) {
        return carService.addNewCar(car);
    }

    @GetMapping("/getAllCars")
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/getCarById{id}")
    public Car getCarById(@PathVariable("id") Long id) {
        return carService.getCarById(id);
    }

    @PutMapping("/updateCarById{id}")
    public Car updateMessageById(@PathVariable("id") Long id, @RequestBody Car car) {
        return carService.updateCar(id, car);
    }

    @DeleteMapping("/deleteCarById{id}")
    public void deleteCar(@PathVariable("id") Long id) {
        carService.deleteCar(id);
    }


}
