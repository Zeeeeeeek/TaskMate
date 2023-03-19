package me.zeeeeeeek.backend;

import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.models.user.dtos.UserCreationDTO;
import me.zeeeeeeek.backend.services.auth.AuthService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        log.info("Application is running on http://localhost:8080");
    }

    @Bean
    public CommandLineRunner initUser(AuthService authService) {
        return (args) -> {
            authService.register(
                    new UserCreationDTO(
                            "admin",
                            "a",
                            "admin@admin"
                    )
            );
        };
    }


}
