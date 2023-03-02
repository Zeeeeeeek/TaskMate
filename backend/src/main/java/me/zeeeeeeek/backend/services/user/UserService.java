package me.zeeeeeeek.backend.services.user;

import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.models.user.Role;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.models.user.dtos.UserCreationDTO;
import me.zeeeeeeek.backend.models.user.dtos.UserDTO;
import me.zeeeeeeek.backend.models.user.dtos.UserDTOMapper;
import me.zeeeeeeek.backend.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDtoMapper;

    public UserService(UserRepository userRepository, UserDTOMapper userDtoMapper) {
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
    public UserDTO createAndSave(UserCreationDTO userCreationDTO) {
        User user = this.create(userCreationDTO);
        log.info("Created user {}", user);
        return this.save(user);
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
    public UserDTO save(User user) {
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

    public User create(UserCreationDTO userCreationDTO) {
        Objects.requireNonNull(userCreationDTO);
        return new User(userCreationDTO.username(),
                userCreationDTO.password(),
                userCreationDTO.email(),
                userCreationDTO.firstName(),
                userCreationDTO.lastName(),
                Role.USER);
    }

    /**
     * Gets a UserDTO from a User instance.
     *
     * @param user the user
     * @return the user as a data transfer object
     * @throws NullPointerException if the user is null
     */
    public UserDTO userToUserDto(User user) {
        return this.userDtoMapper
                .mapToDTO(Objects.requireNonNull(user));
    }

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param username the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username " + username + " does not exist"));
    }
}
