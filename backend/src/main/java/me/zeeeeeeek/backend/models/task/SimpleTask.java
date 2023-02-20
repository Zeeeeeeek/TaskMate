package me.zeeeeeeek.backend.models.task;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * A simple task is a specific activity that can be completed or not.
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SimpleTask extends AbstractTask {

    /**
     * Create a new simple task.
     *
     * @param name        the name of the task
     * @param description the description of the task
     * @throws NullPointerException     if name or description is null
     * @throws IllegalArgumentException if name or description is empty
     */
    public SimpleTask(String name, String description) {
        super(name, description);
    }
}
