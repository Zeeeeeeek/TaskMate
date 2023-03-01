package me.zeeeeeeek.backend.repositories;

import lombok.NonNull;
import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.user.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface TaskListRepository extends CrudRepository<TaskList, UUID> {

    @Query("SELECT tl FROM taskLists tl WHERE tl.owner = :user")
    List<TaskList> findAllOwnedBy(@Param("user") @NonNull User owner);

    TaskList findByOwner(@Param("owner") @NonNull User owner);
}
