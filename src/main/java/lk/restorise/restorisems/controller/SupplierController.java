package lk.restorise.restorisems.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lk.restorise.restorisems.dto.SupplierDTO;
import lk.restorise.restorisems.model.SupplierModel;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

public class SupplierController {

    @FXML
    private TextField txtSupplierID;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private TextField txtEmpMobile;

    @FXML
    private DatePicker dtSupplyingDate;

    @FXML
    private TextField txtSupplierEmail;

    @FXML
    private TextField txtSupplyItemID;

    @FXML
    private TextField txtSupplySTOCKQTY;

    @FXML
    private ComboBox<String> cbSupplyItem;

    @FXML
    private TableView<SupplierDTO> tblSupplier;


    @FXML
    private TableColumn<SupplierDTO, String> colSupplierID;

    @FXML
    private TableColumn<SupplierDTO, String> colSupplierName;

    @FXML
    private TableColumn<SupplierDTO, Integer> colSupplierMobileNo;

    @FXML
    private TableColumn<SupplierDTO, String> colSupplierEmail;

    @FXML
    private TableColumn<SupplierDTO, String> colSupplyItemID;

    @FXML
    private TableColumn<SupplierDTO, String> colSupplierItemName;

    @FXML
    private TableColumn<SupplierDTO, String> colSupplyDate;

    @FXML
    private TableColumn<?, ?> colSuppliedStockQTY;


    @FXML
    private Button btnSupplierSaveOnAction;

    @FXML
    private Button btnSupplierUpdateOnAction;

    @FXML
    private Button btnsupplierDeleteOnaction;
    private ObservableList<SupplierDTO> supplierData = FXCollections.observableArrayList();



    private SupplierDTO selectedSupplier;





        @FXML
        void SupplierSaveOnAction(ActionEvent event) {
            String supplier_ID = txtSupplierID.getText();
            String supplier_NAME = txtSupplierName.getText();
            int supplier_MOBILENO = Integer.parseInt(txtEmpMobile.getText());
            var dto = getSupplierDTO(supplier_ID, supplier_NAME, supplier_MOBILENO);

            // New code to handle txtSupplySTOCKQTY
            int suppliedStockQTY = Integer.parseInt(txtSupplySTOCKQTY.getText());
            dto.setSuppliedStockQTY(suppliedStockQTY);

            try {
                boolean isSaved = SupplierModel.saveSupplier(dto);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier saved!").show();
                    clearFields();

                    // Reload data from the database and update the TableView
                    loadSuppliers();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }      }

    private SupplierDTO getSupplierDTO(String supplier_ID, String supplier_NAME, int supplier_MOBILENO) {
        String supplier_EMAIL = txtSupplierEmail.getText();
        // Set txtSuppyItemID from ComboBox value
        String supply_ITEMID = txtSupplyItemID.getText();
        String supply_ITEMNAME = cbSupplyItem.getValue(); // You may want to adjust this based on your requirements
        LocalDate supply_ITEMDATE = dtSupplyingDate.getValue();
        int suppliedStockItem= Integer.parseInt(txtSupplySTOCKQTY.getText());
        var dto = new SupplierDTO(supplier_ID, supplier_NAME, supplier_MOBILENO, supplier_EMAIL, supply_ITEMID, supply_ITEMNAME, supply_ITEMDATE,suppliedStockItem);
        return dto;
    }

    private void clearFields() {

            txtSupplierID.clear();
            txtSupplierName.clear();
            txtEmpMobile.clear();
            txtSupplierEmail.clear();
            txtSupplyItemID.clear();
            cbSupplyItem.getSelectionModel().clearSelection();
            dtSupplyingDate.setValue(null);
        }





