package me.zeeeeeeek.backend.models.tasks.elements;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * TimeConstrainedTask is a task that has a due date.
 */
public class TimeConstrainedTask extends AbstractTask{
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Getter @Setter
    private LocalDateTime dueDate;
    public TimeConstrainedTask(String name, String description, LocalDateTime dueDate) {
        super(name, description);
        this.dueDate = Objects.requireNonNull(dueDate);
    }

    /**
     * This method checks if the task is expired. Meaning that the due date is before the current date.
     * @return true, if the task is expired, false otherwise
     */
    public boolean isExpired() {
        return this.dueDate.isBefore(LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        TimeConstrainedTask that = (TimeConstrainedTask) o;
        return dueDate.equals(that.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dueDate);
    }
}
