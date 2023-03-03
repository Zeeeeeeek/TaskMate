package me.zeeeeeeek.backend.models.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import me.zeeeeeeek.backend.models.tasks.collections.TaskList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * This class represents a user identified by a unique id, a username,
 * a password, an email, a first name and a last name.
 */
@NoArgsConstructor
@Data
@Slf4j
@Entity(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true) @NonNull
    private String username;
    @Column @NonNull @JsonIgnore
    private String password;
    @Column(unique = true) @NonNull
    private String email;
    @Column @NonNull
    private String firstName;
    @Column @NonNull
    private String lastName;

    @OneToMany(mappedBy = "owner")
    private List<TaskList> taskLists;

    @Enumerated(EnumType.STRING) @JsonIgnore @NonNull
    private Role role;

    /**
     * Create a new user.
     *
     * @param username  the username of the user
     * @param password  the password of the user
     * @param email     the email of the user
     * @param firstName the first name of the user
     * @param lastName  the last name of the user
     * @param role      the role of the user
     * @throws NullPointerException     if any of the parameters is null
     * @throws IllegalArgumentException if any of the parameters is empty
     * @throws IllegalArgumentException if the email is invalid
     */
    public User(String username, String password, String email, String firstName, String lastName,@NonNull Role role) {
        requireNonEmpty(username, password, email, firstName, lastName);
        this.email = validateEmail(email);
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.role = role;
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

    /**
     * Returns the authorities granted to the user. Cannot return <code>null</code>.
     *
     * @return the authorities, sorted by natural key (never <code>null</code>)
     */
    @Override @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(
                new SimpleGrantedAuthority(role.name())
        );
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user's account is valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is locked or unlocked. A locked user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is not locked, <code>false</code> otherwise
     */
    @Override @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) has expired. Expired
     * credentials prevent authentication.
     *
     * @return <code>true</code> if the user's credentials are valid (ie non-expired),
     * <code>false</code> if no longer valid (ie expired)
     */
    @Override @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled or disabled. A disabled user cannot be
     * authenticated.
     *
     * @return <code>true</code> if the user is enabled, <code>false</code> otherwise
     */
    @Override @JsonIgnore
    public boolean isEnabled() {
        return true;
    }



    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId()) && getUsername().equals(user.getUsername()) && getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getEmail());
    }
}
