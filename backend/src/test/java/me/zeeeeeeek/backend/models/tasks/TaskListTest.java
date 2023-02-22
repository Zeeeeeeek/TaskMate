package me.zeeeeeeek.backend.models.tasks;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {

    private static final UUID OWNER_UUID = UUID.randomUUID();

    @Test
    void taskListShouldBeCreated() {
        TaskList taskList = new TaskList(OWNER_UUID);
        assertEquals(0, taskList.size());
    }

    @Test
    void taskListShouldBeCreatedWithList() {
        List<Task> tasks = List.of(
                new SimpleTask("Task 1", "Description 1"),
                new SimpleTask("Task 2", "Description 2")
        );
        TaskList taskList = new TaskList(tasks, OWNER_UUID);
        assertEquals(2, taskList.size());
    }

    @Test
    void taskListShouldNotBeCreatedWithNullList() {
        assertThrows(NullPointerException.class, () -> new TaskList(null));
    }

    @Test
    void taskListSizeShouldBeCorrect() {
        TaskList taskList = new TaskList(List.of(
                new SimpleTask("Task 1", "Description 1")), OWNER_UUID);
        assertEquals(1, taskList.size());
        TaskList emptyTaskList = new TaskList(OWNER_UUID);
        assertEquals(0, emptyTaskList.size());
    }

    @Test
    void taskListShouldBeEmpty() {
        TaskList taskList = new TaskList(OWNER_UUID);
        assertTrue(taskList.isEmpty());
    }

    @Test
    void taskListShouldNotBeEmpty() {
        TaskList taskList = new TaskList(List.of(
                new SimpleTask("Task 1", "Description 1")), OWNER_UUID);
        assertFalse(taskList.isEmpty());
    }

    @Test
    void shouldContainTask() {
        Task task = new SimpleTask("Task 1", "Description 1");
        TaskList taskList = new TaskList(List.of(task), OWNER_UUID);
        assertTrue(taskList.contains(task));
    }

    @Test
    void shouldNotContainTask() {
        Task task = new SimpleTask("Task 1", "Description 1");
        TaskList taskList = new TaskList(List.of(task), OWNER_UUID);
        assertFalse(taskList.contains(new SimpleTask("Task 2", "Description 2")));
    }

    @Test
    void containsShouldThrowExceptionWithNull() {
        TaskList taskList = new TaskList(OWNER_UUID);
        assertThrows(NullPointerException.class, () -> taskList.contains(null));
    }


    @Test
    void taskShouldBeAdded() {
        TaskList taskList = new TaskList(OWNER_UUID);
        taskList.addTask(new SimpleTask("Task 1", "Description 1"));
        assertEquals(1, taskList.size());
    }

    @Test
    void taskAddShouldThrowNullPointerException() {
        TaskList taskList = new TaskList(OWNER_UUID);
        assertThrows(NullPointerException.class, () -> taskList.addTask(null));
    }

    @Test
    void taskShouldBeRemoved() {
        Task task = new SimpleTask("Task 1", "Description 1");
        TaskList taskList = new TaskList(List.of(task), OWNER_UUID);
        taskList.removeTask(task);
        assertEquals(0, taskList.size());
    }

    @Test
    void taskRemoveShouldThrowNullPointerException() {
        TaskList taskList = new TaskList(OWNER_UUID);
        assertThrows(NullPointerException.class, () -> taskList.removeTask(null));
    }

    @Test
    void taskListShouldNotBeCompleted() {
        TaskList taskList = new TaskList(List.of(
                new SimpleTask("Task 1", "Description 1")
        ), OWNER_UUID);
        assertFalse(taskList.isCompleted());
    }

    @Test
    void taskListShouldBeCompleted(){
        Task task1 = new SimpleTask("Task 1", "Description 1");
        task1.setCompleted();
        TaskList taskList = new TaskList(List.of(task1), OWNER_UUID);
        assertTrue(taskList.isCompleted());
    }

    @Test
    void taskShouldNotBeCompletedWithOneTaskUncompleted() {
        Task task1 = new SimpleTask("Task 1", "Description 1");
        Task task2 = new SimpleTask("Task 2", "Description 2");
        task1.setCompleted();
        TaskList taskList = new TaskList(List.of(task1, task2), OWNER_UUID);
        assertFalse(taskList.isCompleted());
    }

    @Test
    void shouldSetCompletedTask() {
        Task task = new SimpleTask("Task 1", "Description 1");
        TaskList taskList = new TaskList(List.of(task), OWNER_UUID);
        taskList.setCompleted(task);
        assertTrue(task.isCompleted());
    }



}
