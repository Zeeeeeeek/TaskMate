package me.zeeeeeeek.backend.models.user;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.UUID;

/**
 * This class represents a user identified by a unique id, a username,
 * a password, an email, a first name and a last name.
 */
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Slf4j
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true) @NonNull
    private String username;
    @Column @NonNull
    private String password;
    @Column(unique = true) @NonNull
    private String email;
    @Column @NonNull
    private String firstName;
    @Column @NonNull
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
        requireNonEmpty(username, password, email, firstName, lastName);
        this.email = validateEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        log.info("Created user {}", this);
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