        private void loadSuppliers() {
            try {
                // Fetch suppliers from the database and set them in the table
                tblSupplier.setItems(FXCollections.observableArrayList(SupplierModel.getAllSuppliers()));
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }



    @FXML
    void SupplierUpdateOnAction(ActionEvent event) {
        if (selectedSupplier != null) {
            SupplierDTO updatedDTO = getSupplierDTOFromUI();

            try {
                boolean isUpdated = SupplierModel.updateSupplier(updatedDTO);
                if (isUpdated) {
                    // Update the data in the supplierData list
                    int selectedIndex = supplierData.indexOf(selectedSupplier);
                    supplierData.set(selectedIndex, updatedDTO);

                    // Update the data in the TableView
                    tblSupplier.setItems(FXCollections.observableArrayList(supplierData));

                    new Alert(Alert.AlertType.CONFIRMATION, "Supplier updated!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Update failed!").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "SQL Error: " + e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Select a supplier to update.").show();
        }

    }





    private SupplierDTO getSupplierDTOFromUI() {
        String supplierId = txtSupplierID.getText();
        String supplierName = txtSupplierName.getText();
        int supplierMobile = Integer.parseInt(txtEmpMobile.getText());
        String supplierEmail = txtSupplierEmail.getText();
        String supplyItemId = txtSupplyItemID.getText();
        String supplyItemName = cbSupplyItem.getValue();
        LocalDate supplyDate = dtSupplyingDate.getValue();
        int suppliedStockQTY = Integer.parseInt(txtSupplySTOCKQTY.getText());


        return new SupplierDTO(supplierId, supplierName, supplierMobile, supplierEmail, supplyItemId, supplyItemName, supplyDate,suppliedStockQTY);
    }



    @FXML
    void supplierDeleteOnaction(ActionEvent event) {
        SupplierDTO selectedDTO = tblSupplier.getSelectionModel().getSelectedItem();
        if (selectedDTO != null) {
            Optional<ButtonType> result = showConfirmationDialog("Delete Confirmation", "Are you sure you want to delete?");

            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    boolean isDeleted = SupplierModel.deleteSupplier(selectedDTO.getTxtSupplierID());
                    if (isDeleted) {
                        // Remove the deleted item from the TableView
                        supplierData.remove(selectedDTO);
                        new Alert(Alert.AlertType.CONFIRMATION, "Supplier deleted!").show();
                        clearFields();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Select a supplier to delete.").show();
        }
    }


    private Optional<ButtonType> showConfirmationDialog(String title, String content) {
        Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationDialog.setTitle(title);
        confirmationDialog.setHeaderText(null);
        confirmationDialog.setContentText(content);

        return confirmationDialog.showAndWait();
    }



    @FXML
    void initialize() {

        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("txtSupplierID"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("txtSupplierName"));
        colSupplierMobileNo.setCellValueFactory(new PropertyValueFactory<>("txtEmpMobile"));
        colSupplierEmail.setCellValueFactory(new PropertyValueFactory<>("txtSupplierEmail"));
        colSupplyItemID.setCellValueFactory(new PropertyValueFactory<>("txtSuppyItemID"));
        colSupplierItemName.setCellValueFactory(new PropertyValueFactory<>("cbSupplyItem"));
        colSupplyDate.setCellValueFactory(new PropertyValueFactory<>("dtSupplyingDate"));
        colSuppliedStockQTY.setCellValueFactory(new PropertyValueFactory<>("suppliedStockQTY"));



        cbSupplyItem.setItems(FXCollections.observableArrayList("Kitchen Ingradients", "Bar Drinks", "Desserts","Snackss","Beverages"));


        tblSupplier.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                // Get the selected item from the TableView
                SupplierDTO selectedItem = tblSupplier.getSelectionModel().getSelectedItem();

                if (selectedItem != null) {
                    selectedSupplier = selectedItem;
                    updateUI(selectedSupplier);
                }
            }
        });


        loadSuppliers();
        loadTableData();


    }

    private void handleTableClick(MouseEvent event) {
        if (event.getClickCount() == 1) { // Check for a single click
            // Get the selected item from the TableView
            Object selectedItem = tblSupplier.getSelectionModel().getSelectedItem();

            if (selectedItem instanceof SupplierDTO) {
                SupplierDTO selectedSupplier = (SupplierDTO) selectedItem;
                // Update UI with the selected supplier's information (optional)
                updateUI(selectedSupplier);
            }
        }
    }
    private void updateUI(SupplierDTO supplier) {
        txtSupplierID.setText(supplier.getTxtSupplierID());
        txtSupplierName.setText(supplier.getTxtSupplierName());
        txtEmpMobile.setText(String.valueOf(supplier.getTxtEmpMobile()));
        txtSupplierEmail.setText(supplier.getTxtSupplierEmail());
        txtSupplyItemID.setText(supplier.getTxtSuppyItemID());
        cbSupplyItem.setValue(supplier.getCbSupplyItem());
        dtSupplyingDate.setValue(supplier.getDtSupplyingDate());
        txtSupplySTOCKQTY.setText(String.valueOf(supplier.getSuppliedStockQTY())); // Add this line

    }
    private void loadTableData() {
        try {
            supplierData.clear();
            supplierData.addAll(SupplierModel.getAllSuppliers());
            tblSupplier.setItems(supplierData);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void supplierClearOnaction(ActionEvent actionEvent){

        txtSupplierID.clear();
        txtSupplierName.clear();
        txtEmpMobile.clear();
        txtSupplierEmail.clear();
        txtSupplyItemID.clear();
        cbSupplyItem.setValue(null);
        dtSupplyingDate.setValue(null);

    }
}


