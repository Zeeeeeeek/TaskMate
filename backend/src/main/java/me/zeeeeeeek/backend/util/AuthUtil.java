package me.zeeeeeeek.backend.util;

import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.services.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    final UserService userService;

    private AuthUtil(UserService userService) {
        this.userService = userService;
    }

    public User getUserFromAuthentication(Authentication authentication) {
        User temp = (User) authentication.getPrincipal();

        return userService
                .getUserByUsername(temp.getUsername());
    }
}
