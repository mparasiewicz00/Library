package pl.kurs.LibraryService;

import org.junit.jupiter.api.Test;
import pl.kurs.LibraryModel.Book;
import pl.kurs.LibraryModel.User;

import static org.junit.jupiter.api.Assertions.*;

class BorrowingServiceTest {
    @Test
    void borrowNotBorrowedBook() {
        Book book = new Book("Dawno temu w Warszawie", "Żulczyk");
        User user1 = new User("Adam", "Abacki");

        BorrowingService.borrowBook(book, user1);

        assertEquals(user1, book.getBorrower());
    }
    @Test
    void borrowBorrowedBook() {
        Book book = new Book("Dawno temu w Warszawie", "Żulczyk");
        User user1 = new User("Adam", "Abacki");
        User user2 = new User("Bartosz", "Babacki");

        BorrowingService.borrowBook(book, user1);

        assertThrows(UserFoundException.class , () -> BorrowingService.borrowBook(book, user2));
    }

}