package pl.sda.projekt.cars_fleet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@Entity
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private LocalDate validUntil;
    private double insurancePrice;
    private int instalment;
    @JsonIgnore
    @OneToOne(mappedBy = "insurance")
    private CarUnit carUnit;

    public Insurance() {
    }

    public Insurance(LocalDate validUntil, double insurancePrice, int instalment, CarUnit carUnit) {
        this.validUntil = validUntil;
        this.insurancePrice = insurancePrice;
        this.instalment = instalment;
        this.carUnit = carUnit;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public double getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(double insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public int getInstalment() {
        return instalment;
    }

    public void setInstalment(int instalment) {
        this.instalment = instalment;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        this.validUntil = validUntil;
    }

    public CarUnit getCarUnit() {
        return carUnit;
    }

    public void setCarUnit(CarUnit carUnit) {
        this.carUnit = carUnit;
    }
}
