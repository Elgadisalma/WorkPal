package test;

import java.util.Scanner;

public class AuthTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("You want to ");
        while (true){
            System.out.println("1- Register");
            System.out.println("2- Login");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice){
                case 1:
                    handleRegister(scanner);
                case 2:
                    handleLogin(scanner);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static void handleRegister(Scanner scanner) {
        System.out.println("Enter your Full Name");
        String full_name = scanner.nextLine();
        System.out.println("Enter your email");
        String email = scanner.nextLine();
        System.out.println("Enter your password");
        String password = scanner.nextLine();
        System.out.println("Enter your phone number");
        String phoneNumber = scanner.nextLine();
        System.out.println("Enter your adress");
        String adress = scanner.nextLine();



    }


    private static void handleLogin(Scanner scanner) {}
}
