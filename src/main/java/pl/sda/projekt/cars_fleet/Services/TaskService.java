package pl.sda.projekt.cars_fleet.Services;

import org.springframework.stereotype.Service;
import pl.sda.projekt.cars_fleet.model.CarUnit;
import pl.sda.projekt.cars_fleet.model.Employee;
import pl.sda.projekt.cars_fleet.model.Task;
import pl.sda.projekt.cars_fleet.repository.CarUnitRepository;
import pl.sda.projekt.cars_fleet.repository.TaskRepository;

@Service
public class TaskService {

    private TaskRepository taskRepository;
    private CarUnitRepository carUnitRepository;

    public TaskService(TaskRepository taskRepository, CarUnitRepository carUnitRepository) {
        this.taskRepository = taskRepository;
        this.carUnitRepository = carUnitRepository;
    }


//    public Task addNewTask(CarUnit carUnit) {
//        carUnitRepository.findById(carUnit.getId()).generateTask
//        return taskRepository.save(task);
//    }
}
