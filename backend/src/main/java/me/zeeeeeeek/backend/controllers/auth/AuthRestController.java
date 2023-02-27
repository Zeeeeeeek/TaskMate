package me.zeeeeeeek.backend.controllers.auth;

import me.zeeeeeeek.backend.models.user.dtos.UserCreationDTO;
import me.zeeeeeeek.backend.models.user.dtos.UserDTO;
import me.zeeeeeeek.backend.services.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthRestController implements AuthController{

    private final UserService userService;

    public AuthRestController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Registers a new user from the given data transfer object.
     *
     * @param userCreationDTO the data transfer object for the user registration
     * @return the created user
     */
    @Override
    @PostMapping
    public UserDTO register(@RequestBody UserCreationDTO userCreationDTO) {
        System.out.println(userCreationDTO);
        return this.userService.createAndSave(userCreationDTO);
    }
}
