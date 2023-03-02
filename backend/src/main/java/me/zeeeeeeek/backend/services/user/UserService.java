package me.zeeeeeeek.backend.services.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.models.user.User;
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
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;


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
