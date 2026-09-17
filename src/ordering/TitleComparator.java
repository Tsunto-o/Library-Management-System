package ordering;
import model.items.LibraryItem;


import java.util.Comparator;

public class TitleComparator implements Comparator<LibraryItem> {
    @Override
    public int compare(LibraryItem firstItem, LibraryItem secondItem) {
        int result = firstItem.getTitle().compareTo(secondItem.getTitle());
        if (result == 0) {
            return firstItem.compareTo(secondItem);
        }
        return result;

    }
}
