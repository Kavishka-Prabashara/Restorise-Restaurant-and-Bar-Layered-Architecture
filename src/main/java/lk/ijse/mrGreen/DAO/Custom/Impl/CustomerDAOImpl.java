package lk.ijse.mrGreen.DAO.Custom.Impl;

import javafx.scene.control.Alert;
import lk.ijse.mrGreen.DAO.Custom.CustomerDAO;
import lk.ijse.mrGreen.DAO.SQLUtil;
import lk.ijse.mrGreen.Entity.Customer;
import lk.ijse.mrGreen.db.DbConnection;
import lk.ijse.mrGreen.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    SQLUtil sqlUtil = new SQLUtil();
    public boolean save(Customer dto) throws SQLException {

        Customer set = search(dto.getId());
        if(set!=null){
            return false;
        }else {
            return sqlUtil.execute("INSERT INTO customer VALUES(?,?,?,?)",dto.getId(),dto.getName(),dto.getAddress(),dto.getTel());
        }

    }

    public boolean delete(String id) throws SQLException {

        return sqlUtil.execute("DELETE FROM customer WHERE cus_id = ?",id);
    }

    public boolean update(Customer dto) throws SQLException {

        return sqlUtil.execute("UPDATE customer SET name = ?, address = ?, tel = ? WHERE cus_id = ?",dto.getName(),dto.getAddress(),dto.getTel(),dto.getId());
    }

    public List<Customer> loadAll() throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT * FROM customer");

        ArrayList<Customer> dto = new ArrayList<>();

        while (resultSet.next()){
            dto.add(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return dto;
        
    }

    public int getCount() throws SQLException {
        
        ResultSet resultSet = sqlUtil.execute("SELECT COUNT(*) AS num_Customer FROM customer");

        resultSet.next();

        return resultSet.getInt("num_Customer");
    }

    public String getName(String id) throws SQLException {

        ResultSet resultSet = sqlUtil.execute( "SELECT name FROM customer WHERE cus_id = ?",id);

        if(resultSet.next()){
            return resultSet.getString("name");
        }
        return null;
    }

    public Customer search(String cusId) throws SQLException {

      ResultSet resultSet = sqlUtil.execute("SELECT * FROM customer WHERE cus_id = ?",cusId);
      
      Customer dto = null;

        if(resultSet.next()){
            dto = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return dto;
    }

}
