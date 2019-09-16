package pl.sda.projekt.cars_fleet.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.sda.projekt.cars_fleet.Services.CarUnitService;
import pl.sda.projekt.cars_fleet.model.CarUnit;
import pl.sda.projekt.cars_fleet.model.Insurance;

@RestController
@RequestMapping("/insurances/")
public class InsuranceController {

    private CarUnitService carUnitService;

    public InsuranceController(CarUnitService carUnitService) {
        this.carUnitService = carUnitService;
    }

    @PostMapping("/add_insurance{id}")
    public Insurance addInsurance(@PathVariable("id") Long id, @RequestBody Insurance insurance) {
       return carUnitService.addNewInsuranceToCar(id, insurance);

    }
}
