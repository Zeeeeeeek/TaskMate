package me.zeeeeeeek.backend.models.user.dtos;

/**
 * Data Transfer Object representing the information required to create a new user.
 *
 * @param username  the username of the user to create
 * @param password  the password of the user to create
 * @param email     the email of the user to create
 * @param firstName the first name of the user to create
 * @param lastName  the last name of the user to create
 */
public record UserCreationDto(String username, String password, String email, String firstName, String lastName) {}