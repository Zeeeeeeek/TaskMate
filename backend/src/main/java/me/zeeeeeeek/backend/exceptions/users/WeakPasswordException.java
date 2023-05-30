package me.zeeeeeeek.backend.exceptions.users;

public class WeakPasswordException extends RuntimeException{

    public WeakPasswordException(String message) {
        super(message);
    }
}
