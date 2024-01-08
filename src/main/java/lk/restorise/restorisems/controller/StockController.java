package lk.restorise.restorisems.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.restorise.restorisems.dto.tm.StockTM;
import lk.restorise.restorisems.model.StockModel;
import lombok.SneakyThrows;

import java.sql.SQLException;
import java.util.Optional;

public class StockController {

    @FXML
    private TableColumn<StockTM, Integer> colStockQTY;

    @FXML
    private TableColumn<StockTM, String> colStockSuppliedDate;

    @FXML
    private TableColumn<StockTM, String> colStockSuppliedItemID;

    @FXML
    private TableColumn<StockTM, String> colStockSuppliedItemType;

    @FXML
    private TableColumn<StockTM, String> colStockSupplierID;

    @FXML
    private TableColumn<StockTM, String> colStockSupplierName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<StockTM> tblStock;
    @FXML
    private Button btnstocksUpdate;
    @FXML
    private Button btngetStocksJasparrreports;
    @FXML
    private Button btnstocksDelete;


    private boolean isDataCopied = false; // Variable to track whether data is copied

    @FXML
    private void initialize() {
        try {
            // Check whether data is already copied
            if (!isDataCopied) {
                // Copy data from Suppliers to Stock
                StockModel.copySupplierDataToStock();
                isDataCopied = true; // Mark data as copied
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Load StockDetails into UI
        loadStockDetails();
        tblStock.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // Add listener for selection change
        tblStock.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Handle selection change (e.g., update UI components)
                handleSelectionChange(newSelection);
            }
        });

    }
    @FXML
    private void updateQuantityOnAction(ActionEvent actionEvent) {
        StockTM selectedStock = tblStock.getSelectionModel().getSelectedItem();
        if (selectedStock != null) {
            // Prompt the user for the new quantity, you can use a TextInputDialog
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Update Quantity");
            dialog.setHeaderText("Enter the new quantity:");
            dialog.setContentText("Quantity:");

            Optional<String> result = dialog.showAndWait();
            result.ifPresent(newQuantity -> {
                try {
                    int quantity = Integer.parseInt(newQuantity);
                    StockModel.updateStockQuantity(selectedStock.getSuppliedItemID(), quantity);
                    // Refresh the table after update
                    loadStockDetails();
                } catch (NumberFormatException | SQLException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
            });
        }
    }
    @FXML
    private void updateQuantityOnAction() {
        StockTM selectedStock = tblStock.getSelectionModel().getSelectedItem();
        if (selectedStock != null) {
            try {
                // Set the new quantity programmatically, for example, increase by 5
                int newQuantity = selectedStock.getStockQty() + 5;
                StockModel.updateStockQuantity(selectedStock.getSuppliedItemID(), newQuantity);

                // Refresh the table after update
                loadStockDetails();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }


    private void handleSelectionChange(StockTM selectedStock) {
        // Example: Display selected data in text fields

        // Set other text fields as needed...

        // You can enable/disable buttons based on the selection if needed
        btnstocksUpdate.setDisable(false);
        btnstocksDelete.setDisable(false);
    }



    private void loadStockDetails() {
        try {
            // Clear existing items in the table
            tblStock.getItems().clear();

            // Set cell value factories for each column
            colStockSupplierID.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
            colStockSupplierName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
            colStockSuppliedItemID.setCellValueFactory(new PropertyValueFactory<>("suppliedItemID"));
            colStockSuppliedItemType.setCellValueFactory(new PropertyValueFactory<>("suppliedItemType"));
            colStockQTY.setCellValueFactory(new PropertyValueFactory<>("stockQty"));
            colStockSuppliedDate.setCellValueFactory(new PropertyValueFactory<>("suppliedDate"));

            // Load data from the model to the table
            tblStock.getItems().addAll(StockModel.getStockDetails());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void stocksUpdateOnaAction(ActionEvent actionEvent) {

            StockTM selectedStock = tblStock.getSelectionModel().getSelectedItem();
            if (selectedStock != null) {
                // Perform update operation using selectedStock
                // Example: Call a method in your StockModel to update the data
                try {
                    StockModel.updateStock(selectedStock);
                    // Refresh the table after update
                    loadStockDetails();
                } catch (SQLException e) {
                    e.printStackTrace(); // Handle the exception appropriately
                }
            }
        }


    public void getStocksJasparrreportsOnAction(ActionEvent actionEvent) {
    }

    public void stocksDeleteOnAction(ActionEvent actionEvent) {
        StockTM selectedStock = tblStock.getSelectionModel().getSelectedItem();
        if (selectedStock != null) {
            // Perform delete operation using selectedStock
            // Example: Call a method in your StockModel to delete the data
            try {
                StockModel.deleteStock(selectedStock);
                // Refresh the table after delete
                loadStockDetails();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
