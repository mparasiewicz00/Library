package pl.kurs.LibraryService.MyException;

public class BookNotExistException extends Exception {
    public BookNotExistException() {
    }

    public BookNotExistException(String message) {
        super(message);
    }
}
