package me.zeeeeeeek.backend.models.user.dtos;

import lombok.NonNull;

/**
 * Data Transfer Object representing the information required to create a new user.
 *
 * @param username  the username of the user to create
 * @param password  the password of the user to create
 * @param email     the email of the user to create
 * @param firstName the first name of the user to create
 * @param lastName  the last name of the user to create
 */
public record UserCreationDTO(@NonNull String username,
                              @NonNull String password,
                              @NonNull String email,
                              @NonNull String firstName,
                              @NonNull String lastName) {
}