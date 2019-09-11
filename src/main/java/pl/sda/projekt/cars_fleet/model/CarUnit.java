package pl.sda.projekt.cars_fleet.model;

import javax.persistence.*;

@Entity
public class CarUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    private Car car;
    private String registration;
    @OneToOne
    private Insurance insurance;
    @OneToOne
    private CarServicing carServicing;


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
