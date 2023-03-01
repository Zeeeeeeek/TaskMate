package me.zeeeeeeek.backend.services.task;

import lombok.NonNull;
import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.tasks.collections.dtos.CreateTasksDTO;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.repositories.AbstractTaskRepository;
import me.zeeeeeeek.backend.repositories.TaskListRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Service
public class TaskListService {
    private final TaskListRepository taskListRepository;
    private final AbstractTaskRepository abstractTaskRepository;

    public TaskListService(TaskListRepository taskListRepository, AbstractTaskRepository abstractTaskRepository) {
        this.taskListRepository = taskListRepository;
        this.abstractTaskRepository = abstractTaskRepository;
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

    public void setIsCompleted(UUID taskId, boolean isCompleted) {
        AbstractTask task = abstractTaskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));
        task.setIsCompleted(isCompleted);
        abstractTaskRepository.save(task);
    }

    public List<TaskList> getAllOwnedBy(User owner) {
        return taskListRepository.findAllOwnedBy(owner);
    }

    //Todo remove temporary getAllTasks method
    public List<AbstractTask> getAllTasks() {
        Iterable<AbstractTask> all = abstractTaskRepository.findAll();
        List<AbstractTask> list = new ArrayList<>();
        all.forEach(list::add);
        return StreamSupport
                .stream(abstractTaskRepository.findAll().spliterator(), false)
                .toList();
    }

    public List<AbstractTask> getAllTasksOfList(@NonNull UUID listId) {
        Iterable<AbstractTask> all = abstractTaskRepository.findAllByTaskListId(listId);
        List<AbstractTask> list = new ArrayList<>();
        all.forEach(list::add);
        return list;
    }

    public void deleteTask(@NonNull UUID taskId) {
        abstractTaskRepository.deleteById(taskId);
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
