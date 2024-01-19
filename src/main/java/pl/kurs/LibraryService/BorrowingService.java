package pl.kurs.LibraryService;

import pl.kurs.LibraryModel.Book;
import pl.kurs.LibraryModel.User;

import java.util.List;
import java.util.Optional;

public class BorrowingService {

    public static void borrowBook(Book book, User borrower) {
        try{
            book.getBorrower();
        } catch (BorrowerEmptyException e) {
            book.setBorrower(borrower);
        }
    }

    public static Book findBookByID (List<Book> books, long bookId) throws BookNotExistException {

        return null;
    }

}
