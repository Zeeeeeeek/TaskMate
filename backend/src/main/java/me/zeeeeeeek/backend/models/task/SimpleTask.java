package me.zeeeeeeek.backend.models.task;

import java.util.*;

/**
 * A simple task is a specific activity that can be completed or not.
 */
public class SimpleTask implements Task {

    private String name;
    private String description;

    boolean completed;

    /**
     * Create a new simple task.
     *
     * @param name        the name of the task
     * @param description the description of the task
     * @throws NullPointerException     if name or description is null
     * @throws IllegalArgumentException if name or description is empty
     */
    public SimpleTask(String name, String description) {
        this.name = isValidStringParameter(name);
        this.description = isValidStringParameter(description);
        this.completed = false;
    }

    private String isValidStringParameter(String parameter) {
        if (Objects.requireNonNull(parameter).isEmpty())
            throw new IllegalArgumentException("name cannot be empty");
        return parameter;
    }


    /**
     * The name of the task.
     *
     * @return the name of the task
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * The description of the task.
     *
     * @return the description of the task
     */
    @Override
    public String getDescription() {
        return this.description;
    }

    /**
     * The status of the task.
     *
     * @return the status of the task
     */
    @Override
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * The deadline of the task.
     */
    @Override
    public void setCompleted() {
        this.modifyCompleted(true);
    }

    /**
     * Set the task as uncompleted.
     */
    @Override
    public void setUncompleted() {
        this.modifyCompleted(false);
    }

    private void modifyCompleted(boolean newValue) {
        this.completed = newValue;
    }

    /**
     * Set the name of the task.
     *
     * @param name task's new name
     */
    @Override
    public void setName(String name) {
        this.name = isValidStringParameter(name);
    }

    /**
     * Set the description of the task.
     *
     * @param description task's new description
     */
    @Override
    public void setDescription(String description) {
        this.description = isValidStringParameter(description);
    }
}
