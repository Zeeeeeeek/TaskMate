package me.zeeeeeeek.backend.services.user;

import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.models.user.dtos.UserCreationDTO;
import me.zeeeeeeek.backend.models.user.dtos.UserDTOMapper;
import me.zeeeeeeek.backend.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserDTOMapper userDtoMapper;

    private AutoCloseable closeable;
    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void createAndSave() {
        UserCreationDTO userCreationDTO = testCreationDto();
        userService.createAndSave(userCreationDTO);
        verify(userRepository).save(testUser());
    }

    @Test
    void getAll() {
        userService.getAll();
        verify(userRepository).findAll();//Verify that the method was called
    }

    @Test
    void userShouldBeSaved() {
        User underTest = testUser();
        this.userService.save(underTest);
        verify(userRepository).save(underTest);
    }

    @Test
    void userShouldBeCreated() {
        UserCreationDTO userCreationDTO = testCreationDto();
        User createdUser = userService.create(userCreationDTO);
        assertEquals(userCreationDTO.username(), createdUser.getUsername());
        assertEquals(userCreationDTO.password(), createdUser.getPassword());
        assertEquals(userCreationDTO.email(), createdUser.getEmail());
        assertEquals(userCreationDTO.firstName(), createdUser.getFirstName());
        assertEquals(userCreationDTO.lastName(), createdUser.getLastName());
    }
    private UserCreationDTO testCreationDto() {
        return new UserCreationDTO(
                "username",
                "password",
                "email@sss",
                "firstName",
                "lastName");
    }

    private User testUser() {
        return new User(
                "username",
                "password",
                "email@sss",
                "firstName",
                "lastName");
    }

    @Test
    void userCreationShouldThrowExceptionWhenDTOIsNull() {
        assertThrows(NullPointerException.class, () -> userService.create(null));
    }

    @Test
    void userToUserDto() {
        User user = testUser();
        this.userService
                .userToUserDto(user);
        verify(userDtoMapper).mapToDTO(user);
    }
}