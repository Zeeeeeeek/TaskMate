package me.zeeeeeeek.backend.models.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * A list of tasks.
 * A task list is considered completed when all its tasks are completed.
 */
public class TaskList implements TaskCollection {

    private final List<Task> tasks;

    /**
     * Create a new task list from a list of tasks.
     *
     * @param tasks the list of tasks
     * @throws NullPointerException if the list of tasks is null
     */
    public TaskList(List<Task> tasks) {
        Objects.requireNonNull(tasks);
        tasks.forEach(Objects::requireNonNull);
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Create a new task list with an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    /**
     * Controls if all the contained tasks are completed or not
     *
     * @return true, if all {@link Task#isCompleted()} calls in each contained task returns true, false otherwise
     */
    @Override
    public boolean isCompleted() {
        return this.tasks.stream()
                .allMatch(Task::isCompleted);
    }

    /**
     * Set the given task as completed.
     * If multiple tasks are equal to the given task, all of them are set as completed.
     * If the task is already completed, nothing happens.
     * If the task is not contained in the collection, nothing happens.
     *
     * @param task the task to set as completed
     * @throws NullPointerException if the task is null
     */
    @Override
    public void setCompleted(Task task) {
        this.tasks.stream()
                .filter(t -> t.equals(task))
                .forEach(Task::setCompleted);
    }

    /**
     * Set the given task as uncompleted.
     * If multiple tasks are equal to the given task, all of them are set as uncompleted.
     * If the task is already uncompleted, nothing happens.
     * If the task is not contained in the collection, nothing happens.
     *
     * @param task the task to set as uncompleted
     * @throws NullPointerException if the task is null
     */
    @Override
    public void setUncompleted(Task task) {
        this.tasks.stream()
                .filter(t -> t.equals(task))
                .forEach(Task::setUncompleted);
    }

    /**
     * Controls if the given task is contained in the collection.
     *
     * @param task the task to check
     * @return true if the task is contained in the collection, false otherwise
     * @throws NullPointerException if the task is null
     */
    @Override
    public boolean contains(Task task) {
            return this.tasks.contains(Objects.requireNonNull(task));
    }

    /**
     * Add a task to the collection.
     *
     * @param task the task to add
     * @return true if the task was added, false otherwise
     * @throws NullPointerException if the task is null
     */
    @Override
    public boolean addTask(Task task) {
        return this.tasks.add(Objects.requireNonNull(task));
    }

    /**
     * Remove a task from the collection.
     *
     * @param task the task to remove
     * @return true if the task was removed, false otherwise
     * @throws NullPointerException if the task is null
     */
    @Override
    public boolean removeTask(Task task) {
        return this.tasks.remove(Objects.requireNonNull(task));
    }

    /**
     * Get a stream of the tasks in the collection.
     *
     * @return a stream of the tasks in the collection
     */
    @Override
    public Stream<Task> stream() {
        return this.tasks.stream();
    }

    /**
     * Get the number of tasks in the collection.
     *
     * @return the number of tasks in the collection
     */
    @Override
    public int size() {
        return this.tasks.size();
    }

    /**
     * Return true if it contains no tasks.
     *
     * @return true if it contains no tasks, false otherwise
     */
    @Override
    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

}
