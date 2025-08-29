package lk.ijse.mrGreen.DAO.Custom.Impl;

import lk.ijse.mrGreen.DAO.Custom.UserDAO;
import lk.ijse.mrGreen.DAO.SQLUtil;
import lk.ijse.mrGreen.Entity.User;
import lk.ijse.mrGreen.db.DbConnection;
import lk.ijse.mrGreen.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    SQLUtil sqlUtil = new SQLUtil();
    public User search(String name) throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT * FROM user WHERE name = ?",name);

        User dto =null;

        if(resultSet.next()){
            String user_id = resultSet.getString(1);
            String user_name = resultSet.getString(2);
            String password = resultSet.getString(3);
            String job_role = resultSet.getString(4);

            dto=new User(user_id,user_name,password,job_role);
        }

        return dto;
    }

    @Override
    public boolean save(User dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(User dto) throws SQLException {
        return false;
    }

    public List<User> loadAll() throws SQLException {

        ArrayList<User> dtoList= new ArrayList<>();

        ResultSet resultSet=sqlUtil.execute("SELECT * FROM user");

        while (resultSet.next()){
            dtoList.add(new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return dtoList;
    }

    @Override
    public int getCount() throws SQLException {
        return 0;
    }

    @Override
    public String getName(String id) throws SQLException {
        return null;
    }

    public String getPassword(String name) throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT password FROM user WHERE name = ?",name);

        resultSet.next();
        return resultSet.getString("password");

    }

    public String getEmail(String name) throws SQLException {


        ResultSet resultSet = sqlUtil.execute("SELECT email FROM user WHERE name = ?",name
        );

        resultSet.next();
        return resultSet.getString("email");


    }
}
