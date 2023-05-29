package me.zeeeeeeek.backend.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private final LocalDateTime timestamp;

    private String errorMessage;

    private HttpStatus httpStatus;

    private String stackTrace;

    private Object data;

    /**
     * Constructs an ErrorResponse object with the current timestamp.
     */
    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructs an ErrorResponse object with the current timestamp and the given error message and HTTP status.
     * @param errorMessage the error message
     * @param httpStatus the HTTP status
     */
    public ErrorResponse(String errorMessage, HttpStatus httpStatus) {
        this();
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }

    /**
     * Constructs an ErrorResponse object with the current timestamp and the given error message.
     *
     * @param errorMessage the error message
     * @param httpStatus the HTTP status
     * @param stackTrace the stack trace
     * @param data the data object that caused the error
     */
    public ErrorResponse(String errorMessage, HttpStatus httpStatus, String stackTrace, Object data) {
        this(errorMessage, httpStatus);
        this.stackTrace = stackTrace;
        this.data = data;
    }
}
