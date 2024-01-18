package pl.kurs.LibraryService;

public class BorrowerEmptyException extends RuntimeException{
    public BorrowerEmptyException(String message) {
        super(message);
    }
}
