package model.menu;

import model.items.LibraryItem;
import model.items.itemMemory.ItemList;
import model.user.User;
import model.user.userMemory.UsersList;
import ordering.AuthorComparator;
import ordering.PublicationDateComparator;
import ordering.TitleComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static app.main.menu;
import static app.main.readInputOption;

public class browseAndSortMenu {

    public static void browseAndSortMenu(User session, UsersList listUser, ItemList listItem) {
        if (listItem.getListItems().isEmpty()) {
            System.out.println("\nCatalogue is empty.");
            menu(session, listUser, listItem);
            return;
        }

        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "Catalogue - Choose display order :\n" +
                "   1 - Default / Natural Order (by ID)\n" +
                "   2 - Sort by Title\n" +
                "   3 - Sort by Publication Date\n" +
                "   4 - Sort by Author (Books)\n" +
                "   5 - Back to Main Menu\n\n");

        List<LibraryItem> sortedList = new ArrayList<>(listItem.getListItems());

        switch (readInputOption(1, 5)) {
            case 1:
                Collections.sort(sortedList); // Ordre naturel (Comparable -> ID)
                break;
            case 2:
                sortedList.sort(new TitleComparator());
                break;
            case 3:
                sortedList.sort(new PublicationDateComparator());
                break;
            case 4:
                sortedList.sort(new AuthorComparator());
                break;
            case 5:
                menu(session, listUser, listItem);
                return;
        }

        System.out.println("\n--- Items in Catalogue (" + sortedList.size() + ") ---");
        for (LibraryItem item : sortedList) {
            System.out.println(" • " + item.getDisplayInfo());
        }

        // Back to the menu after
        menu(session, listUser, listItem);
    }
}
