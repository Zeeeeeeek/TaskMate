package me.zeeeeeeek.backend.models.user.dtos;

import me.zeeeeeeek.backend.models.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDTOMapperTest {

    @Test
    void apply() {
        UserDTOMapper userDtoMapper = new UserDTOMapper();
        User user = new User(
                        "username",
                        "password",
                        "email@sss",
                        "firstName",
                        "lastName");
        UserDTO userDto = userDtoMapper.apply(user);
        assertEquals(user.getUsername(), userDto.username());
        assertEquals(user.getEmail(), userDto.email());
        assertEquals(user.getFirstName(), userDto.firstName());
        assertEquals(user.getLastName(), userDto.lastName());
    }
}