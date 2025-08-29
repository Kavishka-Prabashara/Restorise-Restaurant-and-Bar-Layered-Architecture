package lk.ijse.mrGreen.DAO.Custom.Impl;

import lk.ijse.mrGreen.DAO.Custom.OrderDAO;
import lk.ijse.mrGreen.DAO.SQLUtil;
import lk.ijse.mrGreen.Entity.Order;
import lk.ijse.mrGreen.db.DbConnection;
import lk.ijse.mrGreen.dto.LettuceDto;
import lk.ijse.mrGreen.dto.OrderDto;

import java.sql.*;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    SQLUtil sqlUtil = new SQLUtil();
    public String genarateOrderId() throws SQLException {

        ResultSet resultSet = sqlUtil.execute( "SELECT order_id FROM orders ORDER BY order_id DESC LIMIT 1");

        if(resultSet.next()){
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("O0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "O00"+id;
            }else {
                if (length < 3){
                    return "O0"+id;
                }else {
                    return "O"+id;
                }
            }
        }
        return "O001";
    }

    public boolean save(Order orderDto) throws SQLException {

        return sqlUtil.execute("INSERT INTO orders VALUES(?,?,?)",
                orderDto.getOrderId(),orderDto.getCusId(),Date.valueOf(orderDto.getDate()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Order dto) throws SQLException {
        return false;
    }

    @Override
    public List<Order> loadAll() throws SQLException {
        return null;
    }

    public int getCount() throws SQLException {

        ResultSet resultSet=sqlUtil.execute("SELECT COUNT(*) AS num_of_orders FROM orders");

        resultSet.next();

        int count = resultSet.getInt("num_of_orders");
        return count;
    }

    @Override
    public String getName(String id) throws SQLException {
        return null;
    }

    @Override
    public Order search(String cusId) throws SQLException {
        Order dto = null;

        ResultSet resultSet = sqlUtil.execute("SELECT * FROM orders WHERE order_id = ?");
        if(resultSet.next()){
            dto = new Order(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDate(3).toLocalDate()
            );
        }
        return dto;
    }
}
