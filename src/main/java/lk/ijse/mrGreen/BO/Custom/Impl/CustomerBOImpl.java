package lk.ijse.mrGreen.BO.Custom.Impl;

import lk.ijse.mrGreen.BO.Custom.CustomerBO;
import lk.ijse.mrGreen.DAO.Custom.CustomerDAO;
import lk.ijse.mrGreen.DAO.DAOFactory;
import lk.ijse.mrGreen.Entity.Customer;
import lk.ijse.mrGreen.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    private CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DTOTypes.CUSTOMER);
    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        return customerDAO.save(new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }

    @Override
    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        return customerDAO.update(new Customer(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel()));
    }

    @Override
    public List<CustomerDto> loadAllCustomer() throws SQLException {
        List<Customer> set= customerDAO.loadAll();

        ArrayList<CustomerDto> dtoList = new ArrayList<>();

        for (Customer dto: set) {
            dtoList.add(new CustomerDto(dto.getId(),dto.getName(),dto.getAddress(),dto.getTel()));
        }
        return dtoList;
    }

    @Override
    public int getCustomerCount() throws SQLException {
        return customerDAO.getCount();
    }

    @Override
    public String getCustomerName(String id) throws SQLException {
        return customerDAO.getName(id);
    }

    @Override
    public CustomerDto searchCustomer(String cusId) throws SQLException {
        Customer customer = customerDAO.search(cusId);

        return new CustomerDto(customer.getId(),customer.getName(),customer.getAddress(),customer.getTel());
    }
}
