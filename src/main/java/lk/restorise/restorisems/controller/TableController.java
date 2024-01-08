package lk.restorise.restorisems.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.restorise.restorisems.dto.TableDTO;
import lk.restorise.restorisems.dto.tm.TableTM;
import lk.restorise.restorisems.model.TableModel;

import java.sql.SQLException;

public class TableController {

    @FXML
    private JFXButton btnClear;

    @FXML
    private JFXButton btnDelete;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<TableTM, Integer> colTableID;

    @FXML
    private TableColumn<TableTM, Integer> colTableNoOfChairs;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<TableTM> tblTable;

    @FXML
    private TextField txtTableID;

    @FXML
    private TextField txtTableNoOfChairs;

    private TableModel tableModel;

    @FXML
    public void initialize() {
        tableModel = new TableModel();
        initTable();
        loadTableData();
    }

    private void initTable() {
        colTableID.setCellValueFactory(cellData -> cellData.getValue().tableIDProperty().asObject());
        colTableNoOfChairs.setCellValueFactory(cellData -> cellData.getValue().noOfChairsProperty().asObject());

        tblTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showTableDetails(newValue));
    }

    private void loadTableData() {
        try {
            tblTable.setItems(tableModel.getAllTables());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showTableDetails(TableTM tableTM) {
        if (tableTM != null) {
            txtTableID.setText(String.valueOf(tableTM.getTableID()));
            txtTableNoOfChairs.setText(String.valueOf(tableTM.getNoOfChairs()));
        }
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        TableTM selectedTable = tblTable.getSelectionModel().getSelectedItem();
        if (selectedTable != null) {
            try {
                tableModel.deleteTable(selectedTable.getTableID());
                loadTableData();
                clearFields();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        saveTable();
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        updateTable();
    }

    private void saveTable() {
        try {
            int tableID = Integer.parseInt(txtTableID.getText());
            int noOfChairs = Integer.parseInt(txtTableNoOfChairs.getText());

            TableDTO tableDTO = new TableDTO(tableID, noOfChairs);
            tableModel.insertTable(tableDTO);

            loadTableData();
            clearFields();

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateTable() {
        try {
            int tableID = Integer.parseInt(txtTableID.getText());
            int noOfChairs = Integer.parseInt(txtTableNoOfChairs.getText());

            TableDTO tableDTO = new TableDTO(tableID, noOfChairs);
            tableModel.updateTable(tableDTO);

            loadTableData();
            clearFields();

        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        txtTableID.clear();
        txtTableNoOfChairs.clear();
        tblTable.getSelectionModel().clearSelection();
    }
}

