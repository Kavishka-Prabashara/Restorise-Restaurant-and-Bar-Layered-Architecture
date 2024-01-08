package lk.restorise.restorisems.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.restorise.restorisems.db.DBConnection;
import lk.restorise.restorisems.dto.tm.PlaceOrderTM;
import lk.restorise.restorisems.model.PlaceOrderModel;
import lk.restorise.restorisems.model.StockModel;
import lombok.SneakyThrows;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlaceOrderController {

    @FXML
    private JFXButton btnCancelOrder;

    @FXML
    private JFXButton btnProceedToCheckOut;

    @FXML
    private ComboBox<String> cbCustomerID;

    @FXML
    private TableColumn<PlaceOrderTM, String> colItemCategory;

    @FXML
    private TableColumn<PlaceOrderTM, String> colItemName;

    @FXML
    private TableColumn<PlaceOrderTM, Integer> colQTY;

    @FXML
    private TableColumn<PlaceOrderTM, Double> colTotal;

    @FXML
    private TableColumn<PlaceOrderTM, Double> colUnitPrice;

    @FXML
    private Label lblChange;

    @FXML
    private Label lblnetTotal;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<PlaceOrderTM> tblAddToCart;

    @FXML
    private TextField txtCash;

    private PlaceOrderModel placeOrderModel;

    StockModel stockModel;
    @FXML
    void btnCancelOrderOnAction(ActionEvent event) throws SQLException {
        placeOrderModel.cancelOrder();
    }




    @FXML
    void btnProceedToCheckOutOnAction(ActionEvent event) {
        try {

            placeOrderModel.placeOrder();


            for (PlaceOrderTM item : tblAddToCart.getItems()) {
                StockModel.deductStock(item.getSuppliedItemID(), item.getItemQTY());
            }


            placeOrderModel.cancelOrder();

            loadAddtoCartData();

            updateTotalLabel();
            updateChangeLabel();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }







    @FXML
    public void initialize() {
        placeOrderModel = new PlaceOrderModel();
        initAddtoCartTable();
        tblAddToCart.setOnMouseClicked(this::handleTableClick);
        loadAddtoCartData();
        updateTotalLabel();
        updateChangeLabel();



    }
    private void handleTableClick(MouseEvent mouseEvent) {
    }

    private void initAddtoCartTable() {
        colItemCategory.setCellValueFactory(cellData -> cellData.getValue().itemCategoryProperty());
        colItemName.setCellValueFactory(cellData -> cellData.getValue().itemNameProperty());
        colUnitPrice.setCellValueFactory(cellData -> cellData.getValue().itemPriceProperty().asObject());
        colQTY.setCellValueFactory(cellData -> cellData.getValue().itemQTYProperty().asObject());
        colTotal.setCellValueFactory(cellData -> cellData.getValue().itemTotalProperty().asObject());
    }

    private void loadAddtoCartData() {
        try {
            ObservableList<PlaceOrderTM> addtoCartList = placeOrderModel.getAllAddtoCarts();
            tblAddToCart.setItems(addtoCartList);
        } catch (SQLException e) {
            e.printStackTrace();
        }}


    @FXML
    public void btnEnterOnAction(ActionEvent actionEvent) {
        updateLabelsOnEnter();
    }
    private void updateLabelsOnEnter() {
        updateTotalLabel();
        updateChangeLabel();
    }
    private void updateChangeLabel() {
        double total = calculateTotal();
        double cash = parseCash();
        double change = calculateChange(total, cash);
        lblChange.setText("Change: " + change);
    }

    private void updateTotalLabel() {
        double total = calculateTotal();
        lblnetTotal.setText("Net Total:LKR " + total);
    }

    private double calculateTotal() {
        double total = 0.0;
        for (PlaceOrderTM placeOrderTM : tblAddToCart.getItems()) {
            total += placeOrderTM.getItemTotal();
        }
        return total;
    }

    private double calculateChange(double total, double cash) {
        return cash - total;
    }

    private double parseCash() {
        try {
            return Double.parseDouble(txtCash.getText());
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}