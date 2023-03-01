package me.zeeeeeeek.backend.repositories;

import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AbstractTaskRepository extends CrudRepository<AbstractTask, UUID> {
    /**
     * Find all the tasks of a give TaskList.
     */
    @Query("SELECT t FROM AbstractTask t WHERE t.taskList.id = ?1")
    Iterable<AbstractTask> findAllByTaskListId(UUID taskListId);

}
