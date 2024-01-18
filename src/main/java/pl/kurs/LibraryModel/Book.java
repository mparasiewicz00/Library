package pl.kurs.LibraryModel;

import pl.kurs.LibraryService.BorrowerEmptyException;

import java.util.Optional;

public class Book {
    private int bookId;
    private String title;
    private String author;
    public User borrower;
    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.borrower = null;
    }

    public int getBookId() {
        return bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public User getBorrower() {
        Optional<User> optionalUser = Optional.ofNullable(borrower);
        return optionalUser.orElseThrow(() -> new BorrowerEmptyException("Book is not borrowed now"));
    }
    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }



}
