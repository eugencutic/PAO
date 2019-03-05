public class Book {
    private final int mId;
    private String mTitle;
    private String mAuthor;
    private String mGenre;

    public Book(int mId) {
        this.mId = mId;
    }

    public Book(int mId, String mTitle, String mAuthor, String mGenre) {
        this.mId = mId;
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mGenre = mGenre;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public void setmAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getmGenre() {
        return mGenre;
    }

    public void setmGenre(String mGenre) {
        this.mGenre = mGenre;
    }
}
