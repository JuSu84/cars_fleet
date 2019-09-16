package pl.sda.projekt.cars_fleet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class CarUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;
    private String registration;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "insurance_id", referencedColumnName = "id")
    private Insurance insurance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carServicing_id", referencedColumnName = "id")
    private CarServicing carServicing;
    @OneToMany(mappedBy = "carUnit")
    private Set<Task> taskSet;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {

        this.car = car;
    }



    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration.toLowerCase();
    }


    public Insurance getInsurance() {
        return insurance;
    }

    public void setInsurance(Insurance insurance) {
        this.insurance = insurance;
    }

    public CarServicing getCarServicing() {
        return carServicing;
    }

    public void setCarServicing(CarServicing carServicing) {
        this.carServicing = carServicing;
    }
}
