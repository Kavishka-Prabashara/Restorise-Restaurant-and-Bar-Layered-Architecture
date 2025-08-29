package lk.ijse.mrGreen.DAO.Custom;

import lk.ijse.mrGreen.DAO.CrudDAO;
import lk.ijse.mrGreen.Entity.Order;
import lk.ijse.mrGreen.dto.OrderDto;

import java.sql.*;

public interface OrderDAO extends CrudDAO<Order> {
     String genarateOrderId() throws SQLException ;

//     boolean saveOrder(OrderDto orderDto) throws SQLException ;
//
//     int getOrderCount() throws SQLException ;
}
