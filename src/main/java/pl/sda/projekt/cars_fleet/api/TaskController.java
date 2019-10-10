package pl.sda.projekt.cars_fleet.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.sda.projekt.cars_fleet.Services.CarUnitService;
import pl.sda.projekt.cars_fleet.Services.TaskService;
import pl.sda.projekt.cars_fleet.model.Task;

import java.util.List;

@RestController
@RequestMapping("/tasks/")
public class TaskController {


    private TaskService taskService;
    private CarUnitService carUnitService;


    public TaskController(TaskService taskService, CarUnitService carUnitService) {
        this.taskService = taskService;
        this.carUnitService = carUnitService;
    }

    @PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")
    @GetMapping("/getAllTasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")
    @PutMapping("/addTaskToCar{id}")
    public Task addTask(@PathVariable("id") Long id, @RequestBody Task task){
     return    carUnitService.addTaskToCar(id, task);

    }

    @PreAuthorize("hasRole('ROLE_USER')or hasRole('ROLE_ADMIN')")
    @PutMapping("/addupdateTaskAsDone{id}")
    public Task updateTaskAsDone(@PathVariable("id") Long id){

        return carUnitService.updateTaskAsDone(id);
    }



}
