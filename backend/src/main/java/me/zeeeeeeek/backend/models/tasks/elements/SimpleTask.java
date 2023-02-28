package me.zeeeeeeek.backend.models.tasks.elements;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * A simple task is a specific activity that can be completed or not.
 * It is the most basic task. It has a name and a description.
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
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
