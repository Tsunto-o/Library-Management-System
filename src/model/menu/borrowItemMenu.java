package model.menu;

import exception.LibraryException;
import model.Loan;
import model.items.Book;
import model.items.LibraryItem;
import model.items.itemMemory.ItemList;
import model.user.Member;
import model.user.User;
import model.user.userMemory.UsersList;

import java.time.LocalDate;
import java.util.Scanner;

import static app.main.circulationService;
import static app.main.menu;

public class borrowItemMenu {

    public static LibraryItem findItemById(ItemList listItem, int id) {
        for (LibraryItem item : listItem.getListItems()) {
            if (item.getStableId() == id) {
                return item;
            }
        }
        return null;
    }

    public static void borrowItemMenu(User session, UsersList listUser, ItemList listItem) {
        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "--- Borrow an Item ---\n\n");

        if (session instanceof Member) {
            Member member = (Member) session;
            int currentActiveLoans = 0;
            for (Loan l : circulationService.getActiveLoans()) {
                if (l.getBorrower().getStableId().equals(session.getStableId()) && !l.getReturned()) {
                    currentActiveLoans++;
                }
            }
            if (currentActiveLoans >= member.getActiveLoanLimit()) {
                System.out.println("You have reached your active loan limit (" + member.getActiveLoanLimit() + " items).");
                System.out.println("Please return an item before borrowing another one.");
                menu(session, listUser, listItem);
                return;
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the ID of the item you want to borrow (or 0 to cancel): ");
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

        // Seuls les livres peuvent être empruntés
        if (!(item instanceof Book)) {
            System.out.println("Item \"" + item.getTitle() + "\" is a " + item.getType() + ".");
            System.out.println("Magazines cannot be borrowed (in-library reading only).");
            menu(session, listUser, listItem);
            return;
        }

        Book book = (Book) item;

        if (!book.isAvailable()) {
            System.out.println("The book \"" + book.getTitle() + "\" is currently " + book.getStatus() + ".");
            System.out.println("You can reserve it from the main menu (Option 3).");
            menu(session, listUser, listItem);
            return;
        }

        try {
            LocalDate loanDate = LocalDate.now();
            LocalDate dueDate = loanDate.plusDays(14); // 2 semaines standard

            Loan loan = circulationService.borrowItem(session, book, loanDate, dueDate);
            System.out.println("\nSuccess! You borrowed: \"" + book.getTitle() + "\"");
            System.out.println("Loan ID: " + loan.getLoanId() + " | Due Date: " + dueDate);
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }

        menu(session, listUser, listItem);
    }
}