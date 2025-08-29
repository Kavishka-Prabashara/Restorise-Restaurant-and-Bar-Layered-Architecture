package lk.ijse.mrGreen.DAO.Custom.Impl;

import lk.ijse.mrGreen.DAO.Custom.FertilizerDAO;
import lk.ijse.mrGreen.DAO.SQLUtil;
import lk.ijse.mrGreen.Entity.Fertilizer;
import lk.ijse.mrGreen.db.DbConnection;
import lk.ijse.mrGreen.dto.CustomerDto;
import lk.ijse.mrGreen.dto.Fertilizerdto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FertilizerDAOImpl implements FertilizerDAO {

    SQLUtil sqlUtil = new SQLUtil();
    public boolean save(Fertilizer dto) throws SQLException {

        Object object = search(dto.getId());

        if(object!=null){
            return false;
        }else {
            return sqlUtil.execute("INSERT INTO fertilizer VALUES(?,?,?,?,?,?,?)",
                    dto.getId(),dto.getName(),dto.getCompany(),dto.getUnit(),dto.getQty(),dto.getSupId(),dto.getLId());
        }




    }

    public boolean delete(String id) throws SQLException {

        return sqlUtil.execute("DELETE FROM fertilizer WHERE f_id = ?",id);
    }

    public boolean update(Fertilizer dto) throws SQLException {

        return sqlUtil.execute("UPDATE fertilizer SET name = ?, company = ?, unit = ?, qty = ?,sup_id = ?," +
                        "l_id = ? WHERE f_id = ?",dto.getName(),dto.getCompany(),dto.getUnit()
                ,dto.getQty(),dto.getSupId(),dto.getLId(),dto.getId());
    }

    public List<Fertilizer> loadAll() throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT * FROM fertilizer");

        ArrayList<Fertilizer> dto = new ArrayList<>();

        while (resultSet.next()){
            dto.add(new Fertilizer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            ));
        }
        return dto;

    }

    @Override
    public int getCount() throws SQLException {
        return 0;
    }

    @Override
    public String getName(String id) throws SQLException {
        return null;
    }

    @Override
    public Fertilizer search(String cusId) throws SQLException {
        ResultSet resultSet = sqlUtil.execute("SELECT * FROM fertilizer WHERE f_id = ?",cusId);

        Fertilizer dto = null;

        if(resultSet.next()){
            dto = new Fertilizer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }
        return dto;
    }
}
