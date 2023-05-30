package me.zeeeeeeek.backend.exceptions.users;

public class CredentialsAlreadyUsed extends RuntimeException {

    public CredentialsAlreadyUsed(String message) {
        super(message);
    }
}
