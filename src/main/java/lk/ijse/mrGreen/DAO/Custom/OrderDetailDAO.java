package lk.ijse.mrGreen.DAO.Custom;

import lk.ijse.mrGreen.DAO.CrudDAO;
import lk.ijse.mrGreen.Entity.OrderDetails;
import lk.ijse.mrGreen.dto.OrderDetailsDto;
import lk.ijse.mrGreen.dto.tm.CartTm;

import java.sql.SQLException;

public interface OrderDetailDAO extends CrudDAO<OrderDetails> {

//    boolean saveOrderDetails(OrderDetailsDto orderDetailsDto) throws SQLException;

    boolean saveOrderDetails(String orderId, CartTm tm) throws SQLException ;
}
