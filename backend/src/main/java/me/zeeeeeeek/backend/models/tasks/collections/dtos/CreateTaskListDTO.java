package me.zeeeeeeek.backend.models.tasks.collections.dtos;

import lombok.NonNull;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;

import java.util.List;

/**
 * This class represents the data transfer object for a task list.
 * @param tasks the tasks to add to the collection
 * @param name the name of the collection
 */
public record CreateTaskListDTO(@NonNull String name, @NonNull TasksDTO tasksDTO) {
    public List<AbstractTask> getTasksAsList() {
        return tasksDTO.getTasksAsList();
    }
}
