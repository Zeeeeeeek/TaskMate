package me.zeeeeeeek.backend.exceptions.users;

public class DuplicateCredentialsException extends RuntimeException {

    public DuplicateCredentialsException(String message) {
        super(message);
    }
}
