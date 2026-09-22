package ordering;

import model.items.Book;
import model.items.LibraryItem;
import java.util.Comparator;

public class AuthorComparator implements Comparator<LibraryItem> {
    @Override
    public int compare(LibraryItem first, LibraryItem second) {
        String author1 = (first instanceof Book) ? ((Book) first).getAuthor() : "";
        String author2 = (second instanceof Book) ? ((Book) second).getAuthor() : "";

        int result = author1.compareToIgnoreCase(author2);
        if (result == 0) {
            // Tie breaker déterministe (par ID)
            return first.compareTo(second);
        }
        return result;
    }
}