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
        if (Optional.ofNullable(book.getBorrower()).isEmpty())
            book.setBorrower(borrower);
        else
            throw new UserFoundException("Book already borrower");
    }

    public static Book findBookByID(List<Book> books, long bookId) throws BookNotExistException {
            return findBook(books, bookId);
    }

    public static void printBookInfo(List<Book> books, long bookId) {
        try {
            Book findedBook = findBook(books, bookId);
            Optional.ofNullable(findedBook.getBorrower())
                    .ifPresentOrElse(x -> {
                                System.out.println("Book borrowed by: " + findedBook.getBorrower());
                            },
                            () -> System.out.println("Book: " + findedBook.getTitle() + " is available to borrow" + "\n" + "Book info: " + findedBook));
        } catch (BookNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Book findBook(List<Book> books, long bookId) throws BookNotExistException {
        return books.stream()
                .filter(book -> bookId == book.getBookId())
                .findFirst()
                .orElseThrow(BookNotExistException::new);
    }

}
