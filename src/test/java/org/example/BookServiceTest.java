package org.example;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    private BookService bookService;
    private User user;
    private Book book1;
    private Book book2;

    @BeforeAll
    public static void init() {
        System.out.println("Starting BookService tests...");
    }

    @AfterAll
    public static void tearDownAll() {
        System.out.println("All BookService tests completed.");
    }

    @BeforeEach
    public void setUp() {
        bookService = new BookService();
        user = mock(User.class);
        book1 = mock(Book.class);
        book2 = mock(Book.class);
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Test completed.");
    }

    @Test
    public void testAddBookPositive() {
        when(book1.getTitle()).thenReturn("Title1");
        assertTrue(bookService.addBook(book1), "Book should be added successfully");
    }

    @Test
    public void testAddBookNegative() {
        when(book1.getTitle()).thenReturn("Title1");
        bookService.addBook(book1);
        assertFalse(bookService.addBook(book1), "Adding the same book again should fail");
    }

    @Test
    public void testAddBookEdgeCase() {
        Book nullBook = null;
        assertThrows(NullPointerException.class, () -> bookService.addBook(nullBook), "Null book should throw NullPointerException");
    }

    @Test
    public void testRemoveBookPositive() {
        bookService.addBook(book1);
        assertTrue(bookService.removeBook(book1), "Book should be removed successfully");
    }

    @Test
    public void testRemoveBookNegative() {
        assertFalse(bookService.removeBook(book1), "Removing a non-existent book should fail");
    }

    @Test
    public void testRemoveBookEdgeCase() {
        Book nullBook = null;
        assertThrows(NullPointerException.class, () -> bookService.removeBook(nullBook), "Null book should throw NullPointerException");
    }

    @Test
    public void testSearchBookPositive() {
        when(book1.getTitle()).thenReturn("Title1");
        bookService.addBook(book1);
        List<Book> foundBooks = bookService.searchBook("Title1");
        assertEquals(1, foundBooks.size(), "Search should return one book");
        assertTrue(foundBooks.contains(book1), "Search should return the correct book");
    }

    @Test
    public void testSearchBookNegative() {
        List<Book> foundBooks = bookService.searchBook("Nonexistent Title");
        assertTrue(foundBooks.isEmpty(), "Search should return no books for a nonexistent title");
    }

    @Test
    public void testSearchBookEdgeCase() {
        when(book1.getGenre()).thenReturn("Programming");
        when(book2.getGenre()).thenReturn("Programming");
        bookService.addBook(book1);
        bookService.addBook(book2);
        List<Book> foundBooks = bookService.searchBook("Programming");
        assertEquals(2, foundBooks.size(), "Search should return all books with the 'Programming' genre");
    }

    @Test
    public void testPurchaseBookPositive() {
        bookService.addBook(book1);
        assertTrue(bookService.purchaseBook(user, book1), "Purchase should succeed if the book is in the database");
    }

    @Test
    public void testPurchaseBookNegative() {
        assertFalse(bookService.purchaseBook(user, book2), "Purchase should fail if the book is not in the database");
    }

    @Test
    public void testPurchaseBookEdgeCase() {
        Book nullBook = null;
        assertThrows(NullPointerException.class, () -> bookService.purchaseBook(user, nullBook), "Null book should throw NullPointerException");
    }

    @Test
    public void testAddBookReviewPositive() {
        when(user.getPurchasedBooks()).thenReturn(List.of(book1));
        bookService.addBook(book1);
        assertTrue(bookService.addBookReview(user, book1, "Great book!"), "Review should be added successfully");
    }

    @Test
    public void testAddBookReviewNegative() {
        when(user.getPurchasedBooks()).thenReturn(List.of());
        assertFalse(bookService.addBookReview(user, book2, "Not purchased"), "Review should fail if the book was not purchased by the user");
    }

    @Test
    public void testAddBookReviewEdgeCase() {
        Book nullBook = null;
        assertThrows(NullPointerException.class, () -> bookService.addBookReview(user, nullBook, "Review"), "Null book should throw NullPointerException");
    }
}

