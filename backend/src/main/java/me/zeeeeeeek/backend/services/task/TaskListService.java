package me.zeeeeeeek.backend.services.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.tasks.collections.dtos.CreateTasksDTO;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.repositories.AbstractTaskRepository;
import me.zeeeeeeek.backend.repositories.TaskListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskListService {
    private final TaskListRepository taskListRepository;
    private final AbstractTaskRepository abstractTaskRepository;


    public TaskList create(List<AbstractTask> tasks, User owner) {
        return new TaskList(tasks, owner);
    }

    public TaskList save(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public TaskList createAndSave(List<AbstractTask> tasks, User owner) {
        return save(create(tasks, owner));
    }

    public List<TaskList> getAllOwnedBy(User owner) {
        return taskListRepository.findAllOwnedBy(owner);
    }

    public void addTasksToTaskList(UUID taskListId, CreateTasksDTO createTasksDTO) {
        TaskList taskList = this.taskListRepository
                .findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task list not found"));
        List<AbstractTask> tasks = createTasksDTO.getTasksAsList();

        tasks.forEach(task -> task.setTaskList(taskList));
        abstractTaskRepository.saveAll(tasks);
    }
}
