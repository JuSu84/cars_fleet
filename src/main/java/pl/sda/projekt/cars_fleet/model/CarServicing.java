package pl.sda.projekt.cars_fleet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
public class CarServicing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date lastServiceDate;
    private int mileage;
    @JsonIgnore
    @OneToOne(mappedBy = "carServicing")
    private CarUnit carUnit;

    public CarServicing() {
    }

    public CarServicing(Date lastServiceDate, int mileage) {
        this.lastServiceDate = lastServiceDate;
        this.mileage = mileage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
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
