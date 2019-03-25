import sun.java2d.pipe.SpanShapeRenderer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class LibraryService {
    private ArrayList<User> mUsers;
    private ArrayList<Reservation> mReservations;
    private ArrayList<Book> mBooks;

    private static int mBookId = 0;
    private static int mUserId = 0;

    private LibraryService instance = null;

    private LibraryService() {
        mUsers = new ArrayList<>();
        mReservations = new ArrayList<>();
        mBooks = new ArrayList<>();
    }

    public LibraryService getInstance() {
        if (instance == null)
            instance = new LibraryService();
        return instance;
    }

    public void addBook(Book book) {
        book.setId(mBookId);
        mBooks.add(book);
        mBookId++;
    }

    public void addUser(User user) {
        user.setId(mUserId);
        mUsers.add(user);
        mUserId++;
    }

    public void listBooksThatExists() {
        for (Book book : mBooks) {
            System.out.println(book.toString());
        }
    }

    public void addReservation(int userId, int bookId, Date startDate, Date endDate) {
        User user = getUserById(userId);
        Book book = getBookById(bookId);

        if (user == null) {
            System.out.println("Userul nu exista.");
            return;
        }
        if (book == null) {
            System.out.println("Cartea nu exista.");
            return;
        }

        boolean isAvailable = checkAvailability(book, startDate, endDate);
        if (!isAvailable)
            return;

        Reservation res = new Reservation(user, startDate, endDate, book);
        mReservations.add(res);

        System.out.println("Rezervare adaugata.");
    }

    private boolean checkAvailability(Book book, Date startDate, Date endDate) {
        boolean isAvailable = false;
        SimpleDateFormat formatter = new SimpleDateFormat("dd-mm-yyyy");
        for (Reservation bookRes : mReservations) {
            if (bookRes.getBook().getId() == book.getId()) {
                if (startDate.before(bookRes.getEndDate())) {
                    if (endDate.after(bookRes.getStartDate())) {
                        System.out.println("Cartea este indisponibila intre " +
                                            formatter.format(bookRes.getStartDate()) + " si " +
                                            formatter.format(bookRes.getEndDate()) + ".");
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private Book getBookById(int bookId) {
        for (Book book : mBooks) {
            if (book.getId() == bookId)
                return book;
        }
        return null;
    }

    private User getUserById(int userId) {
        for (User user : mUsers) {
            if (user.getId() == userId)
                return user;
        }
        return null;
    }

}
