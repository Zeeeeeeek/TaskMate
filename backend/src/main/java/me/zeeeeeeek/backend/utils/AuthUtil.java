package me.zeeeeeeek.backend.utils;

import lombok.RequiredArgsConstructor;
import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.services.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthUtil {

    private final UserService userService;

    public User getUserFromAuthentication(Authentication authentication) {
        String username = (String) authentication.getPrincipal();
        return userService
                .getUserByUsername(username);
    }
}
