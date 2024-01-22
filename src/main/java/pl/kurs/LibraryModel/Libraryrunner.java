package pl.kurs.LibraryModel;

import pl.kurs.LibraryService.BorrowingService;
import pl.kurs.LibraryService.MyException.BookNotExistException;

import java.util.List;

public class Libraryrunner {
    public static void main(String[] args) {
        try {

            User user = new User("Mateusz", "Parasiewicz");
            User user1 = new User("Andrzej", "Parasiewicz");
            User user2 = new User("Gacuś", "Parasiewicz");

            List<Book> library = List.of(
                    new Book(1, "W pustyni i w puszczy", "Henryk Sienkiewicz"),
                    new Book(2, "Ślepnąc od świateł", "Jakub Żulczyk"),
                    new Book(3, "Dawno temu w Warszawie", "Jakub Żulczyk"),
                    new Book(4, "Shoe Dog", "Phil Knight")
            );


            BorrowingService.borrowBook(library.get(3), user);
            BorrowingService.borrowBook(library.get(0), user2);
            BorrowingService.printBookInfo(library, 1);
            BorrowingService.printBookInfo(library, 3);

            System.out.println(BorrowingService.findBookByID(library, 10));


            List<Book> library2 = List.of(
                    new Book(6, "Shoe Dog 2", "Phil Knight")
            );
            User borrower = new User("Albert", "Einstein");


            BorrowingService.borrowBook(library2.get(0), borrower);
            BorrowingService.printBookInfo(library2, 6);
        } catch (BookNotExistException e) {
            e.printStackTrace();
        }
    }
}