// CustomerModel.java
package lk.restorise.restorisems.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.restorise.restorisems.db.DBConnection;
import lk.restorise.restorisems.dto.CustomerDTO;
import lk.restorise.restorisems.dto.tm.CustomerTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date; // Import the Date class

public class CustomerModel {
    public void saveCustomer(CustomerDTO customerDTO) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "INSERT INTO customer_details (customer_id, customer_name, customer_mobile, order_ID, order_date) VALUES (?, ?, ?, ?, ?)";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customerDTO.getId());
            preparedStatement.setString(2, customerDTO.getName());
            preparedStatement.setInt(3, Integer.parseInt(customerDTO.getMobileNo()));
            preparedStatement.setString(4, customerDTO.getOrderID());
            preparedStatement.setDate(5, new Date(customerDTO.getOrderDate().getTime()));

            preparedStatement.executeUpdate();
        } finally {
            closeResources();
        }
    }

    public void updateCustomer(CustomerDTO customerDTO) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "UPDATE customer_details SET customer_name = ?, customer_mobile = ?, order_ID = ?, order_date = ? WHERE customer_id = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customerDTO.getName());
            preparedStatement.setInt(2, Integer.parseInt(customerDTO.getMobileNo()));
            preparedStatement.setString(3, customerDTO.getOrderID());
            preparedStatement.setDate(4, new Date(customerDTO.getOrderDate().getTime()));
            preparedStatement.setString(5, customerDTO.getId());

            preparedStatement.executeUpdate();
        } finally {
            closeResources();
        }
    }

    public void deleteCustomer(String customerId) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "DELETE FROM customer_details WHERE customer_id = ?";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customerId);

            preparedStatement.executeUpdate();
        } finally {
            closeResources();
        }
    }

    public ObservableList<CustomerTM> getAllCustomers() throws SQLException {
        ObservableList<CustomerTM> customersList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "SELECT * FROM customer_details";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String customerId = resultSet.getString("customer_id");
                String customerName = resultSet.getString("customer_name");
                String customerMobile = resultSet.getString("customer_mobile");
                String orderID = resultSet.getString("order_ID");
                Date orderDate = resultSet.getDate("order_date");

                CustomerTM customerTM = new CustomerTM(customerId, customerName, Integer.parseInt(customerMobile), orderID, orderDate);
                customersList.add(customerTM);
            }
        } finally {
            closeResources();
        }

        return customersList;
    }

    private void closeResources() {

        }
    }