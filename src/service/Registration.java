
package service;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Registration {

    public void register(Scanner scanner) {

        System.out.println("\n===== USER REGISTRATION =====");

        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter number: ");
        String number = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        String sql = "INSERT INTO users (name, email, number, pin) VALUES (?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, number);
            statement.setString(4, pin);

            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Registration successful!");
            }

        } catch (SQLException e) {
            System.out.println("Registration failed!");
            e.printStackTrace();
        }
    }
}
