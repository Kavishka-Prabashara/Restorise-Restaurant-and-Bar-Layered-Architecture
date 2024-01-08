package lk.restorise.restorisems.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import lk.restorise.restorisems.db.DBConnection;
import lk.restorise.restorisems.dto.tm.StockTM;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class StockModel {

    public static void copySupplierDataToStock() throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        // Check if STOCK_SUPPLIER_ID already exists in Stockdetails
        String checkExistingQuery = "SELECT COUNT(*) FROM Stockdetails WHERE STOCK_SUPPLIER_ID = ?";
        String insertQuery = "INSERT INTO Stockdetails(STOCK_SUPPLIER_ID, STOCK_SUPPLIER_NAME, STOCK_ITEM_ID, STOCK_SUPPLY_ITEM, STOCK_SUPPLY_ITEMSTOCKQTY, STOCK_SUPPLY_DATE) " +
                "SELECT SUPPLIER_ID, NAME, ITEM_ID, SUPPLY_ITEM, SUPPLY_ITEMSTOCKQTY, SUPPLY_DATE FROM supplierdetails WHERE SUPPLIER_ID = ?";

        try (PreparedStatement checkExistingStatement = connection.prepareStatement(checkExistingQuery);
             PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {

            // Get distinct supplier IDs from supplierdetails
            Set<String> supplierIDs = getDistinctSupplierIDs(connection);

            // Iterate over distinct supplier IDs
            for (String supplierID : supplierIDs) {
                // Check if the same STOCK_SUPPLIER_ID exists in Stockdetails
                checkExistingStatement.setString(1, supplierID);
                ResultSet resultSet = checkExistingStatement.executeQuery();
                resultSet.next();
                int count = resultSet.getInt(1);

                // If the STOCK_SUPPLIER_ID doesn't exist, insert the record
                if (count == 0) {
                    insertStatement.setString(1, supplierID);
                    insertStatement.executeUpdate();
                }
            }
        }
 }

    private static Set<String> getDistinctSupplierIDs(Connection connection) throws SQLException {
        Set<String> supplierIDs = new HashSet<>();
        String query = "SELECT DISTINCT SUPPLIER_ID FROM supplierdetails";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                supplierIDs.add(resultSet.getString("SUPPLIER_ID"));
            }
        }
        return supplierIDs;
    }




    public static ObservableList<StockTM> getStockDetails() throws SQLException {
        ObservableList<StockTM> stockList = FXCollections.observableArrayList();
        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT * FROM Stockdetails";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                StockTM stockTM = new StockTM(
                        resultSet.getString("STOCK_SUPPLIER_ID"),
                        resultSet.getString("STOCK_SUPPLIER_NAME"),
                        resultSet.getString("STOCK_ITEM_ID"),
                        resultSet.getString("STOCK_SUPPLY_ITEM"),
                        resultSet.getInt("STOCK_SUPPLY_ITEMSTOCKQTY"),
                        resultSet.getString("STOCK_SUPPLY_DATE")
                );
                stockList.add(stockTM);
            }
        }
        return stockList;
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
    public static void updateStock(StockTM updatedStock) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String updateQuery = "UPDATE stockdetails SET " +
                "STOCK_SUPPLIER_NAME = ?, " +
                "STOCK_ITEM_ID = ?, " +
                "STOCK_SUPPLY_ITEM = ?, " +
                "STOCK_SUPPLY_ITEMSTOCKQTY = ?, " +
                "STOCK_SUPPLY_DATE = ? " +
                "WHERE STOCK_SUPPLIER_ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, updatedStock.getSupplierName());
            preparedStatement.setString(2, updatedStock.getSuppliedItemID());
            preparedStatement.setString(3, updatedStock.getSuppliedItemType());
            preparedStatement.setInt(4, updatedStock.getStockQty());
            preparedStatement.setString(5, updatedStock.getSuppliedDate());
            preparedStatement.setString(6, updatedStock.getSupplierID());

            preparedStatement.executeUpdate();
        }
    }

    public static void deleteStock(StockTM stockToDelete) throws SQLException {
        // Show a confirmation dialog
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete Confirmation");
        alert.setContentText("Are you sure you want to delete the selected stock?");

        Optional<ButtonType> result = alert.showAndWait();

        // Check if the user clicked OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Connection connection = DBConnection.getInstance().getConnection();
            String deleteQuery = "DELETE FROM stockdetails WHERE STOCK_SUPPLIER_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setString(1, stockToDelete.getSupplierID());

                preparedStatement.executeUpdate();
            }
        }
    }

    public static void updateStockQuantity(String suppliedItemID, int newQuantity) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        // Start transaction
        connection.setAutoCommit(false);

        try {
            // Update the stock quantity
            String updateQuery = "UPDATE Stockdetails SET STOCK_SUPPLY_ITEMSTOCKQTY = ? WHERE STOCK_ITEM_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setInt(1, newQuantity);
                preparedStatement.setString(2, suppliedItemID);
                preparedStatement.executeUpdate();
            }

            // Commit the transaction if everything is successful
            connection.commit();
        } catch (SQLException e) {
            // Rollback the transaction if there's an exception
            connection.rollback();
            throw e; // Rethrow the exception for handling at a higher level
        } finally {
            // Reset auto-commit to true after the transaction
            connection.setAutoCommit(true);
        }
    }
}
