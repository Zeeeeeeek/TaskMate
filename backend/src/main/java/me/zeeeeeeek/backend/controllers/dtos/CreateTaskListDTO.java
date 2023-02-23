package me.zeeeeeeek.backend.controllers.dtos;

import me.zeeeeeeek.backend.models.tasks.elements.Task;

import java.util.UUID;

/**
 * This class represents the data transfer object for a task list.
 * @param listaDelleTasks the tasks to add to the collection
 * @param ownerUUID the owner of the collection
 */
public record CreateTaskListDTO(Task[] listaDelleTasks, UUID ownerUUID) {
}
