package lk.restorise.restorisems.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.SneakyThrows;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class DashboardMenuController {

    @FXML
    private JFXButton btnemployee;
    @FXML
    private AnchorPane dashboardpane;

    @FXML
    private AnchorPane root;

    @SneakyThrows
    @FXML
    private void initialize(){
        displayDashbaoard();
    }

    private void displayDashbaoard() throws IOException {
        Parent load=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/DashBoardForm.fxml")));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    @FXML
    void btnemployeeonaction(ActionEvent event) throws IOException {
        Parent load=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/EmployeeForm.fxml")));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void ondashboardonaction(ActionEvent actionEvent) throws IOException {

        Parent load=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/DashBoardForm.fxml")));
        root.getChildren().clear();
        root.getChildren().add(load);

    }

    public void btnmanagecustomeronaction(ActionEvent actionEvent) throws IOException {
        Parent load=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/CustomerForm.fxml")));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btnrestoriseonaction(ActionEvent actionEvent) throws IOException {
        Parent load=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/RestoRiseMenuForm.fxml")));
        root.getChildren().clear();
        root.getChildren().add(load);

    }

    public void btnattendanceOnAction(ActionEvent actionEvent) throws IOException {
        Parent load=FXMLLoader.load(getClass().getResource("/view/AttendanceForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btntableOnAction(ActionEvent actionEvent) throws IOException {
        Parent load=FXMLLoader.load(getClass().getResource("/view/TableForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btnpaymentOnAction(ActionEvent actionEvent) throws IOException {
        Parent load=FXMLLoader.load(getClass().getResource("/view/PaymentForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btnplaceorderOnAction(ActionEvent actionEvent) throws IOException {
        Parent load=FXMLLoader.load(getClass().getResource("/view/PlaceOrderForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btnmanagesupplieronaction(ActionEvent actionEvent) throws IOException {
        Parent load=FXMLLoader.load(getClass().getResource("/view/SupplierForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btnuserOnAction(ActionEvent actionEvent) throws IOException {
        Parent load=FXMLLoader.load(getClass().getResource("/view/UserDetailsChangeForm.fxml"));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginForm.fxml")));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)dashboardpane.getScene().getWindow();

        stage.setScene(scene);


        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    public void btnInventoryOnAction(ActionEvent actionEvent) throws IOException {
        Parent load=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/StockForm.fxml")));
        root.getChildren().clear();
        root.getChildren().add(load);
    }

    public void btnPlaceOrderOnAction(ActionEvent actionEvent) throws IOException {
        Parent load=FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/PlaceOrderForm.fxml")));
        root.getChildren().clear();
        root.getChildren().add(load);
    }
}
