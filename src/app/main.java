import java.util.InputMismatchException;
import java.util.Scanner;

public class main{
    public static void welcome() {
        System.out.print("\nWelcome to the Library Management System !\n" +
                "\n" +
                "Choose an option :\n" +
                " 1 - Login\n" +
                " 2 - Register\n" +
                " 3 - Logout\n" +
                " 4 - Exit");
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

        int option = readInputOption(1, 4);
        System.out.print(option);


    }

}