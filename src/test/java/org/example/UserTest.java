package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("john_doe", "password123", "john@example.com");
    }

    @Test
    public void testConstructor() {
        assertEquals("john_doe", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("john@example.com", user.getEmail());
        assertTrue(user.getPurchasedBooks().isEmpty(), "Purchased books list should be empty on initialization");
    }

    @Test
    public void testSettersAndGetters() {
        user.setUsername("jane_doe");
        user.setPassword("newpassword");
        user.setEmail("jane@example.com");

        assertEquals("jane_doe", user.getUsername());
        assertEquals("newpassword", user.getPassword());
        assertEquals("jane@example.com", user.getEmail());
    }

    @Test
    public void testAddPurchasedBook() {
        Book book = new Book("Title", "Author", "Genre", 10.0);
        user.getPurchasedBooks().add(book);

        assertEquals(1, user.getPurchasedBooks().size());
        assertEquals(book, user.getPurchasedBooks().get(0));
    }

    @Test
    public void testSetPurchasedBooks() {
        List<Book> purchasedBooks = new ArrayList<>();
        Book book = new Book("Title", "Author", "Genre", 10.0);
        purchasedBooks.add(book);
        user.setPurchasedBooks(purchasedBooks);

        assertEquals(1, user.getPurchasedBooks().size());
        assertEquals(book, user.getPurchasedBooks().get(0));
    }

    @Test
    public void testEdgeCaseEmptyUsername() {
        user.setUsername("");
        assertEquals("", user.getUsername(), "Username should be set to an empty string");
    }

    @Test
    public void testEdgeCaseNullPassword() {
        user.setPassword(null);
        assertNull(user.getPassword(), "Password should be set to null");
    }

    @Test
    public void testNullPurchasedBooks() {
        user.setPurchasedBooks(null);
        assertNull(user.getPurchasedBooks(), "Purchased books list should be null if set to null");
    }
}

