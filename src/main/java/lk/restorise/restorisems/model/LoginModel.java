package lk.restorise.restorisems.model;

import lk.restorise.restorisems.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {
    public static boolean validateUser(String username, String password) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM user WHERE username = ? AND password = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, username);
            pstm.setString(2, password);

            try (ResultSet resultSet = pstm.executeQuery()) {
                return resultSet.next(); // Returns true if there is at least one row (valid user)
            }
        }
    }
}
