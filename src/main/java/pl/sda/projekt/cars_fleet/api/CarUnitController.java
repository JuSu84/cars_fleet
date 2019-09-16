package pl.sda.projekt.cars_fleet.api;

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
    private CarInsuranceService carInsuranceService;
    private CarServicingService carServicingService;

    public CarUnitController(CarUnitService carUnitService, CarInsuranceService carInsuranceService, CarServicingService carServicingService) {
        this.carUnitService = carUnitService;
        this.carInsuranceService = carInsuranceService;
        this.carServicingService = carServicingService;
    }


    @PostMapping
    public CarUnit addNewCarUnit(@RequestBody CarUnitForm carForm) {
        return carUnitService.addNewCarUnit(carForm);
    }


    @GetMapping
    public List<CarUnit> getAllCarUnits() {
        return carUnitService.getAllCarUnits();
    }

    @GetMapping("{id}")
    public CarUnit getCarUnitById(@PathVariable("id") Long id) {
        return carUnitService.getCarUnitById(id);
    }

    @GetMapping("{mark}")
    public List<CarUnit> getListOfCarUnitsByMark(@PathVariable("mark") String mark) {
        return carUnitService.getListOfCarUnitsByMark(mark.toLowerCase());
    }


}
