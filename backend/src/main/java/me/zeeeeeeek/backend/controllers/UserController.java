package me.zeeeeeeek.backend.controllers;

import me.zeeeeeeek.backend.models.user.User;
import me.zeeeeeeek.backend.services.user.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Test controller.
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    /**
     * Get all users.
     */
    @GetMapping
    public List<User> getAll() {
        return this.userService
                .getAll();
    }
}
