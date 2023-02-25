package me.zeeeeeeek.backend.models.tasks.elements;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * TimeConstrainedTask is a task that has a due date.
 */
@ToString
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
        if (!(o instanceof TimeConstrainedTask that)) return false;
        if (!super.equals(o)) return false;
        return isLocalDateTimeEqual(this.dueDate, that.dueDate) &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getDescription(), that.getDescription());
    }

    /**
     * Utility method to check if two LocalDateTime are equal.
     * We consider two LocalDateTime equal if they are within 1 second.
     * @param firstDate the first LocalDateTime
     * @param secondDate the second LocalDateTime
     */
    private boolean isLocalDateTimeEqual(LocalDateTime firstDate, LocalDateTime secondDate) {
        return  Objects.equals(firstDate.getYear(), secondDate.getYear()) &&
                Objects.equals(firstDate.getMonth(), secondDate.getMonth()) &&
                Objects.equals(firstDate.getDayOfMonth(), secondDate.getDayOfMonth()) &&
                Objects.equals(firstDate.getHour(), secondDate.getHour()) &&
                Objects.equals(firstDate.getMinute(), secondDate.getMinute()) &&
                Math.abs(firstDate.getSecond() - secondDate.getSecond()) <= 1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), dueDate);
    }

}
