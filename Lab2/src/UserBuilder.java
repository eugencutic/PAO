public class UserBuilder {
    private final User target = new User();

    public UserBuilder withName(String name) {
        target.setName(name);
        return this;
    }

    public UserBuilder withId(int id) {
        target.setId(id);
        return this;
    }

    public  UserBuilder withEmail(String email) {
        if (RegexTestPatternMatcher.validateEmail(email))
            target.setEmail(email);
        else
            System.out.println("Email not valid");
        return this;
    }

    public  UserBuilder withAddress(String address) {
        target.setAddres(address);
        return this;
    }

    public User build() {
        return target;
    }
}
