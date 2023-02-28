package me.zeeeeeeek.backend.controllers.task;

import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.controllers.dtos.CreateTaskListDTO;
import me.zeeeeeeek.backend.models.tasks.collections.TaskCollection;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.services.task.TaskListService;
import me.zeeeeeeek.backend.services.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller used to manage a list of tasks using a REST API.
 */
@RestController
@RequestMapping("/api/tasks")
@Slf4j
public class TaskListRestController implements TaskCollectionController {

    private final UserService userService;
    private final TaskListService taskListService;

    public TaskListRestController(UserService userService, TaskListService taskListService) {
        this.userService = userService;
        this.taskListService = taskListService;
    }

    /**
     * Creates a new task collection, with the given owner and tasks.
     *
     * @param createTaskListDTO the data transfer object for the task list
     * @return the created collection
     */
    @Override
    @PostMapping("/")
    public TaskCollection create(@RequestBody CreateTaskListDTO createTaskListDTO, @RequestParam UUID ownerUUID) {
        System.out.println(ownerUUID);
        User owner = this.userService.getById(ownerUUID);
        return this.taskListService
                .createAndSave(createTaskListDTO.getTasksAsList(), owner);
    }


    @GetMapping("/")
    public TaskCollection get(@RequestParam UUID ownerUUID) {
        User owner = this.userService.getById(ownerUUID);
        TaskCollection taskCollection = this.taskListService.getByOwner(owner);
        System.out.println(taskCollection);
        return taskCollection;
    }
}
