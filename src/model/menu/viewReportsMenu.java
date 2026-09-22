package model.menu;

import model.Loan;
import model.Reservation;
import model.items.Book;
import model.items.LibraryItem;
import model.items.itemMemory.ItemList;
import model.user.Admin;
import model.user.Librarian;
import model.user.User;
import model.user.userMemory.UsersList;
import ordering.DueDateComparator;

import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static app.main.circulationService;
import static app.main.menu;
import static app.main.readInputOption;

public class viewReportsMenu {

    public static void viewReportsMenu(User session, UsersList listUser, ItemList listItem) {
        if (session instanceof Librarian || session instanceof Admin) {
            staffReportsMenu(session, listUser, listItem);
        } else {
            memberReportsMenu(session, listUser, listItem);
        }
    }

    private static void staffReportsMenu(User session, UsersList listUser, ItemList listItem) {
        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "--- Library Reports ---\n" +
                "   1 - Active loans (sorted by due date)\n" +
                "   2 - Overdue loans\n" +
                "   3 - My borrowing activity\n" +
                "   4 - Library catalogue statistics\n" +
                "   5 - Reservation queues\n" +
                "   6 - Back to Main Menu\n\n");

        switch (readInputOption(1, 6)) {
            case 1:
                printActiveLoans();
                staffReportsMenu(session, listUser, listItem);
                break;
            case 2:
                printOverdueLoans();
                staffReportsMenu(session, listUser, listItem);
                break;
            case 3:
                printMyActivity(session);
                staffReportsMenu(session, listUser, listItem);
                break;
            case 4:
                printCatalogueStatistics(listItem, listUser);
                staffReportsMenu(session, listUser, listItem);
                break;
            case 5:
                printReservationQueues();
                staffReportsMenu(session, listUser, listItem);
                break;
            case 6:
                menu(session, listUser, listItem);
                break;
        }
    }

    private static void memberReportsMenu(User session, UsersList listUser, ItemList listItem) {
        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "--- My Reports ---\n" +
                "   1 - My borrowing activity\n" +
                "   2 - Library catalogue statistics\n" +
                "   3 - Back to Main Menu\n\n");

        switch (readInputOption(1, 3)) {
            case 1:
                printMyActivity(session);
                memberReportsMenu(session, listUser, listItem);
                break;
            case 2:
                printCatalogueStatistics(listItem, listUser);
                memberReportsMenu(session, listUser, listItem);
                break;
            case 3:
                menu(session, listUser, listItem);
                break;
        }
    }

    private static void printActiveLoans() {
        Predicate<Loan> notReturned = loan -> !loan.getReturned();
        List<Loan> active = circulationService.getActiveLoans().stream()
                .filter(notReturned)
                .sorted(new DueDateComparator())
                .collect(Collectors.toList());

        System.out.println("\n--- Active Loans (" + active.size() + ") ---");
        if (active.isEmpty()) {
            System.out.println("No active loans at this time.");
        } else {
            for (Loan l : active) {
                System.out.println(" • Loan #" + l.getLoanId() +
                        " | Borrower: " + l.getBorrower().getFirstName() + " " + l.getBorrower().getLastName() +
                        " (" + l.getBorrower().getStableId() + ")" +
                        " | Item: \"" + l.getItem().getTitle() + "\" (ID: " + l.getItem().getStableId() + ")" +
                        " | Due: " + l.getDueDate());
            }
        }
    }

    private static void printOverdueLoans() {
        LocalDate today = LocalDate.now();
        Predicate<Loan> isOverdue = loan -> !loan.getReturned() && loan.getDueDate().isBefore(today);
        List<Loan> overdue = circulationService.getActiveLoans().stream()
                .filter(isOverdue)
                .sorted(new DueDateComparator())
                .collect(Collectors.toList());

        System.out.println("\n--- Overdue Loans (" + overdue.size() + ") ---");
        if (overdue.isEmpty()) {
            System.out.println("No overdue loans. Everything is on time!");
        } else {
            for (Loan l : overdue) {
                System.out.println(" • [OVERDUE] Loan #" + l.getLoanId() +
                        " | Borrower: " + l.getBorrower().getFirstName() + " " + l.getBorrower().getLastName() +
                        " | Title: \"" + l.getItem().getTitle() + "\"" +
                        " | Due Date was: " + l.getDueDate());
            }
        }
    }

    private static void printMyActivity(User session) {
        System.out.println("\n--- My Activity for " + session.getFirstName() + " " + session.getLastName() + " ---");
        int count = 0;
        for (Loan l : circulationService.getActiveLoans()) {
            if (l.getBorrower().getStableId().equals(session.getStableId())) {
                count++;
                String state = l.getReturned() ? "RETURNED on " + l.getReturnDate() : "ACTIVE (Due: " + l.getDueDate() + ")";
                System.out.println(" • Loan #" + l.getLoanId() + " | Item: \"" + l.getItem().getTitle() + "\" | Status: " + state);
            }
        }
        if (count == 0) {
            System.out.println("You have no past or current loans recorded.");
        }
    }

    private static void printCatalogueStatistics(ItemList listItem, UsersList listUser) {
        int totalItems = 0;
        int bookCount = 0;
        int magazineCount = 0;
        int availableCount = 0;

        Iterator<LibraryItem> catalogueIterator = listItem.iterator();
        while (catalogueIterator.hasNext()) {
            LibraryItem item = catalogueIterator.next();
            totalItems++;
            if (item instanceof Book) {
                bookCount++;
                if (((Book) item).isAvailable()) {
                    availableCount++;
                }
            } else {
                magazineCount++;
            }
        }

        System.out.println("\n--- Catalogue Statistics ---");
        System.out.println(" • Total items: " + totalItems);
        System.out.println(" • Books: " + bookCount + " (" + availableCount + " available)");
        System.out.println(" • Magazines: " + magazineCount);
        System.out.println(" • Total registered users: " + listUser.getListUsers().size());
    }

    private static void printReservationQueues() {
        Map<Book, List<Reservation>> queues = circulationService.getReservationQueues();

        System.out.println("\n--- Reservation Queues ---");
        boolean anyQueue = false;
        for (Map.Entry<Book, List<Reservation>> entry : queues.entrySet()) {
            if (entry.getValue().isEmpty()) {
                continue;
            }
            anyQueue = true;
            System.out.println(" • \"" + entry.getKey().getTitle() + "\" (ID: " + entry.getKey().getStableId() + ")");
            for (Reservation r : entry.getValue()) {
                System.out.println("      - Position " + r.getQueuePosition() + " | " +
                        r.getMember().getFirstName() + " " + r.getMember().getLastName() +
                        " | State: " + r.getState());
            }
        }
        if (!anyQueue) {
            System.out.println("No active reservations.");
        }
    }
}
