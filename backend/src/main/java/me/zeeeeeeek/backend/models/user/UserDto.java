package me.zeeeeeeek.backend.models.user;

/**
 * Data transfer object for the user.
 * @param username
 * @param email
 * @param firstName
 * @param lastName
 */
public record UserDto(String username, String email, String firstName, String lastName) {

}
