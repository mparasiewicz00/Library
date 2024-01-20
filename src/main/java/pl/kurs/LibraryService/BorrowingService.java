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

        return books.stream()
                .filter(book -> bookId == book.getBookId())
                .findFirst()
                .orElseThrow(BookNotExistException::new);

    }

//    public static Optional<User> printBookInfo (List<Book> books, String bookName) throws BookNotExistException {
//        Book findedBook = books.stream()
//                .filter(book -> bookName.equals(book.getTitle()))
//                .findFirst()
//                .orElseThrow(BookNotExistException::new);
//
//        return Optional.ofNullable(findedBook.getBorrower())
//                .ifPresentOrElse(x -> System.out.println(findedBook.getBorrower()), () -> System.out.println(""));
//
//    }



}
