package me.zeeeeeeek.backend.models.tasks.elements;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Transient;
import me.zeeeeeeek.backend.models.tasks.collections.TaskList;

/**
 * A task is a specific activity that can be completed or not.
 * This interface is used to represent a task, and defines the contract for all subclasses.
 *
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleTask.class, name = "simpleTask"),
        @JsonSubTypes.Type(value = TimeConstrainedTask.class, name = "timeConstrainedTask"),
})

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public interface Task {

    /**
     * The name of the task.
     *
     * @return the name of the task
     */
    String getName();

    /**
     * The description of the task.
     *
     * @return the description of the task
     */
    String getDescription();

    /**
     * The status of the task.
     *
     * @return the status of the task
     */
    @Transient
    boolean isCompleted();

    /**
     * Set the task as completed.
     *
     */
    void setCompleted();

    /**
     * Set the task as uncompleted.
     */
    void setUncompleted();

    /**
     * Set the name of the task.
     *
     * @param name task's new name
     */
    void setName(String name);

    /**
     * Set the description of the task.
     *
     * @param description task's new description
     */
    void setDescription(String description);

    /**todo temp
     * Set the taskList of the task.
     */
    void setTaskList(TaskList taskList);

    /**
     * Get the taskList of the task.
     */
    TaskList getTaskList();
}
