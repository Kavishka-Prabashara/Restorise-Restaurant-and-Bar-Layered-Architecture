package lk.ijse.mrGreen.DAO.Custom.Impl;

import lk.ijse.mrGreen.DAO.Custom.OrderDetailDAO;
import lk.ijse.mrGreen.DAO.SQLUtil;
import lk.ijse.mrGreen.Entity.OrderDetails;
import lk.ijse.mrGreen.db.DbConnection;
import lk.ijse.mrGreen.dto.OrderDetailsDto;
import lk.ijse.mrGreen.dto.tm.CartTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    SQLUtil sqlUtil = new SQLUtil();
    public boolean save(OrderDetails orderDetailsDto) throws SQLException {
        for (CartTm tm: orderDetailsDto.getCartTmList()) {
            if(!saveOrderDetails(orderDetailsDto.getOrderId(),tm)){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(OrderDetails dto) throws SQLException {
        return false;
    }

    @Override
    public List<OrderDetails> loadAll() throws SQLException {
        return null;
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
    public OrderDetails search(String cusId) throws SQLException {
        return null;
    }

    public boolean saveOrderDetails(String orderId, CartTm tm) throws SQLException {

        return sqlUtil.execute("INSERT INTO order_details VALUES (?,?,?,?,?)",
                orderId,tm.getId(),tm.getQty(),tm.getUnit(),tm.getQty()*tm.getUnit());

    }
}
