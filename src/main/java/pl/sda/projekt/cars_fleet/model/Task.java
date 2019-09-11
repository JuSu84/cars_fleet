package pl.sda.projekt.cars_fleet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Task {

    @Id
    @GeneratedValue
    Long id;
    private String taskName;
    private Date doneDate;
    private Date taskDeadline;
    private boolean done;

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

    public Date getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(Date doneDate) {
        this.doneDate = doneDate;
    }

    public Date getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(Date taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}