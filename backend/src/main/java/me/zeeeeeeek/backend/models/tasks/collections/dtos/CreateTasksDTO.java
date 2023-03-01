package me.zeeeeeeek.backend.models.tasks.collections.dtos;

import lombok.NonNull;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;

import java.util.Arrays;
import java.util.List;

/**
 * This class represents the data transfer object for a task list.
 * @param tasks the tasks to add to the collection
 */
public record CreateTasksDTO(@NonNull AbstractTask... tasks) {
    public List<AbstractTask> getTasksAsList() {
        return Arrays.asList(tasks);
    }
}
