package me.zeeeeeeek.backend.models.task;

import lombok.*;

import java.util.*;

public abstract class AbstractTask implements Task {
    @Getter
    private String name;
    @Getter
    private String description;
    private boolean completed;

    protected AbstractTask(String name, String description) {
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
     * The status of the task.
     *
     * @return the status of the task
     */
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
