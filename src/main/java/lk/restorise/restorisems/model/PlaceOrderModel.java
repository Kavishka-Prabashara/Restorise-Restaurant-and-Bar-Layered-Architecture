package lk.restorise.restorisems.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.restorise.restorisems.db.DBConnection;
import lk.restorise.restorisems.dto.tm.PlaceOrderTM;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceOrderModel {

    public ObservableList<PlaceOrderTM> getAllAddtoCarts() throws SQLException {
        ObservableList<PlaceOrderTM> addtoCartList = FXCollections.observableArrayList();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DBConnection.getInstance().getConnection();
            String query = "SELECT * FROM addtocart";

            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String itemCategory = resultSet.getString("itemType");
                String itemName = resultSet.getString("itemName");
                double itemUnitPrice = resultSet.getDouble("unitPrice");
                int itemQTY = resultSet.getInt("qty");
                double itemTotal = resultSet.getDouble("total");

                PlaceOrderTM placeOrderTM = new PlaceOrderTM(itemCategory, itemName, itemUnitPrice, itemQTY, itemTotal);
                addtoCartList.add(placeOrderTM);
            }
        } finally {

        }

        return addtoCartList;
    }

    @SneakyThrows
    public void cancelOrder() {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            // SQL query to delete all records from the addtocart table
            String deleteQuery = "DELETE FROM addtocart";

            // Create a PreparedStatement
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);

            // Execute the delete query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error deleting records from the addtocart table", e);
        } finally {

        }
    }

    public static void deductStock(String suppliedItemID, int qty) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String updateQuery = "UPDATE Stockdetails SET STOCK_SUPPLY_ITEMSTOCKQTY = STOCK_SUPPLY_ITEMSTOCKQTY - ? WHERE STOCK_ITEM_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setInt(1, qty);
            preparedStatement.setString(2, suppliedItemID);
            preparedStatement.executeUpdate();
        }
    }

    public void placeOrder() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            String insertOrderQuery = "INSERT INTO orders (itemName, unitPrice, qty, total, itemType) " +
                    "SELECT itemName, unitPrice, qty, total, itemType FROM addtocart";


            try (PreparedStatement insertOrderStatement = connection.prepareStatement(insertOrderQuery)) {
                insertOrderStatement.executeUpdate();
            }

            cancelOrder();
        }finally {

        }
    }

}