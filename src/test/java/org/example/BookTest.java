package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookTest {

    private Book book;

    @BeforeEach
    public void setUp() {
        book = new Book("Title", "Author", "Genre", 10.0);
    }

    @Test
    public void testConstructor() {
        assertEquals("Title", book.getTitle());
        assertEquals("Author", book.getAuthor());
        assertEquals("Genre", book.getGenre());
        assertEquals(10.0, book.getPrice());
        assertTrue(book.getReviews().isEmpty(), "Reviews list should be empty on initialization");
    }

    @Test
    public void testSettersAndGetters() {
        book.setTitle("New Title");
        book.setAuthor("New Author");
        book.setGenre("New Genre");
        book.setPrice(20.0);

        assertEquals("New Title", book.getTitle());
        assertEquals("New Author", book.getAuthor());
        assertEquals("New Genre", book.getGenre());
        assertEquals(20.0, book.getPrice());
    }

    @Test
    public void testAddReview() {
        List<String> reviews = book.getReviews();
        reviews.add("Excellent book!");
        book.setReviews(reviews);

        assertEquals(1, book.getReviews().size());
        assertEquals("Excellent book!", book.getReviews().get(0));
    }

    @Test
    public void testSetReviews() {
        List<String> newReviews = new ArrayList<>();
        newReviews.add("Awesome book");
        book.setReviews(newReviews);

        assertEquals(1, book.getReviews().size());
        assertEquals("Awesome book", book.getReviews().get(0));
    }

    @Test
    public void testEdgeCaseEmptyTitle() {
        book.setTitle("");
        assertEquals("", book.getTitle(), "Title should be set to an empty string");
    }

    @Test
    public void testEdgeCaseNegativePrice() {
        book.setPrice(-5.0);
        assertEquals(-5.0, book.getPrice(), "Price should handle negative values correctly");
    }

    @Test
    public void testNullReviews() {
        book.setReviews(null);
        assertNull(book.getReviews(), "Reviews list should be null if set to null");
    }
}

