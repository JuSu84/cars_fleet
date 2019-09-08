package pl.sda.projekt.cars_fleet.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.projekt.cars_fleet.Services.CarService;
import pl.sda.projekt.cars_fleet.model.Car;

@RestController
@RequestMapping("/carsfleet/")
public class Controller {

        private CarService carService;

    public Controller(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public Car addNewCar(@RequestBody Car car) {
        Car savedCar = carService.addNewCar(car);
        return savedCar;
    }
}
