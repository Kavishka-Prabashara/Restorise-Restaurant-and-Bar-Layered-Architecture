// CustomerController.java
package lk.restorise.restorisems.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.restorise.restorisems.db.DBConnection;
import lk.restorise.restorisems.dto.CustomerDTO;
import lk.restorise.restorisems.dto.tm.CustomerTM;
import lk.restorise.restorisems.model.CustomerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.LocalDate;
import java.util.Date;



public class CustomerController {

    @FXML
    private DatePicker DateCustomerOrderDate;

    @FXML
    private TableColumn<CustomerTM, String> colCusId;

    @FXML
    private TableColumn<CustomerTM, String> colCusName;

    @FXML
    private TableColumn<CustomerTM, Integer> colCusMobileNo;

    @FXML
    private TableColumn<CustomerTM, String> colCusOrderID;

    @FXML
    private TableColumn<CustomerTM, String> colCusOrderDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtCusId;

    @FXML
    private TextField txtCusMobile;

    @FXML
    private TextField txtCusName;

    @FXML
    private TextField txtCusOrderID;

    private CustomerModel customerModel;

    @FXML
    public void initialize() {
        customerModel = new CustomerModel();
        initCustomerTable();
        tblCustomer.setOnMouseClicked(this::handleTableClick);

        loadCustomerData();

    }
    private void handleTableClick(MouseEvent mouseEvent) {
        if (mouseEvent.getClickCount() == 1) {
            Object selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

            if (selectedItem instanceof CustomerTM) {
                CustomerTM selectedCustomer = (CustomerTM) selectedItem;
                showCustomerDetails(selectedCustomer);
            }
        }
    }

    private void initCustomerTable() {
        colCusId.setCellValueFactory(cellData -> cellData.getValue().customerIdProperty());
        colCusName.setCellValueFactory(cellData -> cellData.getValue().customerNameProperty());
        colCusMobileNo.setCellValueFactory(cellData -> cellData.getValue().customerMobileNoProperty().asObject());
        colCusOrderID.setCellValueFactory(cellData -> cellData.getValue().orderIDProperty());
        colCusOrderDate.setCellValueFactory(cellData -> cellData.getValue().orderDateProperty().asString());

        tblCustomer.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showCustomerDetails(newValue));
    }

    private void loadCustomerData() {
        try {
            tblCustomer.setItems(customerModel.getAllCustomers());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showCustomerDetails(CustomerTM customerTM) {
        if (customerTM != null) {
            txtCusId.setText(customerTM.getCustomerID());
            txtCusName.setText(customerTM.getCustomerName());
            txtCusMobile.setText(String.valueOf(customerTM.getCustomerMobile()));
            txtCusOrderID.setText(customerTM.getOrderID());

            // Convert java.util.Date to java.time.LocalDate
            Date orderDate = customerTM.getOrderDate();
            if (orderDate != null) {
                LocalDate localDate = orderDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                DateCustomerOrderDate.setValue(localDate);
            } else {
                DateCustomerOrderDate.setValue(null);
            }
        }
    }







    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btncustomerDeleteOnAction(ActionEvent event) {
        CustomerTM selectedCustomer = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedCustomer != null) {
            try {
                customerModel.deleteCustomer(selectedCustomer.getCustomerID());
                loadCustomerData();
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void btncustomerSaveOnAction(ActionEvent event) {
        saveCustomer();
    }

    @FXML
    public void btncustomerUpdateOnaction(ActionEvent actionEvent) {
        updateCustomer();
    }

    private void saveCustomer() {
        try {
            String customerId = txtCusId.getText();
            String customerName = txtCusName.getText();
            int customerMobile = Integer.parseInt(txtCusMobile.getText());
            String orderID = txtCusOrderID.getText();
            java.sql.Date orderDate = java.sql.Date.valueOf(DateCustomerOrderDate.getValue());

            CustomerDTO customerDTO = new CustomerDTO(customerId, customerName, String.valueOf(customerMobile), orderID, orderDate);
            customerModel.saveCustomer(customerDTO);

            loadCustomerData();
            clearFields();

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateCustomer() {
        try {
            String customerId = txtCusId.getText();
            String customerName = txtCusName.getText();
            int customerMobile = Integer.parseInt(txtCusMobile.getText());
            String orderID = txtCusOrderID.getText();
            java.sql.Date orderDate = java.sql.Date.valueOf(DateCustomerOrderDate.getValue());

            CustomerDTO customerDTO = new CustomerDTO(customerId, customerName, String.valueOf(customerMobile), orderID, orderDate);
            customerModel.updateCustomer(customerDTO);

            loadCustomerData();
            clearFields();

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
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

    private void clearFields() {
        txtCusId.clear();
        txtCusName.clear();
        txtCusMobile.clear();
        txtCusOrderID.clear();
        DateCustomerOrderDate.setValue(null);
        tblCustomer.getSelectionModel().clearSelection();
    }
    private void closeResources() {

    }
}
