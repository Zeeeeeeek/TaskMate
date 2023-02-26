package me.zeeeeeeek.backend.controllers.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.zeeeeeeek.backend.models.user.*;
import me.zeeeeeeek.backend.models.user.dtos.*;
import me.zeeeeeeek.backend.repositories.*;
import me.zeeeeeeek.backend.services.user.UserService;

import org.aspectj.lang.annotation.*;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.context.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthRestControllerTest {
    @InjectMocks
    private AuthRestController authRestController;

    @Mock
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private MockMvc mockMvc;

    private String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(authRestController)
                .build();
    }

    @Test
    public void userRegistrationShouldReturnCorrectDTO() throws Exception {
        String uri = "/api/auth/";
        UserCreationDTO userCreationDTO = new UserCreationDTO(
                "test",
                "test",
                "test@test",
                "test",
                "test"
                );
        String jsonInput = mapToJson(userCreationDTO);
        MvcResult result = mockMvc
                .perform(MockMvcRequestBuilders.post(uri)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonInput))
                .andReturn();
        int resultStatus = result
                .getResponse()
                .getStatus();
        assertEquals(200, resultStatus);
        String responseContent = result
                .getResponse()
                .getContentAsString();
        System.out.println(responseContent);

    }

}
