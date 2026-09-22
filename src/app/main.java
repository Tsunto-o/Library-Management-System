package app;

import model.items.itemMemory.ItemList;
import model.user.Member;
import model.user.User;
import model.user.userMemory.UsersList;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class main {
    /**
     * Welcoming page (1st level)
     */
    public static void welcome(UsersList listUser, ItemList listItem) {
        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "Welcome to the Library Management System !\n" +
                "\n" +
                "   Choose an option :\n" +
                "       1 - Login\n" +
                "       2 - Register\n\n");

        switch (readInputOption(1, 2)) {
            case 1:
                login(listUser);
                break;
            case 2:
                register(listUser);
                break;
        }
    }

    /**
     * Login page (2nd level)
     */
    public static void login(UsersList listUser) {
        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "Login \n" +
                "\n" +
                "   Choose an option :\n" +
                "       1 - Login\n" +
                "       2 - Register\n\n");

        switch (readInputOption(1, 2)) {
            case 1:
                login(listUser);
                break;
            case 2:
                register(listUser);
                break;
        }
    }

    /**
     * Register page (2nd level)
     */
    public static void register(UsersList listUser) {
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

        listUser.addUser(new Member(stableId,password,firstName,lastName,birthDate,3));
        listUser.save("src/model/user/userMemory/users.csv");
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

    public static void mainMenu(UsersList listUser, ItemList listItem) {
        System.out.print("\n_______________________________________________________________________________________\n" +
                "\n" +
                "Main Menu" +"!\n" +
                "\n" +
                "   Choose an option :\n" +
                "       1 - Borrow an item\n" +
                "       2 - Return an item\n" +
                "       3 - Reserve an item\n" +
                "       4 - Search / filter catalogue\n" +
                "       5 - View reports\n" +
                "       6 - Logout\n\n");

        switch (readInputOption(1, 6)) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                welcome(listUser, listItem);
                break;
        }
    }

    public static void main(String[] args) {
        ItemList listItem = new ItemList();
        listItem.load("src/model/items/itemMemory/items.csv");
        ///listItem.displayListItems();


        UsersList listUser = new UsersList();
        listUser.load("src/model/user/userMemory/users.csv");
        ///listUser.displaylistUsers();

        welcome(listUser, listItem);

        listUser.save("src/model/user/userMemory/users.csv");

    }
}


///         listUser.addUser(new Member("testgv", "pw", "Gia", "To", LocalDate.of(2000, 9, 11), 8));
///         listUser.addUser(new Member("testgv1", "pass", "Gia", "To", LocalDate.of(2000, 9, 11), 8));
///         listUser.addUser(new Member("testgv2","pw2", "Gia", "To", LocalDate.of(2000, 9, 11), 8));