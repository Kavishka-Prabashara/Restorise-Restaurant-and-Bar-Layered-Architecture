package lk.restorise.restorisems.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.restorise.restorisems.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RestoRiseModel {
    private String itemName;
    private double unitPrice;
    private int qty;
    private double total;
    private String itemType;

    public RestoRiseModel() {
        // Default constructor
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public ObservableList<String> getAllCustomerIDs() throws SQLException {
        ObservableList<String> customerIDs = FXCollections.observableArrayList();

        try (Connection connection = DBConnection.getInstance().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT customer_id FROM customer_details");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                customerIDs.add(resultSet.getString("customer_id"));
            }
        }

        return customerIDs;
    }
}
