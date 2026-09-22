package model.menu;

import model.Loan;
import model.items.Book;
import model.items.LibraryItem;
import model.items.itemMemory.ItemList;
import model.user.User;
import model.user.userMemory.UsersList;
import repository.Repository;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static app.main.circulationService;
import static app.main.menu;
import static app.main.readInputOption;
import static model.menu.browseAndSortMenu.browseAndSortMenu;

public class searchMenu {

    public static Set<String> getAvailableTypes(ItemList listItem) {
        return listItem.getListItems().stream()
                .map(LibraryItem::getType)
                .collect(Collectors.toSet());
    }

    private static void displayResults(List<LibraryItem> results) {
        if (results.isEmpty()) {
            System.out.println("\nNo matching item found.");
            return;
        }
        System.out.println("\n--- Results (" + results.size() + ") ---");
        for (LibraryItem item : results) {
            System.out.println(" • " + item.getDisplayInfo());
        }
    }

    public static void searchMenu(User session, UsersList listUser, ItemList listItem) {
        if (listItem.getListItems().isEmpty()) {
            System.out.println("\nCatalogue is empty.");
            menu(session, listUser, listItem);
            return;
        }

        Set<String> availableTypes = getAvailableTypes(listItem);

        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "Search / Filter Catalogue\n" +
                "\n" +
                "   Available types: " + availableTypes + "\n" +
                "\n" +
                "   Choose an option :\n" +
                "       1 - Search by ID\n" +
                "       2 - Filter by title\n" +
                "       3 - Filter by author\n" +
                "       4 - Filter by type (Book/Magazine)\n" +
                "       5 - Filter by availability\n" +
                "       6 - Filter by borrower\n" +
                "       7 - Back to Main Menu\n" +
                "       8 - Sort catalogue\n\n");

        Scanner scanner = new Scanner(System.in);

        switch (readInputOption(1, 8)) {
            case 1: {
                Repository<LibraryItem> itemRepository = new Repository<>();
                listItem.getListItems().forEach(itemRepository::add);

                System.out.print("Enter the item ID to search: ");
                String idQuery = scanner.nextLine().trim();

                LibraryItem found = itemRepository.findById(idQuery);
                if (found == null) {
                    System.out.println("\nNo item found with ID " + idQuery + ".");
                } else {
                    System.out.println("\n--- Result ---");
                    System.out.println(" • " + found.getDisplayInfo());
                }
                break;
            }
            case 2: {
                System.out.print("Enter a title (or part of a title) to search: ");
                String query = scanner.nextLine().trim().toLowerCase();

                Predicate<LibraryItem> byTitle = item -> item.getTitle().toLowerCase().contains(query);
                List<LibraryItem> results = listItem.getListItems().stream()
                        .filter(byTitle)
                        .collect(Collectors.toList());

                displayResults(results);
                break;
            }
            case 3: {
                System.out.print("Enter an author (or part of a name) to search: ");
                String query = scanner.nextLine().trim().toLowerCase();

                Predicate<LibraryItem> byAuthor = item -> item instanceof Book
                        && ((Book) item).getAuthor().toLowerCase().contains(query);
                List<LibraryItem> results = listItem.getListItems().stream()
                        .filter(byAuthor)
                        .collect(Collectors.toList());

                displayResults(results);
                break;
            }
            case 4: {
                System.out.print("Enter a type from the list above: ");
                String query = scanner.nextLine().trim();

                Predicate<LibraryItem> byType = item -> item.getType().equalsIgnoreCase(query);
                List<LibraryItem> results = listItem.getListItems().stream()
                        .filter(byType)
                        .collect(Collectors.toList());

                displayResults(results);
                break;
            }
            case 5: {
                System.out.print("Enter a status to filter by (e.g. AVAILABLE, BORROWED): ");
                String query = scanner.nextLine().trim();

                Predicate<LibraryItem> byAvailability = item -> item.getStatus().equalsIgnoreCase(query);
                List<LibraryItem> results = listItem.getListItems().stream()
                        .filter(byAvailability)
                        .collect(Collectors.toList());

                displayResults(results);
                break;
            }
            case 6: {
                System.out.print("Enter a borrower name (or part of it) to search: ");
                String query = scanner.nextLine().trim().toLowerCase();

                Predicate<Loan> byBorrower = loan -> !loan.getReturned()
                        && (loan.getBorrower().getFirstName() + " " + loan.getBorrower().getLastName())
                                .toLowerCase().contains(query);
                List<LibraryItem> results = circulationService.getActiveLoans().stream()
                        .filter(byBorrower)
                        .map(Loan::getItem)
                        .distinct()
                        .collect(Collectors.toList());

                displayResults(results);
                break;
            }
            case 7:
                menu(session, listUser, listItem);
                return;
            case 8:
                browseAndSortMenu(session, listUser, listItem);
                return;
        }

        menu(session, listUser, listItem);
    }
}
