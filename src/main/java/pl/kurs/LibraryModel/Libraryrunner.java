package pl.kurs.LibraryModel;

import pl.kurs.LibraryService.MyException.BookNotExistException;
import pl.kurs.LibraryService.BorrowingService;

import java.util.List;

public class Libraryrunner {
    public static void main(String[] args) throws BookNotExistException {

        User user = new User("Mateusz", "Parasiewicz");
        User user1 = new User("Andrzej", "Parasiewicz");
        User user2 = new User("Gacuś", "Parasiewicz");

        List<Book> library = List.of(
                new Book(1,"W pustyni i w puszczy", "Henryk Sienkiewicz"),
                new Book(2,"Ślepnąc od świateł", "Jakub Żulczyk"),
                new Book(3,"Dawno temu w Warszawie", "Jakub Żulczyk"),
                new Book(4,"Shoe Dog", "Phil Knight")
        );


        BorrowingService.borrowBook(library.get(3), user);
        BorrowingService.borrowBook(library.getFirst(), user2);
        BorrowingService.printBookInfo(library, 1);
        BorrowingService.printBookInfo(library, 3);

        try {
            System.out.println(BorrowingService.findBookByID(library, 10));
        } catch (BookNotExistException e) {
            System.out.println("Error. Book not exist.");
        }



        List<Book> library2 = List.of(
                new Book(6,"Shoe Dog 2", "Phil Knight")
        );
        User borrower = new User("Albert", "Einstein");


        BorrowingService.borrowBook(library2.getFirst(), borrower);
        BorrowingService.printBookInfo(library2, 6 );

    }
}