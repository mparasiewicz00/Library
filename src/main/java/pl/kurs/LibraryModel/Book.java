package pl.kurs.LibraryModel;

import pl.kurs.LibraryService.MyException.BorrowerEmptyException;
import pl.kurs.LibraryService.MyException.UserFoundException;

import java.util.Objects;
import java.util.Optional;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private User borrower;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
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
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = Optional.ofNullable(borrower)
                .orElseThrow(() -> new UserFoundException("Book is borrowed now!"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(borrower, book.borrower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, borrower);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", borrower=" + borrower +
                '}';
    }
}
