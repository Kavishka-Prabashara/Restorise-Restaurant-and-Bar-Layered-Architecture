package lk.ijse.mrGreen.DAO.Custom.Impl;

import lk.ijse.mrGreen.DAO.Custom.SupplierDAO;
import lk.ijse.mrGreen.DAO.SQLUtil;
import lk.ijse.mrGreen.Entity.Supplier;
import lk.ijse.mrGreen.db.DbConnection;
import lk.ijse.mrGreen.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {

    SQLUtil sqlUtil = new SQLUtil();
    public List<Supplier> loadAll() throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT * FROM supplier");

        List<Supplier> supList = new ArrayList<>();

        while (resultSet.next()){
            supList.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));

        }

        return supList;
    }

    public boolean save(Supplier dto) throws SQLException {

        Object object = search(dto.getSup_id());

        if(object!=null){
            return false;
        }else {
            return sqlUtil.execute("INSERT INTO supplier VALUES(?,?,?,?,?)",dto.getSup_id(),dto.getName(),
                    dto.getCompany(),dto.getTel(),dto.getUser_id());
        }

    }

    public boolean delete(String id) throws SQLException {
        return sqlUtil.execute("DELETE FROM supplier WHERE sup_id = ?",id);
    }

    public boolean update(Supplier dto) throws SQLException {

        return sqlUtil.execute("UPDATE supplier SET name = ?, company = ?, tel = ?, user_id = ? WHERE sup_id =?",
                dto.getName(),dto.getCompany(),dto.getTel(),dto.getUser_id(),dto.getSup_id());
    }

    public int getCount() throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT COUNT(*)As sup_count from supplier");

        resultSet.next();

        int count = resultSet.getInt("sup_count");
        return count;
    }

    @Override
    public String getName(String id) throws SQLException {
        return null;
    }

    public Supplier search(String suppId) throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT * FROM supplier WHERE sup_id = ?",suppId);

        Supplier dto = null;

        if(resultSet.next()){
            dto = new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return dto;
    }
}
