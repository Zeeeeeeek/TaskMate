package me.zeeeeeeek.backend.services.task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.tasks.collections.dtos.TasksDTO;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.repositories.TaskListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class TaskListService {
    private final TaskListRepository taskListRepository;
    private final TaskService taskService;


    public TaskList create(List<AbstractTask> tasks, User owner, String name) {
        return new TaskList(tasks, owner, name);
    }

    public TaskList save(TaskList taskList) {
        return taskListRepository.save(taskList);
    }

    public TaskList createAndSave(List<AbstractTask> tasks, User owner, String name) {
        return save(create(tasks, owner, name));
    }

    public List<TaskList> getAllOwnedBy(User owner) {
        return taskListRepository.findAllOwnedBy(owner);
    }

    public void addTasksToTaskList(UUID taskListId, TasksDTO tasksDTO) {
        TaskList taskList = this.taskListRepository
                .findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task list not found"));
        List<AbstractTask> tasks = tasksDTO.getTasksAsList();
        this.taskService
                .setTasksTaskList(tasks, taskList);
    }

    public void deleteTaskList(UUID taskListId, User owner) {
        TaskList taskList = this.taskListRepository
                .findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task list not found"));
        if(!taskList.getOwner().equals(owner)) {
            throw new IllegalArgumentException("User is not the owner of the task list");
        }
        this.taskListRepository.delete(taskList);
    }

    public void updateTaskListName(UUID taskListId, String name, User owner) {
        TaskList taskList = this.taskListRepository
                .findById(taskListId)
                .orElseThrow(() -> new IllegalArgumentException("Task list not found"));
        if(!taskList.getOwner().equals(owner)) {
            throw new IllegalArgumentException("User is not the owner of the task list");
        }
        taskList.setName(name);
        this.taskListRepository.save(taskList);
    }
}
