package me.zeeeeeeek.backend.services.user;

import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.models.user.dtos.UserCreationDTO;
import me.zeeeeeeek.backend.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Spring service that handles the business logic for the user.
 */
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Gets a user by its UUID.
     *
     * @param uuid the UUID of the user
     * @return the user
     * @throws IllegalArgumentException if the user is not found
     * @throws NullPointerException     if the UUID is null
     */
    public User getById(UUID uuid) {
        return this.userRepository
                .findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
    }

    /**
     * Creates a new user from the given data transfer object. This operation does not save the user in the database.
     *
     * @param userCreationDTO the data transfer object for the user creation
     * @return the created user
     */
    public User create(UserCreationDTO userCreationDTO) {
        return new User(userCreationDTO.username(),
                userCreationDTO.password(),
                userCreationDTO.email(),
                userCreationDTO.firstName(),
                userCreationDTO.lastName());
    }


    /**
     * Saves a user in the database.
     *
     * @param user the user to save
     * @throws NullPointerException if the user is null
     */
    public void save(User user) {
        Objects.requireNonNull(user);
        this.userRepository.save(user);
    }

    /**
     * Creates and saves a new user from the given data transfer object.
     * This operation is equivalent to calling {@link #create(UserCreationDTO)} and {@link #save(User)}.
     * <p>
     * This method is useful when you want to create a user and save it in the database in a single operation.
     * </p>
     *
     * @param userCreationDTO the data transfer object for the user creation
     * @return the created user
     */
    public User createAndSave(UserCreationDTO userCreationDTO) {
        User user = this.create(userCreationDTO);
        log.info("Created user {}", user);
        this.save(user);
        log.info("Saved user {}", user);
        return user;
    }


    public List<User> getAll() {
        List<User> users = new LinkedList<>();
        this.userRepository.findAll().forEach(users::add);
        return users;
    }
}
