package org.example;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private UserService userService;
    private User user;

    @BeforeAll
    public static void init() {
        System.out.println("Starting UserService tests...");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("All UserService tests completed.");
    }

    @BeforeEach
    public void setUp() {
        userService = new UserService();
        user = mock(User.class);
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Test completed.");
    }

    @Test
    public void testRegisterUserPositive() {
        when(user.getUsername()).thenReturn("john_doe");
        assertTrue(userService.registerUser(user), "User should be registered successfully");
    }

    @Test
    public void testRegisterUserNegative() {
        when(user.getUsername()).thenReturn("john_doe");
        userService.registerUser(user);
        assertFalse(userService.registerUser(user), "Registering the same user again should fail");
    }

    @Test
    public void testRegisterUserEdgeCase() {
        User nullUser = null;
        assertThrows(NullPointerException.class, () -> userService.registerUser(nullUser), "Null user should throw NullPointerException");
    }

    @Test
    public void testLoginUserPositive() {
        when(user.getUsername()).thenReturn("john_doe");
        when(user.getPassword()).thenReturn("password123");
        userService.registerUser(user);
        assertNotNull(userService.loginUser("john_doe", "password123"), "Login should succeed with correct credentials");
    }

    @Test
    public void testLoginUserNegative() {
        when(user.getUsername()).thenReturn("john_doe");
        when(user.getPassword()).thenReturn("password123");
        userService.registerUser(user);
        assertNull(userService.loginUser("john_doe", "wrongpassword"), "Login should fail with incorrect password");
    }

    @Test
    public void testLoginUserEdgeCase() {
        assertNull(userService.loginUser("unknown_user", "password123"), "Login should fail for non-existent user");
    }

    @Test
    public void testUpdateUserProfilePositive() {
        when(user.getUsername()).thenReturn("john_doe");
        when(user.getPassword()).thenReturn("password123");
        when(user.getEmail()).thenReturn("john@example.com");
        userService.registerUser(user);

        User updatedUser = mock(User.class);
        when(updatedUser.getUsername()).thenReturn("new_username");
        when(updatedUser.getPassword()).thenReturn("new_password");
        when(updatedUser.getEmail()).thenReturn("new_email@example.com");

        assertTrue(userService.updateUserProfile(user, "new_username", "new_password", "new_email@example.com"), "User profile should be updated successfully");
    }

    @Test
    public void testUpdateUserProfileNegative() {
        when(user.getUsername()).thenReturn("john_doe");
        when(user.getPassword()).thenReturn("password123");
        when(user.getEmail()).thenReturn("john@example.com");
        userService.registerUser(user);

        User updatedUser = mock(User.class);
        when(updatedUser.getUsername()).thenReturn("john_doe"); // Existing username

        assertFalse(userService.updateUserProfile(user, "john_doe", "new_password", "new_email@example.com"), "Updating to an existing username should fail");
    }

    @Test
    public void testUpdateUserProfileEdgeCase() {
        User nullUser = null;
        assertThrows(NullPointerException.class, () -> userService.updateUserProfile(nullUser, "new_username", "new_password", "new_email@example.com"), "Null user should throw NullPointerException");
    }
}