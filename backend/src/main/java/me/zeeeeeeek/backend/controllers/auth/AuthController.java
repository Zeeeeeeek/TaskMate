package me.zeeeeeeek.backend.controllers.auth;

import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.models.user.dtos.*;

/**
 * Defines the contract for a controller that manages authentication.
 */
public interface AuthController {

    /**
     * Registers a new user from the given data transfer object.
     *
     * @param userCreationDTO the data transfer object for the user registration
     * @return the created user
     */
    UserDto register(UserCreationDTO userCreationDTO);
}
