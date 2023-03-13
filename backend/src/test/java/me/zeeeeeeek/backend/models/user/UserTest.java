package me.zeeeeeeek.backend.models.user;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void userShouldBeCreated() {
        User u = new User("username", "password", "a@a.com", Role.USER);
        assertEquals("username", u.getUsername());
        assertEquals("password", u.getPassword());
        assertEquals("a@a.com", u.getEmail());
    }

    @Test
    void userShouldNotBeCreatedWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new User(null, "password", "email@a", Role.USER));
        assertThrows(NullPointerException.class, () -> new User("username", null, "email@a", Role.USER));
        assertThrows(NullPointerException.class, () -> new User("username", "password", null, Role.USER));
        assertThrows(NullPointerException.class, () -> new User("username", "password", "email@a", null));
    }

    @Test
    void userShouldNotBeCreatedWithEmptyParameters() {
        assertThrows(IllegalArgumentException.class, () -> new User("", "password", "email@a", Role.USER));
        assertThrows(IllegalArgumentException.class, () -> new User("username", "", "email@a", Role.USER));
        assertThrows(IllegalArgumentException.class, () -> new User("username", "password", "", Role.USER));
    }

    @Test
    void userShouldNotBeCreatedWithInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> new User("username", "password", "email", Role.USER));
    }


}
