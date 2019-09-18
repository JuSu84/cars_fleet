package pl.sda.projekt.cars_fleet.api;

import org.springframework.web.bind.annotation.*;
import pl.sda.projekt.cars_fleet.Services.TaskService;
import pl.sda.projekt.cars_fleet.model.CarUnit;
import pl.sda.projekt.cars_fleet.model.Task;

import java.util.List;

@RestController
@RequestMapping("/tasks/")
public class TaskController {


    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }



}
