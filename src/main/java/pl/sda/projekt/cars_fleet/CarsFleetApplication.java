package pl.sda.projekt.cars_fleet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableJpaRepositories
@SpringBootApplication
@EnableScheduling


public class CarsFleetApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarsFleetApplication.class, args);
    }

}
