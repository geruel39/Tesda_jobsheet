package service;

import model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAuthentication {

    public User login(String email, String pin) {

        String sql = "SELECT * FROM users WHERE email = ? AND pin = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, email);
            statement.setString(2, pin);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                User user = new User(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("number"),
                        resultSet.getString("pin")
                );

                System.out.println("Login successful!");
                return user;
            }

            System.out.println("Invalid email or PIN.");
            return null;

        } catch (SQLException e) {
            System.out.println("Login failed due to a database error.");
            e.printStackTrace();
            return null;
        }
    }

    public boolean changePin(User user, String currentPin, String newPin) {

        // Verify the current PIN
        if (!user.getPin().equals(currentPin)) {
            System.out.println("Incorrect current PIN.");
            return false;
        }

        String sql = "UPDATE users SET pin = ? WHERE id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, newPin);
            statement.setInt(2, user.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {

                // Update the PIN inside the User object too
                user.setPin(newPin);

                System.out.println("PIN changed successfully!");
                return true;
            }

            System.out.println("Unable to change PIN.");
            return false;

        } catch (SQLException e) {
            System.out.println("Database error while changing PIN.");
            e.printStackTrace();
            return false;
        }
    }

    public User logout(User user) {

        if (user == null) {
            System.out.println("No user is currently logged in.");
            return null;
        }

        System.out.println(user.getName() + " logged out successfully!");

        return null;
    }
}



