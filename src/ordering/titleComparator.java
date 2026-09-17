package ordering;
import model.LibraryItem;


import java.util.Comparator;

public class titleComparator implements Comparator<LibraryItem> {
    @Override
    public int compare(LibraryItem firstItem, LibraryItem secondItem) {
        int result = firstItem.getTitle().compareTo(secondItem.getTitle());
        if (result == 0) {
            return Integer.compare(firstItem.getStableId(),secondItem.getStableId());
        }
        return result;

    }
}
