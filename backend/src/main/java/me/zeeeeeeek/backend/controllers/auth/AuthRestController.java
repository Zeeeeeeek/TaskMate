package me.zeeeeeeek.backend.controllers.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.models.user.dtos.UserCreationDTO;
import me.zeeeeeeek.backend.models.user.dtos.UserLoginDto;
import me.zeeeeeeek.backend.services.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthRestController {

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
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Logs in a user from the given data transfer object.
     *
     * @param userLoginDTO the data transfer object for the user login
     * @return the authentication response containing the JWT and refresh token
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody UserLoginDto userLoginDTO) {
        return ResponseEntity.ok(authService.login(userLoginDTO));
    }

    /**
     * Refreshes the JWT from the given refresh token. The refresh token is invalidated afterwords.
     *
     * @param refreshToken the refresh token
     * @return the authentication response containing the JWT and a new refresh token
     */
    @GetMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh(@RequestHeader("X-Refresh-Token") String refreshToken) {
        try {
            return ResponseEntity.ok((authService.refresh(refreshToken)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Logs out the user by invalidating the refresh token.
     *
     * @param refreshToken the refresh token
     * @return returns a 200-OK response if the refresh token was invalidated successfully or a 400-BAD REQUEST
     *          if the refresh token was invalid
     */
    @DeleteMapping("/refresh")
    public ResponseEntity<Void> logout(@RequestHeader("X-Refresh-Token") String refreshToken) {
        try {
            authService.logout(refreshToken);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
