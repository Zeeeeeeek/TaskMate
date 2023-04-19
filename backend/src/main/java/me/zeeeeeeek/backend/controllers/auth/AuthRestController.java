package me.zeeeeeeek.backend.controllers.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
        try {
            return ResponseEntity.ok(authService.register(userCreationDTO));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UserLoginDto userLoginDTO) {
        return ResponseEntity.ok(authService.login(userLoginDTO));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestBody String refreshToken) {
        try {
            return ResponseEntity.ok((authService.refresh(refreshToken)));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
