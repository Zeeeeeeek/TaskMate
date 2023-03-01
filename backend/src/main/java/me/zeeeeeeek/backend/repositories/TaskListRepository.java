package me.zeeeeeeek.backend.repositories;

import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface TaskListRepository extends CrudRepository<TaskList, UUID> {

    @Query("SELECT tl FROM taskLists tl WHERE tl.owner = ?1")
    List<TaskList> findAllOwnedBy(User owner);

    TaskList findByOwner(User owner);
}
