package lk.ijse.mrGreen.DAO.Custom.Impl;

import lk.ijse.mrGreen.DAO.Custom.GreenHouseDAO;
import lk.ijse.mrGreen.DAO.SQLUtil;
import lk.ijse.mrGreen.Entity.Greenhouse;
import lk.ijse.mrGreen.db.DbConnection;
import lk.ijse.mrGreen.dto.Fertilizerdto;
import lk.ijse.mrGreen.dto.GreenHouseDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GreenHouseDAOImpl implements GreenHouseDAO {

    SQLUtil sqlUtil = new SQLUtil();
    public boolean save(Greenhouse dto) throws SQLException {

        Object object = search(dto.getId());

        if(object!=null){
            return false;
        }else {
            return sqlUtil.execute("INSERT INTO greenhouse(g_id,name,l_id,water_temp,water_ph) VALUES(?,?,?,?,?)",
                    dto.getId(),dto.getName(),dto.getL_id(),dto.getTemp(),dto.getPh());
        }

    }

    public boolean delete(String id) throws SQLException {

        return sqlUtil.execute("DELETE FROM greenhouse WHERE g_id = ?",id);
    }

    public boolean update(Greenhouse dto) throws SQLException {

        return sqlUtil.execute("UPDATE greenhouse SET name = ?, l_id = ?,water_temp = ?," +
                " water_ph = ? WHERE g_id = ?",dto.getName(),dto.getL_id(),dto.getTemp(),dto.getPh(),dto.getId());
    }

    public List<Greenhouse> loadAll() throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT * FROM greenhouse");

        ArrayList<Greenhouse> dto = new ArrayList<>();

        while (resultSet.next()){
            dto.add(new Greenhouse(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6)
            ));
        }
        return dto;
    }

    public int getCount() throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT COUNT(*) AS num_green FROM greenhouse");

        resultSet.next();
        int count = resultSet.getInt("num_green");

        return count;
    }

    @Override
    public String getName(String id) throws SQLException {
        return null;
    }

    @Override
    public Greenhouse search(String cusId) throws SQLException {
        ResultSet resultSet = sqlUtil.execute("SELECT * FROM greenhouse WHERE g_id = ?",cusId);

        Greenhouse dto = null;

        if(resultSet.next()){
            dto = new Greenhouse(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getInt(4),
                    resultSet.getInt(5),
                    resultSet.getDouble(6)

            );
        }
        return dto;
    }
}
