package me.zeeeeeeek.backend.controllers.task;

import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.models.tasks.collections.dtos.CreateTasksDTO;
import me.zeeeeeeek.backend.models.tasks.collections.TaskCollection;
import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.services.task.TaskListService;
import me.zeeeeeeek.backend.services.user.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Controller used to manage a list of tasks using a REST API.
 */
@RestController
@RequestMapping("/api/taskLists/")
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
     * @param createTasksDTO the data transfer object for the task list
     * @return the created collection
     */
    @Override
    @PostMapping("{ownerUUID}")
    public TaskCollection create(@RequestBody CreateTasksDTO createTasksDTO, @PathVariable UUID ownerUUID) {
        User owner = this.userService.getById(ownerUUID);
        return this.taskListService
                .createAndSave(createTasksDTO.getTasksAsList(), owner);
    }


    @GetMapping("{ownerUUID}")
    public List<TaskList> getTaskListsByOwnerId(@PathVariable UUID ownerUUID) {
        User owner = this.userService.getById(ownerUUID);
        return this.taskListService
                .getAllOwnedBy(owner);
    }

    //Todo: remove temporary getAllTasks method
    @GetMapping("tasks")
    public List<AbstractTask> getAllTasks() {
        return this.taskListService
                .getAllTasks();
    }

    @GetMapping("")
    public List<AbstractTask> getTaskList(@RequestParam UUID taskListId) {
        return this.taskListService
                .getAllTasksOfList(taskListId);
    }

    //end of temporary methods

    @PutMapping("tasks")
    public void setTaskIsCompleted(@RequestParam UUID taskId, @RequestParam boolean completed) {
        this.taskListService
                .setIsCompleted(taskId, completed);
    }

    @DeleteMapping("tasks")
    public void deleteTask(@RequestParam UUID taskId) {
        this.taskListService
                .deleteTask(taskId);
    }

    @PostMapping("tasks")
    public void addTasksToTaskList(@RequestParam UUID taskListId, @RequestBody CreateTasksDTO createTasksDTO) {
        this.taskListService
                .addTasksToTaskList(taskListId, createTasksDTO);
    }
}
