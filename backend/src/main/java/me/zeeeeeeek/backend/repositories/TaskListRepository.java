package me.zeeeeeeek.backend.repositories;

import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface TaskListRepository extends CrudRepository<TaskList, UUID> {


    TaskList findByOwner(User owner);
}
