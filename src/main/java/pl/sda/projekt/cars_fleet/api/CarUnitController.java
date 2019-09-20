package pl.sda.projekt.cars_fleet.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.sda.projekt.cars_fleet.Services.CarInsuranceService;
import pl.sda.projekt.cars_fleet.Services.CarServicingService;
import pl.sda.projekt.cars_fleet.Services.CarUnitService;
import pl.sda.projekt.cars_fleet.dto.CarUnitForm;
import pl.sda.projekt.cars_fleet.model.CarUnit;
import pl.sda.projekt.cars_fleet.model.Insurance;

import java.util.List;

@RestController
@RequestMapping("/units/")
public class CarUnitController {

    private CarUnitService carUnitService;

    public CarUnitController(CarUnitService carUnitService) {
        this.carUnitService = carUnitService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/addCarUnit")
    public CarUnit addNewCarUnit(@RequestBody CarUnitForm carForm) {
        return carUnitService.addNewCarUnit(carForm);
    }

    @PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllCarUnits")
    public List<CarUnit> getAllCarUnits() {
        return carUnitService.getAllCarUnits();
    }


    @PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")
    @GetMapping("/getCarUnitById{id}")
    public CarUnit getCarUnitById(@PathVariable("id") Long id) {
        return carUnitService.getCarUnitById(id);
    }


    @PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")
    @GetMapping("/getCatUnitByMark{mark}")
    public List<CarUnit> getListOfCarUnitsByMark(@PathVariable("mark") String mark) {
        return carUnitService.getListOfCarUnitsByMark(mark.toLowerCase());
    }

}
