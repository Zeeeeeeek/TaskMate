package me.zeeeeeeek.backend.services.user;

import me.zeeeeeeek.backend.models.user.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Spring service that handles the business logic for the user.
 */
@Service
public class UserService {

    /**
     * Gets a user by its UUID.
     *
     * @param uuid the UUID of the user
     * @return the user
     * @throws IllegalArgumentException if the user is not found
     * @throws NullPointerException     if the UUID is null
     */
    public User getById(UUID uuid) {
        //Todo implement a user retrieval from the database using the UUID
        return new User("aaa", "bbb", "css@ca", "ddd", "eee");
    }
}
