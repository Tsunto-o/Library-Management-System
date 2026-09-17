package ordering;

import model.LibraryItem;

import java.util.Comparator;

public class PublicationDataComparator implements Comparator<LibraryItem> {
    @Override
    public int compare(LibraryItem first, LibraryItem second) {
        int result = first.getPublicationData().compareTo(second.getPublicationData());
        if (result == 0) {
            return first.compareTo(second);
        }
        return result;
    }

}
