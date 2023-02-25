package me.zeeeeeeek.backend.services.user;

import lombok.extern.slf4j.*;
import me.zeeeeeeek.backend.models.user.*;
import me.zeeeeeeek.backend.models.user.dtos.*;
import me.zeeeeeeek.backend.repositories.*;
import org.springframework.stereotype.*;

import java.util.*;

/**
 * Spring service that handles the business logic for the user.
 */
@Service
@Slf4j
public class UserService implements UserManager{

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;

    public UserService(UserRepository userRepository, UserDtoMapper userDtoMapper) {
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
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

    /**
     * Gets a user by its UUID.
     *
     * @param uuid the UUID of the user
     * @return the user
     * @throws IllegalArgumentException if the user does not exist
     * @throws NullPointerException     if the UUID is null
     */
    @Override
    public User getById(UUID uuid) {
        return this.userRepository
                .findById(Objects.requireNonNull(uuid))
                .orElseThrow(() -> new IllegalArgumentException("User with UUID " + uuid + " does not exist"));
    }

    /**
     * Saves a user in the storage system.
     *
     * @param user the user to save
     * @return the saved user as a data transfer object.
     * @throws NullPointerException if the user is null
     */
    @Override
    public UserDto save(User user) {
        this.userRepository.save(Objects.requireNonNull(user));
        return this.userToUserDto(user);
    }

    /**
     * Creates a new user from the given data transfer object.
     *
     * @param userCreationDTO the data transfer object for the user creation
     * @return the created user
     * @throws NullPointerException if the data transfer object is null
     */
    @Override
    public User create(UserCreationDTO userCreationDTO) {
        Objects.requireNonNull(userCreationDTO);
        return new User(userCreationDTO.username(),
                userCreationDTO.password(),
                userCreationDTO.email(),
                userCreationDTO.firstName(),
                userCreationDTO.lastName());
    }

    /**
     * Gets a UserDto from a User instance.
     *
     * @param user the user
     * @return the user as a data transfer object
     * @throws NullPointerException if the user is null
     */
    @Override
    public UserDto userToUserDto(User user) {
        return this.userDtoMapper.apply(Objects.requireNonNull(user));
    }
}
