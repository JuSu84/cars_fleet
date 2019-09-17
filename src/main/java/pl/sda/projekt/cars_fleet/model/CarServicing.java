package pl.sda.projekt.cars_fleet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CarServicing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date nextServiceDate;
    private int mileage;
    @JsonIgnore
    @OneToOne(mappedBy = "carServicing")
    private CarUnit carUnit;

    public CarServicing() {
    }

    public CarServicing(Date nextServiceDate, int mileage) {
        this.nextServiceDate = nextServiceDate;
        this.mileage = mileage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getNextServiceDate() {
        return nextServiceDate;
    }

    public void setNextServiceDate(Date nextServiceDate) {
        this.nextServiceDate = nextServiceDate;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public CarUnit getCarUnit() {
        return carUnit;
    }

    public void setCarUnit(CarUnit carUnit) {
        this.carUnit = carUnit;
    }
}
