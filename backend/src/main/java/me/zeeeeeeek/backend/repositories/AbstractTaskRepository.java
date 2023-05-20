package me.zeeeeeeek.backend.repositories;

import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AbstractTaskRepository extends CrudRepository<AbstractTask, UUID> {

}
