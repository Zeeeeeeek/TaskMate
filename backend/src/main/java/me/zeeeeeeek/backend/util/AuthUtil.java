package me.zeeeeeeek.backend.util;

import me.zeeeeeeek.backend.models.user.User;
import org.springframework.security.core.Authentication;


public class AuthUtil {

    private AuthUtil() {
    }

    public static User getUserFromAuthentication(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }
}
