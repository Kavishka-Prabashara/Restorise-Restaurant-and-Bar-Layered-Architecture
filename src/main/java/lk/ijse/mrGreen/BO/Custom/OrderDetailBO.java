package lk.ijse.mrGreen.BO.Custom;

import lk.ijse.mrGreen.BO.SuperBO;
import lk.ijse.mrGreen.dto.OrderDetailsDto;

import java.sql.SQLException;
import java.util.List;

public interface OrderDetailBO extends SuperBO {
    boolean saveOrderDetail(OrderDetailsDto dto) throws SQLException;

    boolean deleteOrderDetail(String id) throws SQLException ;

    boolean updateOrderDetail(OrderDetailsDto dto) throws SQLException ;

    List<OrderDetailsDto> loadAllOrderDetail() throws SQLException ;

    int getOrderDetailCount() throws SQLException ;

    String getOrderDetailName(String id) throws SQLException ;

    OrderDetailsDto searchOrderDetail(String cusId) throws SQLException ;
}
