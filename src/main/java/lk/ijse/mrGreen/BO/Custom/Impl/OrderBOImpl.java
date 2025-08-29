package lk.ijse.mrGreen.BO.Custom.Impl;

import lk.ijse.mrGreen.BO.Custom.OrderBO;
import lk.ijse.mrGreen.DAO.Custom.OrderDAO;
import lk.ijse.mrGreen.DAO.DAOFactory;
import lk.ijse.mrGreen.Entity.Order;
import lk.ijse.mrGreen.dto.OrderDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderBOImpl implements OrderBO {

    private OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.ORDER);

    @Override
    public boolean saveOrder(OrderDto dto) throws SQLException {
        return orderDAO.save(new Order(dto.getOrderId(),dto.getCusId(),dto.getDate()));
    }

    @Override
    public boolean deleteOrder(String id) throws SQLException {
        return orderDAO.delete(id);
    }

    @Override
    public boolean updateOrder(OrderDto dto) throws SQLException {
        return orderDAO.update(new Order(dto.getOrderId(),dto.getCusId(),dto.getDate()));
    }

    @Override
    public List<OrderDto> loadAllOrder() throws SQLException {
        List<Order> order = orderDAO.loadAll();
        ArrayList<OrderDto> dtoList = new ArrayList<>();

        for (Order dto: order) {
            dtoList.add(new OrderDto(dto.getOrderId(),dto.getCusId(),dto.getDate()));
        }
        return dtoList;
    }

    @Override
    public int getOrderCount() throws SQLException {
        return orderDAO.getCount();
    }

    @Override
    public String getOrderName(String id) throws SQLException {
        return orderDAO.getName(id);
    }

    @Override
    public OrderDto searchOrder(String cusId) throws SQLException {
        Order order= orderDAO.search(cusId);

        return new OrderDto(order.getOrderId(),order.getCusId(),order.getDate());
    }

    @Override
    public String genarateOrderId() throws SQLException {
        return orderDAO.genarateOrderId();
    }
}
