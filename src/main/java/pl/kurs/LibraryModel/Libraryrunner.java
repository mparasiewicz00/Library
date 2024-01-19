package pl.kurs.LibraryModel;

import pl.kurs.LibraryService.BorrowingService;

public class Libraryrunner {
    public static void main(String[] args) {

        User user = new User(11,"Mateusz", "Parasiewicz");
        Book book = new Book(1, "W pustyni i w puszczy", "Henryk Sienkiewicz");

        BorrowingService.borrowBook(book, user);

        System.out.println(book);

    }
}