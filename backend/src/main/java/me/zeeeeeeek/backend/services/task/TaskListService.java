package me.zeeeeeeek.backend.services.task;

import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.repositories.TaskListRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListService {
    private final TaskListRepository taskListRepository;

    public TaskListService(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }


    public TaskList create(List<AbstractTask> tasks, User owner) {
        return new TaskList(tasks, owner);
    }

    public TaskList save(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public TaskList createAndSave(List<AbstractTask> tasks, User owner) {
        return save(create(tasks, owner));
    }

    /**
     * Retrive a task list by its owner UUID.
     * @param owner the owner
     * @return the task list
     */
    public TaskList getByOwner(User owner) {
        return taskListRepository.findByOwner(owner);
    }

}
