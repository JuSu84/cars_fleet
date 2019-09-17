package pl.sda.projekt.cars_fleet.api;

import org.springframework.web.bind.annotation.*;
import pl.sda.projekt.cars_fleet.Services.CarUnitService;
import pl.sda.projekt.cars_fleet.model.CarServicing;



    @RestController
    @RequestMapping("/carServicing/")
    public class CarServicingController {

        private CarUnitService carUnitService;

        public CarServicingController(CarUnitService carUnitService) {
            this.carUnitService = carUnitService;
        }

        @PostMapping("/addCarServicing{id}")
        public CarServicing addCarServicing(@PathVariable("id") Long id, @RequestBody CarServicing carServicing) {
            return carUnitService.addNewCarServicingToCar(id, carServicing);

        }
    }
