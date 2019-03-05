import java.util.Date;

public class Main {

    public static void main(String[] args) {

        LibraryService library = new LibraryService();

        library.addBook(new Book("Carte1", "Autor1", "Aventura"));
        library.addBook(new Book("Carte2", "Autor2", "Drama"));

        library.addUser(new User("Jane Doe"));

        library.listBooksThatExists();
        library.addReservation(0, 0, new Date(2019, 3, 5), new Date(2019, 3, 10));
        library.addReservation(0, 0, new Date(2019, 3, 5), new Date(2019, 3, 10));

    }
}
