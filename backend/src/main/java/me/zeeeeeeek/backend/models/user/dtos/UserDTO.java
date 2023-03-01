package me.zeeeeeeek.backend.models.user.dtos;

import lombok.NonNull;

/**
 * Data transfer object representing information about a user.
 * @param username the username of the user
 * @param email the email of the user
 * @param firstName the first name of the user
 * @param lastName the last name of the user
 */
public record UserDTO(@NonNull String username,
                      @NonNull String email,
                      @NonNull String firstName,
                      @NonNull String lastName) {}
