package me.zeeeeeeek.backend.models.tasks.collections;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import me.zeeeeeeek.backend.models.tasks.elements.Task;
import me.zeeeeeeek.backend.models.user.User;

import java.util.*;
import java.util.stream.Stream;

/**
 * A list of tasks.
 * A task list is considered completed when all its tasks are completed.
 */
@Entity(name = "taskLists")
@NoArgsConstructor
@Data
public class TaskList implements TaskCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("taskList")
    private List<AbstractTask> tasks;

    @ManyToOne
    @JsonIgnoreProperties("taskLists")
    private User owner;

    /**
     * Create a new task list from a list of tasks.
     *
     * @param tasks the list of tasks
     * @throws NullPointerException if the list of tasks is null
     */
    public TaskList(List<AbstractTask> tasks, User owner) {
        Objects.requireNonNull(tasks);
        tasks.forEach(Objects::requireNonNull);
        this.owner = Objects.requireNonNull(owner);
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Create a new task list with an empty list of tasks.
     */
    public TaskList(User owner) {
        this.owner = Objects.requireNonNull(owner);
        this.tasks = new ArrayList<>();
    }
    /**
     * Controls if all the contained tasks are completed or not
     *
     * @return true, if all {@link AbstractTask#isCompleted()} calls in each contained task returns true, false otherwise
     */
    @Override
    public boolean isCompleted() {
        return this.tasks.stream()
                .allMatch(AbstractTask::isCompleted);
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
    public void setCompleted(AbstractTask task) {
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
    public void setUncompleted(AbstractTask task) {
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
    public boolean contains(AbstractTask task) {
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
    public boolean addTask(AbstractTask task) {
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
    public boolean removeTask(AbstractTask task) {
        return this.tasks.remove(Objects.requireNonNull(task));
    }

    /**
     * Get a stream of the tasks in the collection.
     *
     * @return a stream of the tasks in the collection
     */
    @Override
    public Stream<AbstractTask> stream() {
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

    /**
     * Return the collection uuid.
     *
     * @return the collection uuid
     */
    @Override
    public UUID getUuid() {
        return this.id;
    }

    /**
     * Return the collection owner uuid.
     *
     * @return the collection owner uuid
     */
    @Override
    public User getOwner() {
        return this.owner;
    }

    /**
     * Return the collection tasks list.
     *
     * @return the collection tasks list
     */
    @Override
    public List<AbstractTask> getTasks() {
        return this.tasks;
    }

}
