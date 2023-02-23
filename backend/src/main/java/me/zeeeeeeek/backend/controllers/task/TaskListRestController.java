package me.zeeeeeeek.backend.controllers.task;

import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.controllers.dtos.CreateTaskListDTO;
import me.zeeeeeeek.backend.models.tasks.collections.TaskCollection;
import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.tasks.elements.Task;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.services.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * Controller used to manage a list of tasks using a REST API.
 */
@RestController
@RequestMapping("/api/tasks/")
@Slf4j
public class TaskListRestController implements TaskCollectionController {

    private final UserService userService;

    public TaskListRestController(UserService userService) {
        this.userService = userService;
    }




    @PostMapping("test")
    public Task test(Task test) {
        return test;
    }

    @PostMapping("test2")
    public Task[] test2(@RequestBody CreateTaskListDTO test) {
        System.out.println("Lunghezza: " + test.listaDelleTasks().length);
        for (Task task : test.listaDelleTasks()) {
            System.out.println(task.getClass().getName());
        }
        System.out.println("Owner: " + test.ownerUUID());
        return test.listaDelleTasks();
    }

    /**
     * Creates a new task collection, with the given owner and tasks.
     *
     * @param createTaskListDTO the data transfer object for the task list
     * @return the created collection
     */
    @Override
    @PostMapping
    public TaskCollection create(@RequestBody CreateTaskListDTO createTaskListDTO) {
        System.out.println(createTaskListDTO.ownerUUID());
        User owner = this.userService.getById(createTaskListDTO.ownerUUID());
        //Todo implement the storage of the task collection
        System.out.println("Lista");
        List<Task> tasks = Arrays.asList(createTaskListDTO.listaDelleTasks());
        tasks.forEach(System.out::println);
        return new TaskList(tasks, owner);
    }
}
