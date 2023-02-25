package me.zeeeeeeek.backend.models.user.dtos;

import me.zeeeeeeek.backend.models.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * Mapper for the user. Used to map a user into a user dto.
 */
@Component
public class UserDtoMapper implements Function<User, UserDto> {
    /**
     * Map a user to a user dto.
     *
     * @param user the user to map
     * @return the user dto
     */
    @Override
    public UserDto apply(User user) {
        return new UserDto(user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName());
    }
}
