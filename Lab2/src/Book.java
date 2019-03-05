public class Book {
    private int mId;
    private String mTitle;
    private String mAuthor;
    private String mGenre;

    public Book() {

    }

    public Book(String mTitle, String mAuthor, String mGenre) {
        this.mTitle = mTitle;
        this.mAuthor = mAuthor;
        this.mGenre = mGenre;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String mAuthor) {
        this.mAuthor = mAuthor;
    }

    public String getGenre() {
        return mGenre;
    }

    public void setGenre(String mGenre) {
        this.mGenre = mGenre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Title='" + mTitle + '\'' +
                ", Author='" + mAuthor + '\'' +
                ", Genre='" + mGenre + '\'' +
                '}';
    }
}
