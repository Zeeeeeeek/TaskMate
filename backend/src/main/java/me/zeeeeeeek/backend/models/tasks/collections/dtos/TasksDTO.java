package me.zeeeeeeek.backend.models.tasks.collections.dtos;

import lombok.NonNull;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record TasksDTO(@NonNull AbstractTask... tasks) {

    public TasksDTO {
        Arrays.stream(tasks)
                .forEach(Objects::requireNonNull);
    }

    public List<AbstractTask> getTasksAsList() {
        return Arrays.asList(tasks);
    }
}
