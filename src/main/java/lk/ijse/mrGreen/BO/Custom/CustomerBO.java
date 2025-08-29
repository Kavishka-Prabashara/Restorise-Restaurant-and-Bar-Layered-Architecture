package lk.ijse.mrGreen.BO.Custom;

import lk.ijse.mrGreen.BO.SuperBO;
import lk.ijse.mrGreen.dto.CustomerDto;

import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDto dto) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException ;

    boolean updateCustomer(CustomerDto dto) throws SQLException ;

    List<CustomerDto> loadAllCustomer() throws SQLException ;

    int getCustomerCount() throws SQLException ;

    String getCustomerName(String id) throws SQLException ;

    CustomerDto searchCustomer(String cusId) throws SQLException ;
}
