package me.zeeeeeeek.backend.services.user;

import me.zeeeeeeek.backend.repositories.*;
import org.junit.jupiter.api.*;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class UserServiceTest {

    @Mock private UserRepository userRepository;

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
    }

    @Test
    void getAll() {
        userService.getAll();
        verify(userRepository).findAll();//Verify that the method was called
    }

    @Test
    void getById() {
    }

    @Test
    void save() {
    }

    @Test
    void create() {
    }

    @Test
    void userToUserDto() {
    }
}