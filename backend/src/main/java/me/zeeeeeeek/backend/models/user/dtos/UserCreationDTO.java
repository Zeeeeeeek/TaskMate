package me.zeeeeeeek.backend.models.user.dtos;

import jakarta.validation.constraints.Email;
import lombok.NonNull;

/**
 * Data Transfer Object representing the information required to create a new user.
 *
 * @param username  the username of the user to create
 * @param password  the password of the user to create
 * @param email     the email of the user to create
 */
public record UserCreationDTO(@NonNull String username,
                              @NonNull String password,
                              @Email @NonNull String email) {
}