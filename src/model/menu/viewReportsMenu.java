package model.menu;

import model.Loan;
import model.items.Book;
import model.items.LibraryItem;
import model.items.itemMemory.ItemList;
import model.user.User;
import model.user.userMemory.UsersList;
import ordering.DueDateComparator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static app.main.circulationService;
import static app.main.menu;
import static app.main.readInputOption;

public class viewReportsMenu {

    public static void viewReportsMenu(User session, UsersList listUser, ItemList listItem) {
        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "--- Library Reports ---\n" +
                "   1 - Active loans (sorted by due date)\n" +
                "   2 - Overdue loans\n" +
                "   3 - My borrowing activity\n" +
                "   4 - Library catalogue statistics\n" +
                "   5 - Back to Main Menu\n\n");

        switch (readInputOption(1, 5)) {
            case 1: {
                List<Loan> active = new ArrayList<>();
                for (Loan l : circulationService.getActiveLoans()) {
                    if (!l.getReturned()) {
                        active.add(l);
                    }
                }
                active.sort(new DueDateComparator());

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
                viewReportsMenu(session, listUser, listItem);
                break;
            }
            case 2: {
                List<Loan> overdue = new ArrayList<>();
                LocalDate today = LocalDate.now();
                for (Loan l : circulationService.getActiveLoans()) {
                    if (!l.getReturned() && l.getDueDate().isBefore(today)) {
                        overdue.add(l);
                    }
                }
                overdue.sort(new DueDateComparator());

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
                viewReportsMenu(session, listUser, listItem);
                break;
            }
            case 3: {
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
                viewReportsMenu(session, listUser, listItem);
                break;
            }
            case 4: {
                int totalItems = listItem.getListItems().size();
                int bookCount = 0;
                int magazineCount = 0;
                int availableCount = 0;

                for (LibraryItem item : listItem.getListItems()) {
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
                viewReportsMenu(session, listUser, listItem);
                break;
            }
            case 5:
                menu(session, listUser, listItem);
                break;
        }
    }
}
