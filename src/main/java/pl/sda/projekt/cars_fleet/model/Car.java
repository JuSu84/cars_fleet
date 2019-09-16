package pl.sda.projekt.cars_fleet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity

public class Car {

    public Car() {
    }

    public Car(String mark, String model) {
        this.mark = mark;
        this.model = model;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    private String mark;
    private String model;
 @JsonIgnore
    @OneToMany(mappedBy = "car")
    private List<CarUnit> carUnits = new ArrayList<>();

    public Long getId() {
        return id;
    }

//    public void setId(Long id) {
//        this.id = id;
//    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark.toLowerCase();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model.toLowerCase();
    }

    public List<CarUnit> getCarUnits() {
        return carUnits;
    }

    public void setCarUnits(List<CarUnit> carUnits) {
        this.carUnits = carUnits;
    }

    public void addCarUnitToList(CarUnit carUnit){
        this.carUnits.add(carUnit);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(mark, car.mark) &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mark, model);
    }
}
