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

    private static final User OWNER = new User("user", "password", "email@mm", Role.USER);

    @Test
    void taskListShouldBeCreated() {
        TaskList taskList = new TaskList(OWNER, "listName");
        assertEquals(0, taskList.size());
    }

    @Test
    void taskListShouldBeCreatedWithList() {
        List<AbstractTask> tasks = List.of(
                new SimpleTask("Task 1", "Description 1"),
                new SimpleTask("Task 2", "Description 2")
        );
        TaskList taskList = new TaskList(tasks, OWNER, "listName");
        assertEquals(2, taskList.size());
    }

    @Test
    void taskListShouldNotBeCreatedWithNullList() {
        assertThrows(NullPointerException.class, () -> new TaskList(null, "listName"));
    }

    @Test
    void taskListSizeShouldBeCorrect() {
        TaskList taskList = new TaskList(List.of(
                new SimpleTask("Task 1", "Description 1")), OWNER, "listName");
        assertEquals(1, taskList.size());
        TaskList emptyTaskList = new TaskList(OWNER, "listName");
        assertEquals(0, emptyTaskList.size());
    }

    @Test
    void taskListShouldBeEmpty() {
        TaskList taskList = new TaskList(OWNER, "listName");
        assertTrue(taskList.isEmpty());
    }

    @Test
    void taskListShouldNotBeEmpty() {
        TaskList taskList = new TaskList(List.of(
                new SimpleTask("Task 1", "Description 1")), OWNER, "listName");
        assertFalse(taskList.isEmpty());
    }

    @Test
    void shouldContainTask() {
        AbstractTask task = new SimpleTask("Task 1", "Description 1");
        TaskList taskList = new TaskList(List.of(task), OWNER, "listName");
        assertTrue(taskList.contains(task));
    }

    @Test
    void shouldNotContainTask() {
        AbstractTask task = new SimpleTask("Task 1", "Description 1");
        TaskList taskList = new TaskList(List.of(task), OWNER, "listName");
        assertFalse(taskList.contains(new SimpleTask("Task 2", "Description 2")));
    }

    @Test
    void containsShouldThrowExceptionWithNull() {
        TaskList taskList = new TaskList(OWNER, "listName");
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }


    @Test
    void taskShouldBeAdded() {
        TaskList taskList = new TaskList(OWNER, "listName");
        taskList.addTask(new SimpleTask("Task 1", "Description 1"));
        assertEquals(1, taskList.size());
    }

    @Test
    void taskAddShouldThrowNullPointerException() {
        TaskList taskList = new TaskList(OWNER, "listName");
        assertThrows(NullPointerException.class, () -> taskList.addTask(null));
    }

    @Test
    void taskShouldBeRemoved() {
        AbstractTask task = new SimpleTask("Task 1", "Description 1");
        TaskList taskList = new TaskList(List.of(task), OWNER, "listName");
        taskList.removeTask(task);
        assertEquals(0, taskList.size());
    }

    @Test
    void taskRemoveShouldThrowNullPointerException() {
        TaskList taskList = new TaskList(OWNER, "listName");
        assertThrows(NullPointerException.class, () -> taskList.removeTask(null));
    }

    @Test
    void taskListShouldNotBeCompleted() {
        TaskList taskList = new TaskList(List.of(
                new SimpleTask("Task 1", "Description 1")
        ), OWNER, "listName");
        assertFalse(taskList.isCompleted());
    }

    @Test
    void taskListShouldBeCompleted(){
        AbstractTask task1 = new SimpleTask("Task 1", "Description 1");
        task1.setCompleted();
        TaskList taskList = new TaskList(List.of(task1), OWNER, "listName");
        assertTrue(taskList.isCompleted());
    }

    @Test
    void taskShouldNotBeCompletedWithOneTaskUncompleted() {
        AbstractTask task1 = new SimpleTask("Task 1", "Description 1");
        AbstractTask task2 = new SimpleTask("Task 2", "Description 2");
        task1.setCompleted();
        TaskList taskList = new TaskList(List.of(task1, task2), OWNER, "listName");
        assertFalse(taskList.isCompleted());
    }


    @Test
    void userShouldBeOwner() {
        TaskList taskList = new TaskList(OWNER, "listName");
        assertEquals(OWNER, taskList.getOwner());
    }


}
