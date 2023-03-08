package me.zeeeeeeek.backend.services.auth;

import lombok.RequiredArgsConstructor;
import me.zeeeeeeek.backend.controllers.auth.AuthenticationResponse;
import me.zeeeeeeek.backend.models.user.Role;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.models.user.dtos.UserCreationDTO;
import me.zeeeeeeek.backend.models.user.dtos.UserLoginDto;
import me.zeeeeeeek.backend.repositories.UserRepository;
import me.zeeeeeeek.backend.services.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    /**
     * Registers a new user from the given data transfer object.
     * @param userCreationDTO the data transfer object for the user registration
     * @return the authentication response containing the JWT token
     * @throws IllegalArgumentException if the username or email already exists
     */
    public AuthenticationResponse register(UserCreationDTO userCreationDTO) {
        User user = new User(
                userCreationDTO.username(),
                passwordEncoder.encode(userCreationDTO.password()),
                userCreationDTO.email(),
                userCreationDTO.firstName(),
                userCreationDTO.lastName(),
                Role.USER
        );
        if(userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail()))
            throw new IllegalArgumentException("Username or email already exists");
        userRepository.save(user);
        String token = jwtService.generateJwtTokenWithoutExtraClaims(user);
        return new AuthenticationResponse(token);

    }

    /**
     * Authenticates a user using the AuthenticationManager.
     * @param userLoginDTO the data transfer object for the user login
     * @return the authentication response containing the JWT token
     */
    public AuthenticationResponse login(UserLoginDto userLoginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.username(),
                        userLoginDTO.password()
                )
        );
        //At this point, the user is authenticated
        User user = userRepository.findByUsername(userLoginDTO.username())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String jwtToken = jwtService.generateJwtTokenWithoutExtraClaims(user);
        return new AuthenticationResponse(jwtToken);
    }
}
