package lk.ijse.mrGreen.BO.Custom.Impl;

import lk.ijse.mrGreen.BO.Custom.OrderDetailBO;
import lk.ijse.mrGreen.DAO.Custom.OrderDetailDAO;
import lk.ijse.mrGreen.DAO.DAOFactory;
import lk.ijse.mrGreen.Entity.Order;
import lk.ijse.mrGreen.Entity.OrderDetails;
import lk.ijse.mrGreen.dto.OrderDetailsDto;
import lk.ijse.mrGreen.dto.tm.CartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailBOImpl implements OrderDetailBO {

    private OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.ORDERDETAILS);

    @Override
    public boolean saveOrderDetail(OrderDetailsDto dto) throws SQLException {
        return orderDetailDAO.save(new OrderDetails(dto.getOrderId(),dto.getCartTmList()));
    }

    @Override
    public boolean deleteOrderDetail(String id) throws SQLException {
        return orderDetailDAO.delete(id);
    }

    @Override
    public boolean updateOrderDetail(OrderDetailsDto dto) throws SQLException {
        return orderDetailDAO.update(new OrderDetails(dto.getOrderId(),dto.getCartTmList()));
    }

    @Override
    public List<OrderDetailsDto> loadAllOrderDetail() throws SQLException {
        List<OrderDetails> orderDetails =  orderDetailDAO.loadAll();
        ArrayList<OrderDetailsDto> dtoList = new ArrayList<>();

        for (OrderDetails dto: orderDetails) {
            dtoList.add(new OrderDetailsDto(dto.getOrderId(),dto.getCartTmList()));
        }
        return dtoList;
    }

    @Override
    public int getOrderDetailCount() throws SQLException {
        return orderDetailDAO.getCount();
    }

    @Override
    public String getOrderDetailName(String id) throws SQLException {
        return orderDetailDAO.getName(id);
    }

    @Override
    public OrderDetailsDto searchOrderDetail(String cusId) throws SQLException {
        OrderDetails orderDetails = orderDetailDAO.search(cusId);

        return new OrderDetailsDto(orderDetails.getOrderId(),orderDetails.getCartTmList());
    }

}
