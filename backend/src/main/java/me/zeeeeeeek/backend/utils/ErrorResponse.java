package me.zeeeeeeek.backend.utils;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;
@Data
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private String errorMessage;
    private Object data;
    /**
     * Creates a new ErrorResponse object with the current time as timeStamp.
     */
    public ErrorResponse(){
        this.timeStamp = LocalDateTime.now();
    }

    /**
     * Creates a new ErrorResponse object with the current time as timeStamp and the given errorMessage.
     * @param errorMessage The error message to be displayed.
     */
    public ErrorResponse(@NonNull String errorMessage) {
        this();
        this.errorMessage = errorMessage;
    }

    /**
     * Creates a new ErrorResponse object with the current time as timeStamp and the given errorMessage and the data object.
     * @param errorMessage The error message to be displayed.
     * @param data The data object that caused the error.
     */
    public ErrorResponse(String errorMessage, @NonNull Object data) {
        this(errorMessage);
        this.data = data;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ErrorResponse(timeStamp= ").append(this.getTimeStamp());
        if(this.getErrorMessage() != null) {
            sb.append(", errorMessage= ").append(this.getErrorMessage());
        }
        if(this.getData() != null) {
            sb.append(", data= ").append(this.getData());
        }
        sb.append(")");
        return sb.toString();
    }
}
