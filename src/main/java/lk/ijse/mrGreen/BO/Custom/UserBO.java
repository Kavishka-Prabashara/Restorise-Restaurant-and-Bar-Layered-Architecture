package lk.ijse.mrGreen.BO.Custom;

import lk.ijse.mrGreen.BO.SuperBO;
import lk.ijse.mrGreen.dto.UserDto;

import java.sql.SQLException;
import java.util.List;

public interface UserBO extends SuperBO {
    boolean saveUser(UserDto dto) throws SQLException;

    boolean deleteUser(String id) throws SQLException ;

    boolean updateUser(UserDto dto) throws SQLException ;

    List<UserDto> loadAllUser() throws SQLException ;

    int getUserCount() throws SQLException ;

    String getUserName(String id) throws SQLException ;

    UserDto searchUser(String cusId) throws SQLException ;

    String getUserPassword(String name) throws SQLException ;

    String getUserEmail(String name) throws SQLException ;
}
