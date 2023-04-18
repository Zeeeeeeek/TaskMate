package me.zeeeeeeek.backend.services.task;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.repositories.AbstractTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TaskService {

    private final AbstractTaskRepository abstractTaskRepository;

    /**
     * Sets the task's isCompleted field to the given value. The task must be owned by the given user.
     *
     * @param taskId    the id of the task to update
     * @param completed the new value for the isCompleted field
     * @param owner     the owner of the task
     * @throws IllegalArgumentException if the task does not exist, or if the task is not owned by the given user
     */
    public void setIsCompleted(@NonNull UUID taskId, boolean completed, @NonNull User owner) {
        AbstractTask toBeUpdated = retrieveIfOwnedBy(taskId, owner);
        toBeUpdated.setIsCompleted(completed);
        this.abstractTaskRepository.save(toBeUpdated);
    }

    /**
     * Deletes the task with the given id. The task must be owned by the given user.
     *
     * @param taskId the id of the task to delete
     * @param owner  the owner of the task
     * @throws IllegalArgumentException if the task does not exist, or if the task is not owned by the given user
     */
    public void deleteTask(@NonNull UUID taskId, @NonNull User owner) {
        AbstractTask toBeDeleted = retrieveIfOwnedBy(taskId, owner);
        this.abstractTaskRepository.delete(toBeDeleted);
    }

    /**
     * Utility method to retrieve the task with the given id if it is owned by the given user.
     *
     * @param taskId the id of the task to retrieve
     * @param owner  the user to check
     * @return the task with the given id if it is owned by the given user
     * @throws IllegalArgumentException if the task does not exist, or if the task is not owned by the given user
     */
    private AbstractTask retrieveIfOwnedBy(@NonNull UUID taskId, @NonNull User owner) {
        AbstractTask toBeReturned = this
                .abstractTaskRepository
                .findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task with id " + taskId + " does not exist"));
        if (!toBeReturned.getTaskList().getOwner().equals(owner)) {
            throw new IllegalArgumentException("Task with id " + taskId + " is not owned by user " + owner);
        }
        return toBeReturned;
    }


    public Iterable<AbstractTask> setTasksTaskList(List<AbstractTask> tasks, TaskList taskList) {
        tasks.forEach(task -> task.setTaskList(taskList));
        return this.abstractTaskRepository.saveAll(tasks);
    }
}
