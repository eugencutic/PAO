import java.util.Date;

public class Reservation {
    private User mUser;
    private Date mStartDate;
    private Date mEndDate;
    private Book mBook;

    public Reservation() {
    }

    public Reservation(User mUser, Date mStartDate, Date mEndDate, Book mBook) {
        this.mUser = mUser;
        this.mStartDate = mStartDate;
        this.mEndDate = mEndDate;
        this.mBook = mBook;
    }

    public User getUser() {
        return mUser;
    }

    public void setUser(User mUser) {
        this.mUser = mUser;
    }

    public Date getStartDate() {
        return mStartDate;
    }

    public void setStartDate(Date mStartDate) {
        this.mStartDate = mStartDate;
    }

    public Date getEndDate() {
        return mEndDate;
    }

    public void setEndDate(Date mEndDate) {
        this.mEndDate = mEndDate;
    }

    public Book getBook() {
        return mBook;
    }

    public void setBook(Book mBook) {
        this.mBook = mBook;
    }
}
