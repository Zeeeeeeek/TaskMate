package me.zeeeeeeek.backend.controllers.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.tasks.collections.dtos.CreateTaskListDTO;
import me.zeeeeeeek.backend.models.tasks.collections.dtos.TasksDTO;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.services.task.TaskListService;
import me.zeeeeeeek.backend.utils.AuthUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;


/**
 * Controller used to manage a list of tasks using a REST API.
 */
@RestController
@RequestMapping("/api/v1/task-lists")
@Slf4j
@RequiredArgsConstructor
public class TaskListRestController{

    private final TaskListService taskListService;
    private final AuthUtil authUtil;

    /**
     * Creates a new task collection, with the given owner and tasks.
     *
     * @param createTaskListDTO the data transfer object for the task list
     * @return the created collection
     */
    @PostMapping
    public ResponseEntity<TaskList> create(@RequestBody CreateTaskListDTO createTaskListDTO, Authentication authentication) {
        User owner = authUtil.getUserFromAuthentication(authentication);
        if(owner == null) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        return ResponseEntity
                .ok(this.taskListService
                        .createAndSave(
                                createTaskListDTO.getTasksAsList(),
                                owner,
                                createTaskListDTO.name()
                        ));
    }


    @GetMapping
    public ResponseEntity<List<TaskList>> getTaskListsOwnedBy(Authentication authentication) {
        User owner = authUtil.getUserFromAuthentication(authentication);
        if(owner == null) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        return ResponseEntity
                .ok(this.taskListService
                        .getAllOwnedBy(owner));
    }



    @PostMapping("/{taskListId}/tasks")
    public List<AbstractTask> addTasksToTaskList(@PathVariable UUID taskListId, @RequestBody TasksDTO tasksDTO, Authentication authentication) {
        User owner = authUtil.getUserFromAuthentication(authentication);
        return this.taskListService
                .addTasksToTaskList(taskListId, tasksDTO, owner);
    }

    @DeleteMapping("/{taskListId}")
    public void deleteTaskList(@PathVariable UUID taskListId, Authentication authentication) {
        User owner = authUtil.getUserFromAuthentication(authentication);
        this.taskListService
                .deleteTaskList(taskListId, owner);
    }

    @PutMapping("/{taskListId}")
    public void updateTaskListName(@PathVariable UUID taskListId, @RequestBody String name, Authentication authentication) {
        User owner = authUtil.getUserFromAuthentication(authentication);
        this.taskListService
                .updateTaskListName(taskListId, name, owner);
    }
}