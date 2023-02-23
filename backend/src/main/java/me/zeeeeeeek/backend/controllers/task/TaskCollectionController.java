package me.zeeeeeeek.backend.controllers.task;

import me.zeeeeeeek.backend.controllers.dtos.CreateTaskListDTO;
import me.zeeeeeeek.backend.models.tasks.collections.TaskCollection;

/**
 * This interface defines the contract for a controller that manages a collection of tasks.
 */
public interface TaskCollectionController {

    /**
     * Creates a new task collection, with the given owner and tasks.
     * @param createTaskListDTO the data transfer object for the task list
     * @return the created collection
     */
    TaskCollection create(CreateTaskListDTO createTaskListDTO);
}
