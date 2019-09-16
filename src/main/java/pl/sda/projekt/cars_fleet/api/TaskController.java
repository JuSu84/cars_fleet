package pl.sda.projekt.cars_fleet.api;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.projekt.cars_fleet.Services.TaskService;
import pl.sda.projekt.cars_fleet.model.Employee;
import pl.sda.projekt.cars_fleet.model.Task;

@RestController
@RequestMapping("/tasks/")
public class TaskController {


    private TaskService taskService;

//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    public Employee addNewTask(@RequestBody Task task) {
//        return taskService.addNewTask(task);
//    }
}
