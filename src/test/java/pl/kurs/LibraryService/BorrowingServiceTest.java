package pl.kurs.LibraryService;

import org.junit.jupiter.api.Test;
import pl.kurs.LibraryModel.Book;
import pl.kurs.LibraryModel.User;
import pl.kurs.LibraryService.MyException.BookNotExistException;
import pl.kurs.LibraryService.MyException.UserFoundException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BorrowingServiceTest {
    @Test
    void testBorrowBook_BookNotBorrowed() {
        Book book1 = new Book(1,"Dawno temu w Warszawie", "Żulczyk");
        User user1 = new User("Adam", "Abacki");

        BorrowingService.borrowBook(book1, user1);

        assertEquals(user1, book1.getBorrower());
    }
    @Test
    void testBorrowBook_BookBorrowed() {
        Book book2 = new Book(2,"Dawno temu w Warszawie", "Żulczyk");
        User user1 = new User("Adam", "Abacki");
        User user2 = new User("Bartosz", "Babacki");

        BorrowingService.borrowBook(book2, user1);

        assertThrows(UserFoundException.class , () -> BorrowingService.borrowBook(book2, user2));
    }

    @Test
    void testFindBookByID_BookExist() throws BookNotExistException {
        List<Book> library1 = List.of(
                new Book(3,"Ślepnac od świateł", "Żulczyk")
        );

        Book result =  BorrowingService.findBookByID(library1, 3);

        assertEquals(library1.get(0), result);

    }

    @Test
    void testFindBookByID_BookNotExist() {
        List<Book> library2 = List.of(
                new Book(4,"Ślepnac od świateł", "Żulczyk")
        );

        assertThrows(BookNotExistException.class , () -> BorrowingService.findBookByID(library2, 10));
    }

    @Test
    void testFindBookByID_EmptyList() {
        List<Book> library3 = new ArrayList<>();

        assertThrows(BookNotExistException.class , () -> BorrowingService.findBookByID(library3, 10));
    }


    @Test
    void testPrintBookInfo_BookWithoutBorrower() throws BookNotExistException {
        List<Book> library4 = List.of(
                new Book(5,"Shoe Dog", "Phil Knight")
        );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        BorrowingService.printBookInfo(library4, 5 );

        System.setOut(System.out);

        String expectedOutput = """
                Book: Shoe Dog is available to borrow
                Book info: Book{bookId=5, title='Shoe Dog', author='Phil Knight', borrower=null}
                """;

        assertEquals(expectedOutput, outputStream.toString());

    }

    @Test
    void testPrintBookInfo_BookWithBorrower() throws BookNotExistException {
        List<Book> library5 = List.of(
                new Book(6,"Shoe Dog 2", "Phil Knight")
        );
        User user1 = new User("Albert", "Einstein");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        BorrowingService.borrowBook(library5.get(0), user1);
        BorrowingService.printBookInfo(library5, 6 );

        System.setOut(System.out);

        String expectedOutput = "Book borrowed by: User{userId=1, firstName='Albert', lastName='Einstein', userEmail='alberteinstein@mylibrary.pl'}\n";

        assertEquals(expectedOutput, outputStream.toString());

    }


    @Test
    void testPrintBookInfo_BookNotExist() {
        List<Book> library6 = new ArrayList<>();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        assertThrows(BookNotExistException.class, () -> {
            BorrowingService.printBookInfo(library6, 10);
        });

        System.setOut(System.out);

        assertEquals("", outputStream.toString());
    }

}