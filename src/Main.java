
import model.User;
import service.Registration;
import service.UserAuthentication;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        Registration registration = new Registration();
        UserAuthentication authentication = new UserAuthentication();

        User loggedInUser = null;

        boolean running = true;

        while (running) {

            if (loggedInUser == null) {

                System.out.println("\n===== USER AUTHENTICATION =====");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Choose an option: ");

                String choice = scanner.nextLine();

                switch (choice) {

                    case "1":
                        registration.register(scanner);
                        break;

                    case "2":
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter PIN: ");
                        String pin = scanner.nextLine();

                        loggedInUser = authentication.login(email, pin);
                        break;

                    case "3":
                        running = false;
                        System.out.println("Application closed.");
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }

            } else {

                System.out.println("\n===== USER MENU =====");
                System.out.println("Welcome, " + loggedInUser.getName() + "!");
                System.out.println("1. Change PIN");
                System.out.println("2. Logout");
                System.out.print("Choose an option: ");

                String choice = scanner.nextLine();

                switch (choice) {

                    case "1":
                        System.out.print("Enter current PIN: ");
                        String currentPin = scanner.nextLine();

                        System.out.print("Enter new PIN: ");
                        String newPin = scanner.nextLine();

                        authentication.changePin(
                                loggedInUser,
                                currentPin,
                                newPin
                        );
                        break;

                    case "2":
                        loggedInUser = authentication.logout(loggedInUser);
                        break;

                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        }

        scanner.close();
    }
}
