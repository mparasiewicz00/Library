package pl.kurs.LibraryService.MyException;

public class UserFoundException extends RuntimeException {

    public UserFoundException(String message) {
        super(message);
    }
}
