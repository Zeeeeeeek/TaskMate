package me.zeeeeeeek.backend.controllers.task;

import me.zeeeeeeek.backend.models.tasks.collections.TaskCollection;
import me.zeeeeeeek.backend.models.tasks.elements.Task;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controller used to manage a list of tasks using a REST API.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskListRestController implements TaskCollectionController{

    /**
     * Creates a new task collection, with the given owner and tasks.
     *
     * @param ownerUUID the owner of the collection
     * @param tasks     the tasks to add to the collection
     * @return the created collection
     */
    @Override
    public TaskCollection create(UUID ownerUUID, Task... tasks) {
        return null;
    }
}
