package model.menu;

import exception.LibraryException;
import model.Reservation;
import model.items.Book;
import model.items.LibraryItem;
import model.items.itemMemory.ItemList;
import model.user.User;
import model.user.userMemory.UsersList;

import java.util.Scanner;

import static app.main.circulationService;
import static app.main.menu;

public class reserveItemMenu {

    public static LibraryItem findItemById(ItemList listItem, int id) {
        for (LibraryItem item : listItem.getListItems()) {
            if (item.getStableId() == id) {
                return item;
            }
        }
        return null;
    }

    public static void reserveItemMenu(User session, UsersList listUser, ItemList listItem) {
        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "--- Reserve an Item ---\n\n");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the book you want to reserve (or 0 to cancel): ");
        int itemId;
        try {
            itemId = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            menu(session, listUser, listItem);
            return;
        }

        if (itemId == 0) {
            menu(session, listUser, listItem);
            return;
        }

        LibraryItem item = findItemById(listItem, itemId);
        if (item == null) {
            System.out.println("Item with ID " + itemId + " not found in catalogue.");
            menu(session, listUser, listItem);
            return;
        }

        if (!(item instanceof Book)) {
            System.out.println("Item \"" + item.getTitle() + "\" is a " + item.getType() + ".");
            System.out.println("Only books can be reserved. Magazines cannot be reserved.");
            menu(session, listUser, listItem);
            return;
        }

        Book book = (Book) item;

        if (book.isAvailable()) {
            System.out.println("The book \"" + book.getTitle() + "\" is currently AVAILABLE.");
            System.out.println("You can borrow it directly (Option 1) without reserving it!");
            menu(session, listUser, listItem);
            return;
        }

        try {
            Reservation reservation = circulationService.reserveItem(book, session);
            System.out.println("\nReservation confirmed for: \"" + book.getTitle() + "\"");
            System.out.println("Reservation ID: " + reservation.getReservationId() + " | Position in queue: " + reservation.getQueuePosition());
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }

        menu(session, listUser, listItem);
    }
}
