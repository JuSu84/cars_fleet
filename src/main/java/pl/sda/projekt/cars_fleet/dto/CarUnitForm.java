package pl.sda.projekt.cars_fleet.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import pl.sda.projekt.cars_fleet.model.Car;
import pl.sda.projekt.cars_fleet.model.CarUnit;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CarUnitForm {

    private String mark;
    private String model;

    private String registration;

    private double insurancePrice;
    private Date insuranceDate;
    private int instalment;

    private Date lastServiceDate;
    private int mileage;


        public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public double getInsurancePrice() {
        return insurancePrice;
    }

    public void setInsurancePrice(double insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public Date getInsuranceDate() {
        return insuranceDate;
    }

    public void setInsuranceDate(Date insuranceDate) {
        this.insuranceDate = insuranceDate;
    }

    public int getInstalment() {
        return instalment;
    }

    public void setInstalment(int instalment) {
        this.instalment = instalment;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
