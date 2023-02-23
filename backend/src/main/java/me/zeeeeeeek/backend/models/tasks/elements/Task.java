package me.zeeeeeeek.backend.models.tasks.elements;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * A task is a specific activity or assignment that needs to be completed within a certain timeframe or deadline.
 * It can be part of a larger project or goal, or it can be a standalone activity.
 * Tasks can have different levels of complexity and can be assigned to one or more individuals or teams to complete.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleTask.class, name = "simpleTask"),
        @JsonSubTypes.Type(value = TimeConstrainedTask.class, name = "timeConstrainedTask"),
})
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
    boolean isCompleted();

    /**
     * The deadline of the task.
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
}
