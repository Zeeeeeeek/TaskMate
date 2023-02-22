package me.zeeeeeeek.backend.models.user.dtos;

/**
 * Data Transfer Object representing the information required for user login.
 *
 * @param username the username of the user to login
 * @param password the password of the user to login
 */
public record UserLoginDto(String username, String password) {
}

