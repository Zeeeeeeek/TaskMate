package me.zeeeeeeek.backend.controllers.task;

import me.zeeeeeeek.backend.models.tasks.collections.dtos.CreateTasksDTO;
import me.zeeeeeeek.backend.models.tasks.collections.TaskCollection;

import java.util.UUID;

/**
 * This interface defines the contract for a controller that manages a collection of tasks.
 */
public interface TaskCollectionController {

    /**
     * Creates a new task collection, with the given owner and tasks.
     * @param createTasksDTO the data transfer object for the task list
     * @param ownerUUID the UUID of the owner of the collection
     * @return the created collection
     */
    TaskCollection create(CreateTasksDTO createTasksDTO, UUID ownerUUID);
}
