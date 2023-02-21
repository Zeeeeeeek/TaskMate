package me.zeeeeeeek.backend.models.user;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void userShouldBeCreated() {
        User u = new User("username", "password", "a@a.com", "first", "last");
        assertEquals("username", u.getUsername());
        assertEquals("password", u.getPassword());
        assertEquals("a@a.com", u.getEmail());
        assertEquals("first", u.getFirstName());
        assertEquals("last", u.getLastName());
    }

    @Test
    void userShouldNotBeCreatedWithNullParameters() {
        assertThrows(NullPointerException.class, () -> new User(null, "password", "email", "first", "last"));
        assertThrows(NullPointerException.class, () -> new User("username", null, "email", "first", "last"));
        assertThrows(NullPointerException.class, () -> new User("username", "password", null, "first", "last"));
        assertThrows(NullPointerException.class, () -> new User("username", "password", "email", null, "last"));
        assertThrows(NullPointerException.class, () -> new User("username", "password", "email", "first", null));
    }

    @Test
    void userShouldNotBeCreatedWithEmptyParameters() {
        assertThrows(IllegalArgumentException.class, () -> new User("", "password", "email", "first", "last"));
        assertThrows(IllegalArgumentException.class, () -> new User("username", "", "email", "first", "last"));
        assertThrows(IllegalArgumentException.class, () -> new User("username", "password", "", "first", "last"));
        assertThrows(IllegalArgumentException.class, () -> new User("username", "password", "email", "", "last"));
        assertThrows(IllegalArgumentException.class, () -> new User("username", "password", "email", "first", ""));
    }

    @Test
    void userShouldNotBeCreatedWithInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> new User("username", "password", "email", "first", "last"));
    }


}
