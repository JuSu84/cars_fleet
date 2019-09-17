package pl.sda.projekt.cars_fleet.Services;

import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.model.CarServicing;
import pl.sda.projekt.cars_fleet.model.Insurance;
import pl.sda.projekt.cars_fleet.repository.CarServicingRepository;

import java.util.Date;

@Service
public class CarServicingService {

    private CarServicingRepository carServicingRepository;

    public CarServicingService(CarServicingRepository carServicingRepository) {
        this.carServicingRepository = carServicingRepository;
    }

    public CarServicing addNewCarServicing(CarServicing carServicing) {

        CarServicing savedCarServicing = carServicingRepository.save(new CarServicing(carServicing.getLastServiceDate(), carServicing.getMileage()));
        return savedCarServicing;
    }

}
