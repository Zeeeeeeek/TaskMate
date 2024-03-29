package me.zeeeeeeek.backend.exceptions.handlers;

import me.zeeeeeeek.backend.exceptions.users.CredentialsAlreadyUsed;
import me.zeeeeeeek.backend.exceptions.users.WeakPasswordException;
import me.zeeeeeeek.backend.utils.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(CredentialsAlreadyUsed.class)
    public ResponseEntity<ErrorResponse> handleCredentialsAlreadyUsed(CredentialsAlreadyUsed e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, CONFLICT);
    }

    @ExceptionHandler(WeakPasswordException.class)
    public ResponseEntity<ErrorResponse> handleWeakPasswordException(WeakPasswordException e) {
        ErrorResponse errorResponse = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(errorResponse, UNPROCESSABLE_ENTITY);
    }

}
