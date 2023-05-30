package me.zeeeeeeek.backend.exceptions.users;

public class InvalidEmailException extends RuntimeException{

        public InvalidEmailException(String message) {
            super(message);
        }
}
