package pl.sda.projekt.cars_fleet.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue
    Long id;
    private String taskName;
    private LocalDate doneDate;
    private LocalDate taskDeadline;
    private boolean done;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "carUnit_id")
    private CarUnit carUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName.toLowerCase();
    }

    public LocalDate getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(LocalDate doneDate) {
        this.doneDate = doneDate;
    }

    public LocalDate getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(LocalDate taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public CarUnit getCarUnit() {
        return carUnit;
    }

    public void setCarUnit(CarUnit carUnit) {
        this.carUnit = carUnit;
    }

    @Override
    public String toString() {
        return  taskName + '\'' +
                ", for car: " + carUnit.getCar().getMark().toUpperCase() + " "+ carUnit.getCar().getModel().toUpperCase() + "   "+ carUnit.getRegistration().toUpperCase() +
                ", until " + taskDeadline.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")) + ".";
    }
}
