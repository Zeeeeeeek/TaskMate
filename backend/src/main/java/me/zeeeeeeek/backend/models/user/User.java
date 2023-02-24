package me.zeeeeeeek.backend.models.user;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

/**
 * This class represents a user identified by a unique id, a username,
 * a password, an email, a first name and a last name.
 */
@NoArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Slf4j
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    /**
     * Create a new user.
     *
     * @param username  the username of the user
     * @param password  the password of the user
     * @param email     the email of the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @throws NullPointerException     if any of the parameters is null
     * @throws IllegalArgumentException if any of the parameters is empty
     * @throws IllegalArgumentException if the email is invalid
     */
    public User(String username, String password, String email, String firstName, String lastName) {
        requireNonNull(username, password, email, firstName, lastName);
        requireNonEmpty(username, password, email, firstName, lastName);
        this.email = validateEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        log.info("Created user {}", this);
    }

    @SafeVarargs
    private <T> void requireNonNull(T... args) {
        Arrays.stream(args)
                .forEach(Objects::requireNonNull);
    }

    private void requireNonEmpty(String... args) {
        Arrays.stream(args)
                .forEach(arg -> {
                    if (arg.isEmpty()) {
                        log.error("Argument cannot be empty");
                        throw new IllegalArgumentException("Argument cannot be empty");
                    }
                });
    }

    private String validateEmail(String email) {
        if (!email.matches(("^[a-zA-Z0-9_!#$%&'*+=?`{|}~^.-]+@[a-zA-Z0-9.-]+$"))) {
            log.error("Invalid email: {}", email);
            throw new IllegalArgumentException("Invalid email");
        }
        return email;
    }
}
