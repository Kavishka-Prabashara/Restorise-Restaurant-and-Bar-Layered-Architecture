package lk.ijse.mrGreen.DAO.Custom.Impl;

import lk.ijse.mrGreen.DAO.Custom.LettuceDAO;
import lk.ijse.mrGreen.DAO.SQLUtil;
import lk.ijse.mrGreen.Entity.Lettuce;
import lk.ijse.mrGreen.db.DbConnection;
import lk.ijse.mrGreen.dto.tm.CartTm;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LettuceDAOImpl implements LettuceDAO {

    SQLUtil sqlUtil = new SQLUtil();
    public boolean save(Lettuce dto) throws SQLException {

        Lettuce object = search(dto.getId());

        if(object!=null){
            return false;
        }else {
            return sqlUtil.execute("INSERT INTO lettuce VALUES(?,?,?,?,?,?,?,?)",
                    dto.getId(),dto.getName(),dto.getTemp(),dto.getHumid(),dto.getQty()
                    ,dto.getSeed(),dto.getUnit(),dto.getSuppId());
        }
    }

    public List<Lettuce> loadAll() throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT * FROM lettuce");

        List<Lettuce> dtoList = new ArrayList<>();

        while(resultSet.next()){
            dtoList.add(new Lettuce(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7),
                    resultSet.getString(8)
            ));
        }

        return dtoList;
    }

    public boolean delete(String id) throws SQLException {

        return sqlUtil.execute("DELETE FROM lettuce WHERE l_id = ?",id);

    }

    public boolean update(Lettuce dto) throws SQLException {

        return sqlUtil.execute("UPDATE lettuce SET name = ?,Temp = ?,humidity = ?,qty_on_hand = ?, " +
                "seed_qty = ?, unit_price = ?, sup_id = ? WHERE l_id = ?",
                dto.getName(),dto.getTemp(),dto.getHumid(),dto.getQty()
                ,dto.getSeed(),dto.getUnit(),dto.getSuppId(),dto.getId());
    }

    public int getCount() throws SQLException {

        ResultSet resultSet = sqlUtil.execute("SELECT COUNT(*) AS num_lettuce From lettuce");

        resultSet.next();
        int lettCount = resultSet.getInt("num_lettuce");

        return lettCount;
    }

    @Override
    public String getName(String id) throws SQLException {
        return null;
    }

    public Lettuce search(String id) throws SQLException {


        Lettuce dto = null;

        ResultSet resultSet = sqlUtil.execute("SELECT * FROM lettuce WHERE l_id = ?",id);
        if(resultSet.next()){
            dto = new Lettuce(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    resultSet.getDouble(5),
                    resultSet.getDouble(6),
                    resultSet.getDouble(7),
                    resultSet.getString(8)
            );
        }
        return dto;
    }

    public boolean updateLettuceQty(List<CartTm> cartTmList) throws SQLException {
        for (CartTm tm: cartTmList) {
            if(!updateQty(tm.getId(),tm.getQty())){
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(String id, Double qty) throws SQLException {


        return sqlUtil.execute("UPDATE lettuce SET qty_on_hand = qty_on_hand - ? WHERE l_id = ?",qty,id);
    }
}
