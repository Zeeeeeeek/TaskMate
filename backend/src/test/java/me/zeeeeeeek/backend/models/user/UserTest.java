package me.zeeeeeeek.backend.models.user;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void userShouldBeCreated() {
        User u = new User("username", "password", "a@a.com", "first", "last", Role.USER);
        assertEquals("username", u.getUsername());
        assertEquals("password", u.getPassword());
        assertEquals("a@a.com", u.getEmail());
        assertEquals("first", u.getFirstName());
        assertEquals("last", u.getLastName());
    }

    @Test
    void userShouldNotBeCreatedWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new User(null, "password", "email", "first", "last", Role.USER));
        assertThrows(NullPointerException.class, () -> new User("username", null, "email", "first", "last", Role.USER));
        assertThrows(NullPointerException.class, () -> new User("username", "password", null, "first", "last", Role.USER));
        assertThrows(NullPointerException.class, () -> new User("username", "password", "email", null, "last", Role.USER));
        assertThrows(NullPointerException.class, () -> new User("username", "password", "email", "first", null, Role.USER));
        assertThrows(NullPointerException.class, () -> new User("username", "password", "email", "first", "last", null));
    }

    @Test
    void userShouldNotBeCreatedWithEmptyParameters() {
        assertThrows(IllegalArgumentException.class, () -> new User("", "password", "email", "first", "last", Role.USER));
        assertThrows(IllegalArgumentException.class, () -> new User("username", "", "email", "first", "last", Role.USER));
        assertThrows(IllegalArgumentException.class, () -> new User("username", "password", "", "first", "last", Role.USER));
        assertThrows(IllegalArgumentException.class, () -> new User("username", "password", "email", "", "last", Role.USER));
        assertThrows(IllegalArgumentException.class, () -> new User("username", "password", "email", "first", "", Role.USER));
    }

    @Test
    void userShouldNotBeCreatedWithInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> new User("username", "password", "email", "first", "last", Role.USER));
    }


}
