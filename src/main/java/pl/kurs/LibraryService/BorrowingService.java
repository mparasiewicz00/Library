package pl.kurs.LibraryService;

import pl.kurs.LibraryModel.Book;
import pl.kurs.LibraryModel.User;
import pl.kurs.LibraryService.MyException.BookNotExistException;
import pl.kurs.LibraryService.MyException.BorrowerEmptyException;
import pl.kurs.LibraryService.MyException.UserFoundException;

import java.util.List;
import java.util.Optional;

public class BorrowingService {

    public static void borrowBook(Book book, User borrower) {
        try{
            Optional.of(book.getBorrower())
                    .ifPresent(x -> {
                        throw new UserFoundException("Borrower with ID: " + borrower.getUserId()+ " is found.");
                    });

        } catch (BorrowerEmptyException e) {
            book.setBorrower(borrower);
        }
    }

    public static Book findBookByID (List<Book> books, long bookId) throws BookNotExistException {

        return books.stream()
                .filter(book -> bookId == book.getBookId())
                .findFirst()
                .orElseThrow(BookNotExistException::new);
    }

    public static void printBookInfo (List<Book> books, long bookId) throws BookNotExistException {
        Book findedBook = books.stream()
                .filter(book -> book.getBookId() == bookId)
                .findFirst()
                .orElseThrow(BookNotExistException::new);

        try {
            Optional.ofNullable(findedBook.getBorrower())
                    .ifPresent(x -> System.out.println("Book borrowed by: " + findedBook.getBorrower()));
        } catch (BorrowerEmptyException e) {
            System.out.println("Book: " + findedBook.getTitle() + " is available to borrow");
            System.out.println("Book info: " + findedBook);
        }
    }

}
