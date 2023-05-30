package me.zeeeeeeek.backend.utils;

import lombok.Data;
import lombok.NonNull;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@Data
public class ErrorResponse {

    private LocalDateTime timeStamp;
    private String errorMessage;
    private Object data;
    private HttpStatus httpStatus;

    /**
     * Creates a new ErrorResponse object with the current time as timeStamp.
     */
    public ErrorResponse(){
        this.timeStamp = LocalDateTime.now();
    }

    /**
     * Creates a new ErrorResponse object with the current time as timeStamp and the given errorMessage and httpStatus.
     * @param errorMessage The error message to be displayed.
     * @param httpStatus The http status to be displayed.
     */
    public ErrorResponse(@NonNull String errorMessage, @NonNull HttpStatus httpStatus) {
        this();
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    /**
     * Creates a new ErrorResponse object with the current time as timeStamp and the given errorMessage, httpStatus and data.
     * @param errorMessage The error message to be displayed.
     * @param httpStatus The http status to be displayed.
     * @param data The data object that caused the error.
     */
    public ErrorResponse(String errorMessage, HttpStatus httpStatus, @NonNull Object data) {
        this(errorMessage, httpStatus);
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
        if(this.getHttpStatus() != null) {
            sb.append(", httpStatus= ").append(this.getHttpStatus());
        }
        sb.append(")");
        return sb.toString();
    }
}
