package app;

import model.items.itemMemory.itemList;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    /**
     * Welcoming page (1st level)
     */
    public static void welcome() {
        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "Welcome to the Library Management System !\n" +
                "\n" +
                "   Choose an option :\n" +
                "       1 - Login\n" +
                "       2 - Register\n\n");

        switch (readInputOption(1, 2)) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
        }
    }

    /**
     * Login page (2nd level)
     */
    public static void login() {
        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "Login \n" +
                "\n" +
                "   Choose an option :\n" +
                "       1 - Login\n" +
                "       2 - Register\n\n");

        switch (readInputOption(1, 2)) {
            case 1:
                login();
                break;
            case 2:
                register();
                break;
        }
    }

    /**
     * Register page (2nd level)
     */
    public static void register() {
        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "Registering new user :\n" +
                "\n" +
                "   Enter user informations: \n");

        Scanner scanner = new Scanner(System.in);

        System.out.print("      First name: ");
        String firstName = scanner.nextLine();
        while (true) {
            if (firstName.isBlank()) {
                System.out.print("      First name can't be blank.\n" +
                        "      First name: ");
                firstName = scanner.nextLine();
                continue;
            } else if (firstName.matches(".*[0-9].*")) { ///.=any cara| *=any count
                System.out.print("      First name can't contain numbers.\n" +
                        "      First name: ");
                firstName = scanner.nextLine();
                continue;
            } else if (!firstName.matches("[a-zA-ZÀ-ÿ\\-]+")) { ///.=any cara| *=any count | \\- = -
                System.out.print("      First name can't contain special caracters.\n" +
                        "      First name: ");
                firstName = scanner.nextLine();
                continue;
            }
            break;
        }

        System.out.print("      Last name: ");
        String lastName = scanner.nextLine();
        while (true) {
            if (lastName.isBlank()) {
                System.out.print("      Last name can't be blank.\n" +
                        "      Last name: ");
                lastName = scanner.nextLine();
                continue;
            } else if (lastName.matches(".*[0-9].*")) { ///.=any cara| *=any count
                System.out.print("      Last name can't contain numbers.\n" +
                        "      Last name: ");
                lastName = scanner.nextLine();
                continue;
            } else if (!lastName.matches("[a-zA-ZÀ-ÿ\\-]+")) { ///.=any cara| *=any count | \\- = -
                System.out.print("      Last name can't contain special caracters.\n" +
                        "      Last name: ");
                lastName = scanner.nextLine();
                continue;
            }
            break;
        }

        System.out.print("      Birth date (YYYY-MM-DD): ");
        LocalDate birthDate;
        while (true) {
            try {
                birthDate = LocalDate.parse(scanner.nextLine());
                if (birthDate.isAfter(LocalDate.now())) {
                    throw new DateTimeException("Illegal date.");
                }
                break;
            } catch (DateTimeException e) {
                System.out.print("      Invalid date.\n" +
                        "      Birth date (YYYY-MM-DD): ");
            }
        }

        System.out.print("      ID (username): ");
        String stableId = scanner.nextLine();
        while (true) {
            if (stableId.isBlank()) {
                System.out.print("      ID can't be blank.\n" +
                        "      ID (username): ");
                stableId = scanner.nextLine();
                continue;
            } else if (stableId.contains(" ")) {
                System.out.print("      Last name can't contain spaces.\n" +
                        "      ID (username): ");
                stableId = scanner.nextLine();
                continue;
            }
            break;
        }


        System.out.print("      Password: ");
        String password = scanner.nextLine();
        while (true) {
            if (password.isBlank()) {
                System.out.print("      Password can't be blank.\n" +
                        "      Password: ");
                password = scanner.nextLine();
                continue;
            }
            break;
        }


    }

    /// concurrence
    /// identifiable.java
    /// item status.java
    /// items, loan, users.txt (log)
    /// display catalogue
    /// brrow, return item
    /// update user
    /// show audited actions
    /// reserve items
    /// generate reports
    /// search and filters
    /// run scripted demonstration
    /// many filters, serach options
    ///

    public static int readInputOption(int min, int max) {
        Scanner scanner = new Scanner(System.in);

        int res = -1;
        while (res < min || res > max) {
            try {
                System.out.printf("Enter an integer between %d and %d: ", min, max);
                res = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                scanner.nextLine();
            }
        }
        return res;
    }


    public static void main(String[] args) {
        ///welcome();
        itemList listItem = new itemList();
        listItem.load("src/model/items/itemMemory/items.csv");
        listItem.displayListItems();






    }
}