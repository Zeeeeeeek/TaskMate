package me.zeeeeeeek.backend.models.tasks;

import me.zeeeeeeek.backend.models.tasks.elements.TimeConstrainedTask;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class TimeConstrainedTaskTest {

    @Test
    void taskShouldBeCreated() {
        TimeConstrainedTask task = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        assertEquals("name", task.getName());
        assertEquals("description", task.getDescription());
    }

    @Test
    void taskShouldNotBeCreatedWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new TimeConstrainedTask("name", "description", null));
        assertThrows(NullPointerException.class, () -> new TimeConstrainedTask("name", null, LocalDateTime.now().plusDays(1)));
        assertThrows(NullPointerException.class, () -> new TimeConstrainedTask(null, "description", LocalDateTime.now().plusDays(1)));
    }

    @Test
    void taskShouldNotBeExpired() {
        TimeConstrainedTask task = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        assertFalse(task.isExpired());
    }

    @Test
    void taskShouldBeExpired() {
        TimeConstrainedTask task = new TimeConstrainedTask("name", "description", LocalDateTime.now().minusDays(1));
        assertTrue(task.isExpired());
    }

    @Test
    void taskShouldBeCompleted() {
        TimeConstrainedTask task = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        task.setCompleted();
        assertTrue(task.isCompleted());
    }

    @Test
    void taskShouldBeUncompleted() {
        TimeConstrainedTask task = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        assertFalse(task.isCompleted());
    }

    @Test
    void taskNameShouldBeModified() {
        TimeConstrainedTask task = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        task.setName("new name");
        assertEquals("new name", task.getName());
    }

    @Test
    void taskDescriptionShouldBeModified() {
        TimeConstrainedTask task = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        task.setDescription("new description");
        assertEquals("new description", task.getDescription());
    }

    @Test
    void taskNameShouldThrowNullPointerException() {
        TimeConstrainedTask task = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        assertThrows(NullPointerException.class, () -> task.setName(null));
    }

    @Test
    void taskDescriptionShouldThrowNullPointerException() {
        TimeConstrainedTask task = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        assertThrows(NullPointerException.class, () -> task.setDescription(null));
    }

    @Test
    void taskNameShouldThrowIllegalArgumentException() {
        TimeConstrainedTask task = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        assertThrows(IllegalArgumentException.class, () -> task.setName(""));
    }

    @Test
    void taskDescriptionShouldThrowIllegalArgumentException() {
        TimeConstrainedTask task = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        assertThrows(IllegalArgumentException.class, () -> task.setDescription(""));
    }

    @Test
    void taskShouldBeEqual() {
        TimeConstrainedTask task1 = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        TimeConstrainedTask task2 = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        assertEquals(task1, task2);
    }

    @Test
    void taskShouldNotBeEqual() {
        TimeConstrainedTask task1 = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        TimeConstrainedTask task2 = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(2));
        assertNotEquals(task1, task2);
    }

    @Test
    void taskShouldHaveSameHashCode() {
        TimeConstrainedTask task1 = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        TimeConstrainedTask task2 = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        assertEquals(task1.hashCode(), task2.hashCode());
    }

    @Test
    void taskShouldNotHaveSameHashCode() {
        TimeConstrainedTask task1 = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(1));
        TimeConstrainedTask task2 = new TimeConstrainedTask("name", "description", LocalDateTime.now().plusDays(2));
        assertNotEquals(task1.hashCode(), task2.hashCode());
    }


}
