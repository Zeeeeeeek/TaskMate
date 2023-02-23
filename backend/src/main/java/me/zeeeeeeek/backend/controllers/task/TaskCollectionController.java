package me.zeeeeeeek.backend.controllers.task;

import me.zeeeeeeek.backend.models.tasks.collections.TaskCollection;
import me.zeeeeeeek.backend.models.tasks.elements.Task;

import java.util.UUID;

/**
 * This interface defines the contract for a controller that manages a collection of tasks.
 */
public interface TaskCollectionController {

    /**
     * Creates a new task collection, with the given owner and tasks.
     * @param ownerUUID the owner of the collection
     * @param tasks the tasks to add to the collection
     * @return the created collection
     */
    TaskCollection create(UUID ownerUUID, Task... tasks);
}
