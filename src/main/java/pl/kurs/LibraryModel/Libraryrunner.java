package pl.kurs.LibraryModel;

import pl.kurs.LibraryService.BookNotExistException;
import pl.kurs.LibraryService.BorrowingService;

import java.util.List;

public class Libraryrunner {
    public static void main(String[] args) throws BookNotExistException {

        User user = new User("Mateusz", "Parasiewicz");
        User user1 = new User("Andrzej", "Parasiewicz");
        User user2 = new User("Gacuś", "Parasiewicz");

        List<Book> library = List.of(
                new Book("W pustyni i w puszczy", "Henryk Sienkiewicz"),
                new Book("W pustyni i w puszczy1", "Henryk Sienkiewicz"),
                new Book("W pustyni i w puszczy2", "Henryk Sienkiewicz"),
                new Book("W pustyni i w puszczy3", "Henryk Sienkiewicz"),
                new Book("W pustyni i w puszczy4", "Henryk Sienkiewicz")
        );


        BorrowingService.borrowBook(library.getFirst(), user1);


//        BorrowingService.borrowBook(library.getFirst(), user);
//
//        library.getFirst().setBorrower(user2);
//
//        System.out.println(library.getFirst());

//        System.out.println(BorrowingService.findBookByID(library, 1001));
//
////        library.forEach(System.out::println);
////        System.out.println(user);
////        System.out.println(user1);
////        System.out.println(user2);
//
//        BorrowingService.printBookInfo(library, 1001);


    }
}