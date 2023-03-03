package me.zeeeeeeek.backend.models.tasks.collections;


import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import me.zeeeeeeek.backend.models.user.User;

import java.util.List;

/**
 * A task collection represents a group of tasks.
 */
public interface TaskCollection {

    /**
     * Controls if all the contained tasks are completed or not
     *
     * @return true, if all {@link AbstractTask#isCompleted()} calls in each contained task returns true, false otherwise
     */
    boolean isCompleted();

    /**
     * Controls if the given task is contained in the collection.
     *
     * @param task the task to check
     * @return true if the task is contained in the collection, false otherwise
     * @throws NullPointerException if the task is null
     */
    boolean contains(AbstractTask task);

    /**
     * Add a task to the collection.
     *
     * @param task the task to add
     * @throws NullPointerException if the task is null
     */
    void addTask(AbstractTask task);

    /**
     * Remove a task from the collection.
     *
     * @param task the task to remove
     * @throws NullPointerException if the task is null
     */
    void removeTask(AbstractTask task);

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
     * Return the collection owner uuid.
     * @return the collection owner uuid
     */
    User getOwner();

    /**
     * Gets the list of tasks in the collection.
     *
     * @return the list of tasks in the collection
     */
    List<AbstractTask> getTasks();

    /**
     * Gets the collection name.
     * @return the collection name
     */
    String getName();

    /**
     * Sets the collection name.
     * @param name the new collection name
     */
    void setName(String name);
}

