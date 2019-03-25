import java.util.regex.Pattern;

public class User {
    private int mId;
    private String mName;
    private String mEmail;
    private String mAddress;

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getAddres() {
        return mAddress;
    }

    public void setAddres(String mAddress) {
        this.mAddress = mAddress;
    }
}
