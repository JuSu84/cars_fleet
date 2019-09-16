package pl.sda.projekt.cars_fleet.Services;


import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.model.Insurance;
import pl.sda.projekt.cars_fleet.repository.CarInsuranceRepository;

@Service
public class CarInsuranceService {

    private CarInsuranceRepository carInsuranceRepository;

    public CarInsuranceService(CarInsuranceRepository carInsuranceRepository) {
        this.carInsuranceRepository = carInsuranceRepository;
    }

    public Insurance addNewInsurance(Insurance insurance) {

     Insurance savedInsurance = carInsuranceRepository.save(new Insurance(insurance.getInsuranceDate(), insurance.getInsurancePrice(), insurance.getInstalment(), insurance.getCarUnit()));

        return savedInsurance;
    }
}
