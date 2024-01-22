package pl.kurs.LibraryService.MyException;

public class BorrowerEmptyException extends RuntimeException{
    public BorrowerEmptyException() {
    }

    public BorrowerEmptyException(String message) {
        super(message);
    }
}
