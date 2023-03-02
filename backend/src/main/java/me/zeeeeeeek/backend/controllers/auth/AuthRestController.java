package me.zeeeeeeek.backend.controllers.auth;

import lombok.RequiredArgsConstructor;
import me.zeeeeeeek.backend.models.user.dtos.UserCreationDTO;
import me.zeeeeeeek.backend.models.user.dtos.UserLoginDto;
import me.zeeeeeeek.backend.services.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthRestController{

    private final AuthService authService;


    /**
     * Registers a new user from the given data transfer object.
     *
     * @param userCreationDTO the data transfer object for the user registration
     * @return the created user
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody UserCreationDTO userCreationDTO) {
        return ResponseEntity.ok(authService.register(userCreationDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UserLoginDto userLoginDTO) {
        return ResponseEntity.ok(authService.login(userLoginDTO));
    }

}
