package me.zeeeeeeek.backend.services.user;

import me.zeeeeeeek.backend.models.user.*;
import me.zeeeeeeek.backend.models.user.dtos.*;

import java.util.*;

/**
 * This interface defines the contract for a class that manages the users.
 */
public interface UserManager {

    /**
     * Gets a user by its UUID.
     *
     * @param uuid the UUID of the user
     * @return the user
     * @throws IllegalArgumentException if the user does not exist
     * @throws NullPointerException if the UUID is null
     */
    User getById(UUID uuid);

    /**
     * Saves a user in the storage system.
     *
     * @param user the user to save
     * @throws NullPointerException if the user is null
     * @return the saved user as a data transfer object.
     */
    UserDto save(User user);

    /**
     * Creates a new user from the given data transfer object.
     *
     * @param userCreationDTO the data transfer object for the user creation
     * @return the created user
     * @throws NullPointerException if the data transfer object is null
     */
    User create(UserCreationDTO userCreationDTO);

    /**
     * Gets a UserDto from a User instance.
     *
     * @param user the user
     * @return the user as a data transfer object
     * @throws NullPointerException if the user is null
     */
    UserDto userToUserDto(User user);
}
