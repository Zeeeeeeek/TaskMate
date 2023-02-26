package me.zeeeeeeek.backend;

import lombok.extern.slf4j.*;
import me.zeeeeeeek.backend.models.user.dtos.*;
import me.zeeeeeeek.backend.services.user.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;

@SpringBootApplication
@Slf4j
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        log.info("Application is running on http://localhost:8080");
    }
    @Bean
    CommandLineRunner init(UserService userService) {
        return args -> {
            userService.createAndSave(new UserCreationDTO(
                    "Enrico",
                    "pass",
                    "a@a",
                    "Enrico",
                    "Fermi"
            ));
        };
    }
}
