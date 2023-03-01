package me.zeeeeeeek.backend.controllers.task;

import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.models.tasks.collections.dtos.CreateTasksDTO;
import me.zeeeeeeek.backend.models.tasks.collections.TaskCollection;
import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.services.task.TaskListService;
import me.zeeeeeeek.backend.services.user.UserService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping
    public TaskCollection create(@RequestBody CreateTasksDTO createTasksDTO, @RequestParam UUID ownerUUID) {
        User owner = this.userService.getById(ownerUUID);
        return this.taskListService
                .createAndSave(createTasksDTO.getTasksAsList(), owner);
    }


    @GetMapping
    public List<TaskList> getTaskListsByOwnerId(@RequestParam UUID ownerUUID) {
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

    @GetMapping("all")
    public List<AbstractTask> getTaskList(@RequestParam UUID taskListId) {
        return this.taskListService
                .getAllTasksOfList(taskListId);
    }

    //end of temporary methods

    @PutMapping("tasks/{taskId}")
    public void setTaskIsCompleted(@PathVariable UUID taskId, @RequestParam boolean completed) {
        this.taskListService
                .setIsCompleted(taskId, completed);
    }

    @DeleteMapping("tasks/{taskId}")
    public void deleteTask(@PathVariable UUID taskId) {
        this.taskListService
                .deleteTask(taskId);
    }

    @PostMapping("{taskListId}/tasks")
    public void addTasksToTaskList(@PathVariable UUID taskListId, @RequestBody CreateTasksDTO createTasksDTO) {
        this.taskListService
                .addTasksToTaskList(taskListId, createTasksDTO);
    }
}
