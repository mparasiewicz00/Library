package pl.kurs.LibraryModel;

import pl.kurs.LibraryService.BookNotExistException;
import pl.kurs.LibraryService.BorrowingService;

import java.util.List;

public class Libraryrunner {
    public static void main(String[] args) throws BookNotExistException {

        User user = new User("Mateusz", "Parasiewicz");
        List<Book> library = List.of(
                new Book("W pustyni i w puszczy", "Henryk Sienkiewicz"),
                new Book("W pustyni i w puszczy", "Henryk Sienkiewicz"),
                new Book("W pustyni i w puszczy", "Henryk Sienkiewicz"),
                new Book("W pustyni i w puszczy", "Henryk Sienkiewicz"),
                new Book("W pustyni i w puszczy", "Henryk Sienkiewicz")
        );

        BorrowingService.borrowBook(library.getFirst(), user);

        System.out.println(BorrowingService.findBookByID(library, 1000));

    }
}