package me.zeeeeeeek.backend.controllers.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.models.tasks.collections.TaskCollection;
import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.tasks.collections.dtos.CreateTasksDTO;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.services.task.TaskListService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static me.zeeeeeeek.backend.util.AuthUtil.getUserFromAuthentication;

/**
 * Controller used to manage a list of tasks using a REST API.
 */
@RestController
@RequestMapping("/api/taskLists/")
@Slf4j
@RequiredArgsConstructor
public class TaskListRestController{

    private final TaskListService taskListService;

    /**
     * Creates a new task collection, with the given owner and tasks.
     *
     * @param createTasksDTO the data transfer object for the task list
     * @return the created collection
     */
    @PostMapping
    public ResponseEntity<? extends TaskCollection> create(@RequestBody CreateTasksDTO createTasksDTO, Authentication authentication) {
        User owner = getUserFromAuthentication(authentication);
        if(owner == null) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        return ResponseEntity
                .ok(this.taskListService
                        .createAndSave(
                                createTasksDTO.getTasksAsList(),
                                owner
                        ));
    }


    @GetMapping
    public List<TaskList> getTaskListsOwnedBy(Authentication authentication) {
        User owner = getUserFromAuthentication(authentication);
        return this.taskListService
                .getAllOwnedBy(owner);
    }



    @PostMapping("{taskListId}/tasks")
    public void addTasksToTaskList(@PathVariable UUID taskListId, @RequestBody CreateTasksDTO createTasksDTO) {
        this.taskListService
                .addTasksToTaskList(taskListId, createTasksDTO);
    }

}
