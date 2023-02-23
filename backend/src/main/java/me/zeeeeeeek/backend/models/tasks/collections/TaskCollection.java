package me.zeeeeeeek.backend.models.tasks.collections;


import me.zeeeeeeek.backend.models.tasks.elements.Task;
import me.zeeeeeeek.backend.models.user.User;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * A task collection represents a group of tasks.
 */
public interface TaskCollection {

    /**
     * Controls if all the contained tasks are completed or not
     *
     * @return true, if all {@link Task#isCompleted()} calls in each contained task returns true, false otherwise
     */
    boolean isCompleted();

    /**
     * Set the given task as completed.
     * If the task is already completed, nothing happens.
     * If the task is not contained in the collection, nothing happens.
     *
     * @param task the task to set as completed
     * @throws NullPointerException if the task is null
     */
    void setCompleted(Task task);

    /**
     * Set the given task as uncompleted.
     * If the task is already uncompleted, nothing happens.
     * If the task is not contained in the collection, nothing happens.
     *
     * @param task the task to set as uncompleted
     * @throws NullPointerException if the task is null
     */
    void setUncompleted(Task task);

    /**
     * Controls if the given task is contained in the collection.
     *
     * @param task the task to check
     * @return true if the task is contained in the collection, false otherwise
     * @throws NullPointerException if the task is null
     */
    boolean contains(Task task);

    /**
     * Add a task to the collection.
     *
     * @param task the task to add
     * @return true if the task was added, false otherwise
     * @throws NullPointerException if the task is null
     */
    boolean addTask(Task task);

    /**
     * Remove a task from the collection.
     *
     * @param task the task to remove
     * @return true if the task was removed, false otherwise
     * @throws NullPointerException if the task is null
     */
    boolean removeTask(Task task);

    /**
     * Get a stream of the tasks in the collection.
     *
     * @return a stream of the tasks in the collection
     */
    Stream<Task> stream();

    /**
     * Get the number of tasks in the collection.
     *
     * @return the number of tasks in the collection
     */
    int size();

    /**
     * Return true if it contains no tasks.
     *
     * @return true if it contains no tasks, false otherwise
     */
    boolean isEmpty();


    /**
     * Return the collection uuid.
     * @return the collection uuid
     */
    UUID getUuid();

    /**
     * Return the collection owner uuid.
     * @return the collection owner uuid
     */
    User getOwner();

    List<Task> getTasks();
}

