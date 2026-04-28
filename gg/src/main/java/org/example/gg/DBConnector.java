package org.example.gg;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.*;

import static java.sql.DriverManager.getConnection;

public class DBConnector {
    private String Url = "jdbc:mysql://localhost:3306/users";
    private String Username = "root";
    private String Password = "12345";

    public void DBConn() {
        try (Connection connection = DriverManager.getConnection(Url, Username, Password)) {
            if (connection != null) {
                System.out.println("Подключение к бд");
            }
        } catch (SQLException e) {
            System.out.println("Не подключенно к бд");
            e.printStackTrace();
        }
    }

    public boolean TableConn(String login, String password) {
        try (Connection connection = DriverManager.getConnection(Url, Username, Password)) {
            PreparedStatement stmt = connection.prepareStatement("Select * FROM user WHERE login = ? AND password = ?");
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return true;
            } else {
                return false;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }

    }

    public boolean RegUser(String Login, String password, String Gender, String Name, String LastName) {
        String sql = "INSERT INTO user (Login, Password, Gender, Name, LastName) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(Url, Username, Password);
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, Login);
            stmt.setString(2, password);
            stmt.setString(3, Gender);
            stmt.setString(4, Name);
            stmt.setString(5, LastName);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

