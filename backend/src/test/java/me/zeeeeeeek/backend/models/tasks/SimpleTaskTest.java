package me.zeeeeeeek.backend.models.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleTaskTest {

    SimpleTask task = new SimpleTask("name", "description");

    @Test
    void taskShouldBeCreated() {
        assertEquals("name", task.getName());
        assertEquals("description", task.getDescription());
        assertFalse(task.isCompleted());
    }

    @Test
    void taskCreationShouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SimpleTask(null, "description"));
        assertThrows(NullPointerException.class, () -> new SimpleTask("name", null));
    }

    @Test
    void taskCreationShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new SimpleTask("", "description"));
        assertThrows(IllegalArgumentException.class, () -> new SimpleTask("name", ""));
    }

    @Test
    void taskShouldBeCompleted() {
        task.setCompleted();
        assertTrue(task.isCompleted());
    }

    @Test
    void taskShouldBeUncompleted() {
        task.setCompleted();
        task.setUncompleted();
        assertFalse(task.isCompleted());
    }

    @Test
    void taskNameShouldBeModified() {
        task.setName("new name");
        assertEquals("new name", task.getName());
    }

    @Test
    void taskDescriptionShouldBeModified() {
        task.setDescription("new description");
        assertEquals("new description", task.getDescription());
    }

    @Test
    void taskNameShouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> task.setName(null));
    }

    @Test
    void taskNameShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> task.setName(""));
    }

    @Test
    void taskDescriptionShouldThrowNullPointerException() {
        assertThrows(NullPointerException.class, () -> task.setDescription(null));
    }

    @Test
    void taskDescriptionShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> task.setDescription(""));
    }

    @Test
    void taskShouldBeEqual() {
        SimpleTask task1 = new SimpleTask("name", "description");
        SimpleTask task2 = new SimpleTask("name", "description");
        assertEquals(task1, task2);
    }

    @Test
    void taskShouldNotBeEqual() {
        SimpleTask task1 = new SimpleTask("name", "description");
        SimpleTask task2 = new SimpleTask("name", "description2");
        assertNotEquals(task1, task2);
    }

    @Test
    void taskShouldHaveSameHashCode() {
        SimpleTask task1 = new SimpleTask("name", "description");
        SimpleTask task2 = new SimpleTask("name", "description");
        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void taskShouldNotHaveSameHashCode() {
        SimpleTask task1 = new SimpleTask("name", "description");
        SimpleTask task2 = new SimpleTask("name", "description2");
        assertNotEquals(task1.hashCode(), task2.hashCode());
    }
}
