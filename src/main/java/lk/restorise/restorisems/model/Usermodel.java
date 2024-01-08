package lk.restorise.restorisems.model;

import lk.restorise.restorisems.db.DBConnection;
import lk.restorise.restorisems.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Usermodel {
    public static boolean saveUser(UserDTO dto) throws SQLException {
        Connection connection= DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO user VALUES(?, ?, ?, ?,?)";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getTxtemail());
        pstm.setString(2, dto.getTxtusername());
        pstm.setString(3, dto.getPassword());
        pstm.setString(4, String.valueOf(dto.getCmbbxQuestion()));
        pstm.setString(5, dto.getAnswer());


        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
}


}
