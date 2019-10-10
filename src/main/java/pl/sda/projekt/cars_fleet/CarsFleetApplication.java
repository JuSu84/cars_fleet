package pl.sda.projekt.cars_fleet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class CarsFleetApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsFleetApplication.class, args);
    }

}
