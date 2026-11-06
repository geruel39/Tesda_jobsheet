package service;

import model.User;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAhh {

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
}
