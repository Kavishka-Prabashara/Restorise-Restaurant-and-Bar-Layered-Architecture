package lk.ijse.mrGreen.BO.Custom;

import lk.ijse.mrGreen.BO.SuperBO;
import lk.ijse.mrGreen.dto.OrderDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderBO extends SuperBO {
    boolean saveOrder(OrderDto dto) throws SQLException;

    boolean deleteOrder(String id) throws SQLException ;

    boolean updateOrder(OrderDto dto) throws SQLException ;

    List<OrderDto> loadAllOrder() throws SQLException ;

    int getOrderCount() throws SQLException ;

    String getOrderName(String id) throws SQLException ;

    OrderDto searchOrder(String cusId) throws SQLException ;

    public String genarateOrderId() throws SQLException ;
}
