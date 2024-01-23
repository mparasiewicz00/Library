package pl.kurs.LibraryService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import pl.kurs.LibraryModel.Book;
import pl.kurs.LibraryModel.User;
import pl.kurs.LibraryService.MyException.BookNotExistException;
import pl.kurs.LibraryService.MyException.UserFoundException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BorrowingServiceTest {
    @InjectMocks
    private BorrowingService borrowingService;
    private Book book;
    private User user;

//    @Mock
//    ISpeedSensor speedSensorMock;
//    @Mock
//    IRoofController roofControllerMock;
//    OnboardComputer onboardComputer;
//
//    @Before
//    public void init() {
//        MockitoAnnotations.openMocks(this);
//        onboardComputer = new OnboardComputer(speedSensorMock, roofControllerMock);
//    }


//    @BeforeEach
//    void beforeEach() {
//        user = mock(User.class);
//        book = mock(Book.class);
//    }

    @Test
    void testBorrowBook_BookNotBorrowed() {
        Book book = mock(Book.class);
        User user = mock(User.class);
        //given
        doReturn(1).when(user).getUserId();
        doReturn("Adam").when(user).getFirstName();
        doReturn("Kowalski").when(user).getLastName();

//        Book book1 = new Book(1, "Dawno temu w Warszawie", "Żulczyk");

        //when
        BorrowingService.borrowBook(book, user);

        //then
        assertAll(
                () -> verify(book, atMostOnce()).setBorrower(user),
                () -> verify(book, times(1)).getBorrower()
        );
    }
    @Test
    void testBorrowBook_BookBorrowed() {
        Book book = mock(Book.class);
        User user = mock(User.class);
        User user1 = mock(User.class);

        doReturn(1).when(user).getUserId();
        doReturn("Adam").when(user).getFirstName();
        doReturn("Kowalski").when(user).getLastName();

        when(book.getBorrower()).thenReturn(mock(User.class));

        assertThrows(UserFoundException.class, () -> BorrowingService.borrowBook(book, user1));

        verifyNoInteractions(user1);

    }

    @Spy
    List<Book> libraryMock = new ArrayList<>();
    @Test
    void testFindBookByID_BookExist() throws BookNotExistException {
        Book bookForMockLibrary = mock(Book.class);
        libraryMock.add(bookForMockLibrary);

        doReturn(3).when(bookForMockLibrary).getBookId();
        doReturn("Ślepnąc od świateł").when(bookForMockLibrary).getTitle();
        doReturn("Jakub Żulczyk").when(bookForMockLibrary).getAuthor();

        Book result = BorrowingService.findBookByID(libraryMock, 3);
        assertAll(
                () -> assertEquals(bookForMockLibrary, result),
                () -> verify(bookForMockLibrary, times(1)).getBookId()
        );
    }

    @Test
    void testFindBookByID_BookNotExist() {

        Book bookWithDifferentID = mock(Book.class);
        libraryMock.add(bookWithDifferentID);

        assertThrows(BookNotExistException.class, () -> BorrowingService.findBookByID(libraryMock, 10));
    }

    @Test
    void testFindBookByID_EmptyList() {
        List<Book> library3 = mock(List.class);

        assertThrows(BookNotExistException.class, () -> BorrowingService.findBookByID(library3, 10));
    }


    @Test
    void testPrintBookInfo_BookWithoutBorrower() throws BookNotExistException {
        List<Book> library4 = List.of(
                new Book(5,"Shoe Dog", "Phil Knight")
        );

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        BorrowingService.printBookInfo(library4, 5 );

        System.setOut(System.out);

        String expectedOutput = """
                Book: Shoe Dog is available to borrow
                Book info: Book{bookId=5, title='Shoe Dog', author='Phil Knight', borrower=null}
                """;

        assertEquals(expectedOutput, outputStream.toString());

    }

    @Test
    void testPrintBookInfo_BookWithBorrower() throws BookNotExistException {
        List<Book> library5 = List.of(
                new Book(6,"Shoe Dog 2", "Phil Knight")
        );
        User user1 = new User("Albert", "Einstein");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        BorrowingService.borrowBook(library5.get(0), user1);
        BorrowingService.printBookInfo(library5, 6 );

        System.setOut(System.out);

        String expectedOutput = "Book borrowed by: User{userId=1, firstName='Albert', lastName='Einstein', userEmail='alberteinstein@mylibrary.pl'}\n";

        assertEquals(expectedOutput, outputStream.toString());

    }


    @Test
    void testPrintBookInfo_BookNotExist() {
        List<Book> library6 = new ArrayList<>();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        assertThrows(BookNotExistException.class, () -> {
            BorrowingService.printBookInfo(library6, 10);
        });

        System.setOut(System.out);

        assertEquals("", outputStream.toString());
    }

}