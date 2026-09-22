package ordering;

import model.items.LibraryItem;
import java.util.Comparator;

public class PublicationDateComparator implements Comparator<LibraryItem> {
    @Override
    public int compare(LibraryItem first, LibraryItem second) {
        int result = first.getPublicationData().compareTo(second.getPublicationData());
        if (result == 0) {
            // Tie breaker déterministe obligatoire (par ID)
            return first.compareTo(second);
        }
        return result;
    }
}