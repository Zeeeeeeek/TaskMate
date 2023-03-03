package me.zeeeeeeek.backend.models.tasks;

import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import me.zeeeeeeek.backend.models.tasks.elements.AbstractTask;
import me.zeeeeeeek.backend.models.tasks.elements.SimpleTask;
import me.zeeeeeeek.backend.models.user.Role;
import me.zeeeeeeek.backend.models.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private static final User OWNER = new User("user", "password", "email@mm", "firstName", "lastName", Role.USER);

    @Test
    void taskListShouldBeCreated() {
        TaskList taskList = new TaskList(OWNER);
        assertEquals(0, taskList.size());
    }

    @Test
    void taskListShouldBeCreatedWithList() {
        List<AbstractTask> tasks = List.of(
                new SimpleTask("Task 1", "Description 1"),
                new SimpleTask("Task 2", "Description 2")
        );
        TaskList taskList = new TaskList(tasks, OWNER);
        assertEquals(2, taskList.size());
    }

    @Test
    void taskListShouldNotBeCreatedWithNullList() {
        assertThrows(NullPointerException.class, () -> new TaskList(null));
    }

    @Test
    void taskListSizeShouldBeCorrect() {
        TaskList taskList = new TaskList(List.of(
                new SimpleTask("Task 1", "Description 1")), OWNER);
        assertEquals(1, taskList.size());
        TaskList emptyTaskList = new TaskList(OWNER);
        assertEquals(0, emptyTaskList.size());
    }

    @Test
    void taskListShouldBeEmpty() {
        TaskList taskList = new TaskList(OWNER);
        assertTrue(taskList.isEmpty());
    }

    @Test
    void taskListShouldNotBeEmpty() {
        TaskList taskList = new TaskList(List.of(
                new SimpleTask("Task 1", "Description 1")), OWNER);
        assertFalse(taskList.isEmpty());
    }

    @Test
    void shouldContainTask() {
        AbstractTask task = new SimpleTask("Task 1", "Description 1");
        TaskList taskList = new TaskList(List.of(task), OWNER);
        assertTrue(taskList.contains(task));
    }

    @Test
    void shouldNotContainTask() {
        AbstractTask task = new SimpleTask("Task 1", "Description 1");
        TaskList taskList = new TaskList(List.of(task), OWNER);
        assertFalse(taskList.contains(new SimpleTask("Task 2", "Description 2")));
    }

    @Test
    void containsShouldThrowExceptionWithNull() {
        TaskList taskList = new TaskList(OWNER);
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }


    @Test
    void taskShouldBeAdded() {
        TaskList taskList = new TaskList(OWNER);
        taskList.addTask(new SimpleTask("Task 1", "Description 1"));
        assertEquals(1, taskList.size());
    }

    @Test
    void taskAddShouldThrowNullPointerException() {
        TaskList taskList = new TaskList(OWNER);
        assertThrows(NullPointerException.class, () -> taskList.addTask(null));
    }

    @Test
    void taskShouldBeRemoved() {
        AbstractTask task = new SimpleTask("Task 1", "Description 1");
        TaskList taskList = new TaskList(List.of(task), OWNER);
        taskList.removeTask(task);
        assertEquals(0, taskList.size());
    }

    @Test
    void taskRemoveShouldThrowNullPointerException() {
        TaskList taskList = new TaskList(OWNER);
        assertThrows(NullPointerException.class, () -> taskList.removeTask(null));
    }

    @Test
    void taskListShouldNotBeCompleted() {
        TaskList taskList = new TaskList(List.of(
                new SimpleTask("Task 1", "Description 1")
        ), OWNER);
        assertFalse(taskList.isCompleted());
    }

    @Test
    void taskListShouldBeCompleted(){
        AbstractTask task1 = new SimpleTask("Task 1", "Description 1");
        task1.setCompleted();
        TaskList taskList = new TaskList(List.of(task1), OWNER);
        assertTrue(taskList.isCompleted());
    }

    @Test
    void taskShouldNotBeCompletedWithOneTaskUncompleted() {
        AbstractTask task1 = new SimpleTask("Task 1", "Description 1");
        AbstractTask task2 = new SimpleTask("Task 2", "Description 2");
        task1.setCompleted();
        TaskList taskList = new TaskList(List.of(task1, task2), OWNER);
        assertFalse(taskList.isCompleted());
    }


    @Test
    void userShouldBeOwner() {
        TaskList taskList = new TaskList(OWNER);
        assertEquals(OWNER, taskList.getOwner());
    }


}
