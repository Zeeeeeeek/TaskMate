package me.zeeeeeeek.backend.models.tasks.elements;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;


/**
 * This is an abstract implementation of a task in order to avoid code duplication.
 */
@ToString
@EqualsAndHashCode
@Slf4j
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
        log.info("Created task {}", this);
    }


    /**
     * Check if the given parameter is a valid string.
     * A valid string is a non-null string that is not empty.
     *
     * @param parameter the parameter to check
     * @return the parameter if it is valid
     * @throws NullPointerException     if the parameter is null
     * @throws IllegalArgumentException if the parameter is empty
     */
    private String isValidStringParameter(String parameter) {
        if (Objects.requireNonNull(parameter).isEmpty()) {
            log.error("Used an empty string as a parameter");
            throw new IllegalArgumentException("Parameter cannot be empty");
        }

        return parameter;
    }

    /**
     * Check if the task is completed.
     *
     * @return true if the task is completed, false otherwise
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Set the task as completed.
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
