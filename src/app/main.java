import java.util.InputMismatchException;
import java.util.Scanner;

public class main{
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
            case 1: login(); break;
            case 2: register(); break;
        }
    }

    /**
     *Login page (2nd level)
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
            case 1: login(); break;
            case 2: register(); break;
        }
    }

    /**
     *Register page (2nd level)
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
        while (true){
            if (firstName.isBlank()){
                System.out.print("      First name can't be blank.\n" +
                        "      First name: ");
                firstName = scanner.nextLine();
                continue;
            }
            else if (firstName.matches(".*[0-9].*")){ ///.=any cara| *=any count
                System.out.print("      First name can't contain numbers.\n" +
                        "      First name: ");
                firstName = scanner.nextLine();
                continue;
            }
            else if (!firstName.matches("[a-zA-ZÀ-ÿ\\-]+")){ ///.=any cara| *=any count | \\- = -
                System.out.print("      First name can't contain special caracters.\n" +
                        "      First name: ");
                firstName = scanner.nextLine();
                continue;
            }
            break;
        }
    }

    public static int readInputOption(int min, int max){
        Scanner scanner = new Scanner(System.in);

        int res = -1;
        while (res<min||res>max){
            try {
                System.out.printf("Enter an integer between %d and %d: ", min, max);
                res = scanner.nextInt();
                scanner.nextLine();
            }catch (InputMismatchException e) {scanner.nextLine();}
        }
        return res;
    }





    public static void main(String[] args){

        welcome();
    }

}