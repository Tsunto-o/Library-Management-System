package model.menu;

import exception.LibraryException;
import model.Loan;
import model.items.itemMemory.ItemList;
import model.user.User;
import model.user.userMemory.UsersList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static app.main.circulationService;
import static app.main.menu;

public class returnItemMenu {

    public static void returnItemMenu(User session, UsersList listUser, ItemList listItem) {
        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "--- Return an Item ---\n\n");

        List<Loan> userLoans = new ArrayList<>();
        for (Loan l : circulationService.getActiveLoans()) {
            if (l.getBorrower().getStableId().equals(session.getStableId()) && !l.getReturned()) {
                userLoans.add(l);
            }
        }

        if (userLoans.isEmpty()) {
            System.out.println("You have no active loans to return.");
            menu(session, listUser, listItem);
            return;
        }

        System.out.println("Your active loans:");
        for (Loan l : userLoans) {
            boolean isOverdue = LocalDate.now().isAfter(l.getDueDate());
            System.out.println(" • Loan ID: " + l.getLoanId() +
                    " | Item ID: " + l.getItem().getStableId() +
                    " | Title: \"" + l.getItem().getTitle() + "\"" +
                    " | Due Date: " + l.getDueDate() +
                    (isOverdue ? " [OVERDUE!]" : ""));
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\nEnter the Loan ID or Item ID to return (or 0 to cancel): ");
        int idToReturn;
        try {
            idToReturn = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid ID format.");
            menu(session, listUser, listItem);
            return;
        }

        if (idToReturn == 0) {
            menu(session, listUser, listItem);
            return;
        }

        Loan selectedLoan = null;
        for (Loan l : userLoans) {
            if (l.getLoanId() == idToReturn || l.getItem().getStableId() == idToReturn) {
                selectedLoan = l;
                break;
            }
        }

        if (selectedLoan == null) {
            System.out.println("No matching active loan found with ID " + idToReturn + ".");
            menu(session, listUser, listItem);
            return;
        }

        try {
            LocalDate returnDate = LocalDate.now();
            circulationService.returnItem(selectedLoan, returnDate);

            if (returnDate.isAfter(selectedLoan.getDueDate())) {
                System.out.println("Warning: This item was returned past its due date (" + selectedLoan.getDueDate() + ")!");
            }
            System.out.println("Success! \"" + selectedLoan.getItem().getTitle() + "\" has been returned. Thank you!");
        } catch (LibraryException e) {
            System.out.println("Error: " + e.getMessage());
        }

        menu(session, listUser, listItem);
    }
}
