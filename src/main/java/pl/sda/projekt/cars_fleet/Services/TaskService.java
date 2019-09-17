package pl.sda.projekt.cars_fleet.Services;

import com.google.common.collect.Lists;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.model.CarUnit;
import pl.sda.projekt.cars_fleet.model.Employee;
import pl.sda.projekt.cars_fleet.model.Task;
import pl.sda.projekt.cars_fleet.repository.CarInsuranceRepository;
import pl.sda.projekt.cars_fleet.repository.CarUnitRepository;
import pl.sda.projekt.cars_fleet.repository.TaskRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private CarUnitRepository carUnitRepository;
    private CarInsuranceRepository carInsuranceRepository;

    public TaskService(TaskRepository taskRepository, CarUnitRepository carUnitRepository, CarInsuranceRepository carInsuranceRepository) {
        this.taskRepository = taskRepository;
        this.carUnitRepository = carUnitRepository;
        this.carInsuranceRepository = carInsuranceRepository;
    }

    public Task generateInsuranceTask(Long id) {
        CarUnit carUnit = carUnitRepository.findById(id) .orElseThrow(() -> new ObjectNotFoundException(id, Employee.class.getName()));
        Task insuranceTask = new Task();
        insuranceTask.setTaskName("Insurance to pay");
        insuranceTask.setDone(false);
        insuranceTask.setTaskDeadline(carUnit.getInsurance().getValidUntil());
        insuranceTask.setCarUnit(carUnit);

        Set<Task> taskSet = carUnit.getTaskSet();
        taskSet.add(insuranceTask);
        carUnit.setTaskSet(taskSet);
        carUnitRepository.save(carUnit);

        return taskRepository.save(insuranceTask);
    }

    public Task generateCarServicingTask(Long id) {
        CarUnit carUnit = carUnitRepository.findById(id) .orElseThrow(() -> new ObjectNotFoundException(id, Employee.class.getName()));
        Task carServicingTask = new Task();
        carServicingTask.setTaskName("Service to do");
        carServicingTask.setDone(false);
        carServicingTask.setTaskDeadline(carUnit.getCarServicing().getNextServiceDate());
        carServicingTask.setCarUnit(carUnit);
        Set<Task> taskSet = carUnit.getTaskSet();
        taskSet.add(carServicingTask);
        carUnit.setTaskSet(taskSet);
        carUnitRepository.save(carUnit);
        return taskRepository.save(carServicingTask);
    }

    public List<Task> getAllTasks() {
        return Lists.newArrayList(taskRepository.findAll());
    }

}
