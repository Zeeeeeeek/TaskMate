package me.zeeeeeeek.backend.models.user.dtos;

import lombok.NonNull;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.util.DTOMapper;
import org.springframework.stereotype.Component;

/**
 * Mapper for the user. Used to map a user into a user dto.
 */
@Component
public class UserDTOMapper implements DTOMapper<User, UserDTO> {
    /**
     * Map a user to a user dto.
     *
     * @param user the user to map
     * @return the user dto
     * @throws NullPointerException if the user is null
     */
    @Override
    public UserDTO mapToDTO(@NonNull User user) {
        return new UserDTO(user.getUsername(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName());
    }
}
