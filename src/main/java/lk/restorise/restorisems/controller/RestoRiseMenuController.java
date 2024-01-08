// RestoRiseMenuController.java
package lk.restorise.restorisems.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.restorise.restorisems.db.DBConnection;
import lk.restorise.restorisems.dto.RestoRiseDTO;
import lk.restorise.restorisems.dto.tm.RestoRiseTM;

import lk.restorise.restorisems.model.RestoRiseModel;
import lombok.SneakyThrows;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

public class RestoRiseMenuController implements Initializable {

    @FXML
    private JFXButton btnProceedToCheckOut;
    @FXML
    private JFXButton btnCancelOrder;
    @FXML
    private Label lblnetTotal;
    @FXML
    private TextField txtCash;
    @FXML
    private Label lblChange;
    @FXML
    private Label lblItemCategory1;
    @FXML
    private Label lblItemCategory2;
    @FXML
    private Label lblItemCategory3;
    @FXML
    private Label lblItemCategory4;
    @FXML
    private Label lblItemCategory5;
    @FXML
    private Label lblItemCategory6;

    @FXML
    private Label lblBEVERAGESItemPrice1;
    @FXML
    private Label lblBEVERAGESItemPrice2;
    @FXML
    private Label lblBEVERAGESItemPrice3;
    @FXML
    private Label lblBEVERAGESItemPrice4;
    @FXML
    private Label lblBEVERAGESItemPrice5;

    @FXML
    private Label lblSNACKERSItemName1;
    @FXML
    private Label lblSNACKERSItemName2;
    @FXML
    private Label lblSNACKERSItemName3;
    @FXML
    private Label lblSNACKERSItemName4;
    @FXML
    private Label lblSNACKERSItemName5;


    @FXML
    private Label lblSNACKERSItemPrice1;
    @FXML
    private Label lblSNACKERSItemPrice2;
    @FXML
    private Label lblSNACKERSItemPrice3;
    @FXML
    private Label lblSNACKERSItemPrice4;
    @FXML
    private Label lblSNACKERSItemPrice5;

    @FXML
    private Spinner<Integer> SNACKERSspinnerQTY1;

    @FXML
    private Spinner<Integer> SNACKERSspinnerQTY2;
    @FXML
    private Spinner<Integer> SNACKERSspinnerQTY3;
    @FXML
    private Spinner<Integer> SNACKERSspinnerQTY4;
    @FXML
    private Spinner<Integer> SNACKERSspinnerQTY5;

    @FXML
    private Spinner<Integer> BARDRINKSspinnerQTY1;

    @FXML
    private Spinner<Integer> BARDRINKSspinnerQTY2;

    @FXML
    private Spinner<Integer> BARDRINKSspinnerQTY3;

    @FXML
    private Spinner<Integer> BARDRINKSspinnerQTY4;

    @FXML
    private Spinner<Integer> BARDRINKSspinnerQTY5;

    @FXML
    private Spinner<Integer> BEVERAGESspinnerQTY1;

    @FXML
    private Spinner<Integer> BEVERAGESspinnerQTY2;

    @FXML
    private Spinner<Integer> BEVERAGESspinnerQTY3;

    @FXML
    private Spinner<Integer> BEVERAGESspinnerQTY4;

    @FXML
    private Spinner<Integer> BEVERAGESspinnerQTY5;

    @FXML
    private Spinner<Integer> DESSERTSspinnerQTY1;

    @FXML
    private Spinner<Integer> DESSERTSspinnerQTY2;

    @FXML
    private Spinner<Integer> DESSERTSspinnerQTY3;

    @FXML
    private Spinner<Integer> DESSERTSspinnerQTY4;

    @FXML
    private Spinner<Integer> DESSERTSspinnerQTY5;
    @FXML
    private Label lblDESSERTSItemName1;

    @FXML
    private Label lblDESSERTSItemName2;

    @FXML
    private Label lblDESSERTSItemName3;

    @FXML
    private Label lblDESSERTSItemName4;

    @FXML
    private Label lblDESSERTSItemName5;

    @FXML
    private Label lblDESSERTSItemPrice1;

    @FXML
    private Label lblDESSERTSItemPrice2;

    @FXML
    private Label lblDESSERTSItemPrice3;

    @FXML
    private Label lblDESSERTSItemPrice4;

    @FXML
    private Label lblDESSERTSItemPrice5;

    @FXML
    private JFXButton btnBardrinks1Add;

    @FXML
    private JFXButton btnBardrinks2Add;

    @FXML
    private JFXButton btnBardrinks3Add;

    @FXML
    private JFXButton btnBardrinks4Add;

    @FXML
    private JFXButton btnBardrinks5Add;

    @FXML
    private Label lblBARDRINKSItemName1;

    @FXML
    private Label lblBARDRINKSItemName2;

    @FXML
    private Label lblBARDRINKSItemName3;

    @FXML
    private Label lblBARDRINKSItemName4;

    @FXML
    private Label lblBARDRINKSItemName5;

    @FXML
    private Label lblBARDRINKSItemPrice1;

    @FXML
    private Label lblBARDRINKSItemPrice2;

    @FXML
    private Label lblBARDRINKSItemPrice3;

    @FXML
    private Label lblBARDRINKSItemPrice4;

    @FXML
    private Label lblBARDRINKSItemPrice5;

    @FXML
    private Spinner<Integer> DISHspinnerQTY1;

    @FXML
    private Spinner<Integer> DISHspinnerQTY2;

    @FXML
    private Spinner<Integer> DISHspinnerQTY3;

    @FXML
    private Spinner<Integer> DISHspinnerQTY4;

    @FXML
    private Spinner<Integer> DISHspinnerQTY5;

    @FXML
    private Spinner<Integer> SOUPSspinnerQTY1;

    @FXML
    private Spinner<Integer> SOUPSspinnerQTY2;

    @FXML
    private Spinner<Integer> SOUPSspinnerQTY3;

    @FXML
    private Spinner<Integer> SOUPSspinnerQTY4;

    @FXML
    private Spinner<Integer> SOUPSspinnerQTY5;


    @FXML
    private JFXButton btnDish1Add;

    @FXML
    private JFXButton btnDish2Add;

    @FXML
    private JFXButton btnDish3Add;

    @FXML
    private JFXButton btnDish4Add;

    @FXML
    private JFXButton btnDish5Add;

    @FXML
    private Label lblDISHESItemName1;

    @FXML
    private Label lblDISHESItemPrice1;

    @FXML
    private Label lblDISHESItemName2;

    @FXML
    private Label lblDISHESItemPrice2;

    @FXML
    private Label lblDISHESItemName3;

    @FXML
    private Label lblDISHESItemPrice3;

    @FXML
    private Label lblDISHESItemName4;

    @FXML
    private Label lblDISHESItemPrice4;

    @FXML
    private Label lblDISHESItemName5;

    @FXML
    private Label lblDISHESItemPrice5;

    @FXML
    private Label lblSOUPSItemName1;

    @FXML
    private Label lblSOUPSItemName2;

    @FXML
    private Label lblSOUPSItemName3;

    @FXML
    private Label lblSOUPSItemName4;

    @FXML
    private Label lblSOUPSItemName5;

    @FXML
    private Label lblSOUPSItemPrice1;

    @FXML
    private Label lblSOUPSItemPrice2;

    @FXML
    private Label lblSOUPSItemPrice3;

    @FXML
    private Label lblSOUPSItemPrice4;

    @FXML
    private Label lblSOUPSItemPrice5;

    @FXML
    private Label lblBEVERAGESItemName1;

    @FXML
    private Label lblBEVERAGESItemName2;

    @FXML
    private Label lblBEVERAGESItemName3;

    @FXML
    private Label lblBEVERAGESItemName4;

    @FXML
    private Label lblBEVERAGESItemName5;

    @FXML
    private AnchorPane placeOrderRoot;
    @FXML
    private TextField txtOrderID;
    @FXML
    private TextField txtOrderDate;

    @FXML
    void btnDish1AddOnAction(ActionEvent event) {
        // Get the values from the spinner, labels, etc.
        int qty = DISHspinnerQTY1.getValue();
        String itemName = lblDISHESItemName1.getText();
        double unitPrice = Double.parseDouble(lblDISHESItemPrice1.getText());

        // Calculate the total
        double total = qty * unitPrice;
        String itemType = lblItemCategory1.getText();

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        DISHspinnerQTY1.getValueFactory().setValue(1);
    }
    @FXML
    void btnDish2AddOnAction(ActionEvent event) {
        // Get the values from the spinner, labels, etc.
        int qty = DISHspinnerQTY2.getValue();
        String itemName = lblDISHESItemName2.getText();
        double unitPrice = Double.parseDouble(lblDISHESItemPrice2.getText());

        // Calculate the total
        double total = qty * unitPrice;
        String itemType = lblItemCategory1.getText();

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        DISHspinnerQTY2.getValueFactory().setValue(1);
    }

    private void saveToDatabase(RestoRiseDTO restoRiseDTO) {
        try {
            Connection connection = DBConnection.getInstance().getConnection();

            // Check if the connection is valid before proceeding
            if (connection != null ) {
                String sql = "INSERT INTO Addtocart (itemName, unitPrice, qty, total,itemType) VALUES (?, ?, ?, ?,?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    // Set the parameter values
                    preparedStatement.setString(1, restoRiseDTO.getItemName());
                    preparedStatement.setDouble(2, restoRiseDTO.getUnitPrice());
                    preparedStatement.setInt(3, restoRiseDTO.getQty());
                    preparedStatement.setDouble(4, restoRiseDTO.getTotal());
                    preparedStatement.setString(5, restoRiseDTO.getItemType());
                    // Execute the query
                    int rowsAffected = preparedStatement.executeUpdate();

                    System.out.println(rowsAffected + " row(s) affected");
                } catch (SQLException e) {
                    // Handle SQL exceptions
                    e.printStackTrace();
                }
            } else {
                System.err.println("Connection is closed or invalid.");
            }
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

    @SneakyThrows
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SpinnerValueFactory<Integer> valueFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10);
        valueFactory.setValue(1);
        ;

        // Set the spinner value factory
        DISHspinnerQTY1.setValueFactory(valueFactory);
        DISHspinnerQTY2.setValueFactory(valueFactory);
        DISHspinnerQTY3.setValueFactory(valueFactory);
        DISHspinnerQTY4.setValueFactory(valueFactory);
        DISHspinnerQTY5.setValueFactory(valueFactory);
        SOUPSspinnerQTY1.setValueFactory(valueFactory);
        SOUPSspinnerQTY2.setValueFactory(valueFactory);
        SOUPSspinnerQTY3.setValueFactory(valueFactory);
        SOUPSspinnerQTY4.setValueFactory(valueFactory);
        SOUPSspinnerQTY5.setValueFactory(valueFactory);
        BARDRINKSspinnerQTY1.setValueFactory(valueFactory);
        BARDRINKSspinnerQTY2.setValueFactory(valueFactory);
        BARDRINKSspinnerQTY3.setValueFactory(valueFactory);
        BARDRINKSspinnerQTY4.setValueFactory(valueFactory);
        BARDRINKSspinnerQTY5.setValueFactory(valueFactory);
        BEVERAGESspinnerQTY1.setValueFactory(valueFactory);
        BEVERAGESspinnerQTY2.setValueFactory(valueFactory);
        BEVERAGESspinnerQTY3.setValueFactory(valueFactory);
        BEVERAGESspinnerQTY4.setValueFactory(valueFactory);
        BEVERAGESspinnerQTY5.setValueFactory(valueFactory);
        SNACKERSspinnerQTY1.setValueFactory(valueFactory);
        SNACKERSspinnerQTY2.setValueFactory(valueFactory);
        SNACKERSspinnerQTY3.setValueFactory(valueFactory);
        SNACKERSspinnerQTY4.setValueFactory(valueFactory);
        SNACKERSspinnerQTY5.setValueFactory(valueFactory);
        DESSERTSspinnerQTY1.setValueFactory(valueFactory);
        DESSERTSspinnerQTY2.setValueFactory(valueFactory);
        DESSERTSspinnerQTY3.setValueFactory(valueFactory);
        DESSERTSspinnerQTY4.setValueFactory(valueFactory);
        DESSERTSspinnerQTY5.setValueFactory(valueFactory);
        placeorderpage();
    }

    public void placeorderpage() throws IOException {
        Parent load= FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/PlaceOrderForm.fxml")));
        placeOrderRoot.getChildren().clear();
        placeOrderRoot.getChildren().add(load);
    }

    public void btnDish3AddOnAction(ActionEvent actionEvent) {
        // Get the values from the spinner, labels, etc.
        int qty = DISHspinnerQTY3.getValue();
        String itemName = lblDISHESItemName3.getText();
        double unitPrice = Double.parseDouble(lblDISHESItemPrice3.getText());
        String itemType = lblItemCategory1.getText();

        // Calculate the total
        double total = qty * unitPrice;



        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        DISHspinnerQTY3.getValueFactory().setValue(1);
    }

    public void btnDish4AddOnAction(ActionEvent actionEvent) {
        // Get the values from the spinner, labels, etc.
        int qty = DISHspinnerQTY4.getValue();
        String itemName = lblDISHESItemName4.getText();
        double unitPrice = Double.parseDouble(lblDISHESItemPrice4.getText());

        // Calculate the total
        double total = qty * unitPrice;

        String itemType = lblItemCategory1.getText();

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        DISHspinnerQTY4.getValueFactory().setValue(1);
    }

    public void btnDish5AddOnAction(ActionEvent actionEvent) {
        // Get the values from the spinner, labels, etc.
        int qty = DISHspinnerQTY5.getValue();
        String itemName = lblDISHESItemName5.getText();
        double unitPrice = Double.parseDouble(lblDISHESItemPrice5.getText());

        // Calculate the total
        double total = qty * unitPrice;
        String itemType = lblItemCategory1.getText();
        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        DISHspinnerQTY5.getValueFactory().setValue(1);
    }

    public void btnSoups1AddOnAction(ActionEvent actionEvent) {
        // Get the values from the spinner, labels, etc.
        int qty =SOUPSspinnerQTY1.getValue();
        String itemName = lblSOUPSItemName1.getText();
        double unitPrice = Double.parseDouble(lblSOUPSItemPrice1.getText());

        // Calculate the total
        double total = qty * unitPrice;
        String itemType = lblItemCategory2.getText();
        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        SOUPSspinnerQTY1.getValueFactory().setValue(1);
    }

    public void btnSoups2AddOnAction(ActionEvent actionEvent) {
        int qty =SOUPSspinnerQTY2.getValue();
        String itemName = lblSOUPSItemName2.getText();
        double unitPrice = Double.parseDouble(lblSOUPSItemPrice2.getText());

        // Calculate the total
        double total = qty * unitPrice;
        String itemType = lblItemCategory2.getText();

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        SOUPSspinnerQTY2.getValueFactory().setValue(1);

    }

    public void btnSoups4AddOnAction(ActionEvent actionEvent) {
        int qty =SOUPSspinnerQTY4.getValue();
        String itemName = lblSOUPSItemName4.getText();
        double unitPrice = Double.parseDouble(lblSOUPSItemPrice4.getText());

        // Calculate the total
        double total = qty * unitPrice;

        String itemType = lblItemCategory2.getText();

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        SOUPSspinnerQTY4.getValueFactory().setValue(1);

    }

    public void btnSoups5AddOnAction(ActionEvent actionEvent) {
        int qty =SOUPSspinnerQTY5.getValue();
        String itemName = lblSOUPSItemName5.getText();
        double unitPrice = Double.parseDouble(lblSOUPSItemPrice5.getText());

        // Calculate the total
        double total = qty * unitPrice;
        String itemType = lblItemCategory2.getText();

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        SOUPSspinnerQTY5.getValueFactory().setValue(1);

    }

    public void btnSoups3AddOnAction(ActionEvent actionEvent) {
        int qty =SOUPSspinnerQTY3.getValue();
        String itemName = lblSOUPSItemName3.getText();
        double unitPrice = Double.parseDouble(lblSOUPSItemPrice3.getText());

        // Calculate the total
        double total = qty * unitPrice;
        String itemType = lblItemCategory2.getText();
        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        SOUPSspinnerQTY3.getValueFactory().setValue(1);

    }

    public void btnBardrinks1AddOnAction(ActionEvent actionEvent) {
        // Get the values from the spinner, labels, etc.
        int qty = BARDRINKSspinnerQTY1.getValue();
        String itemName = lblBARDRINKSItemName1.getText();
        double unitPrice = Double.parseDouble(lblBARDRINKSItemPrice1.getText());

        // Calculate the total
        double total = qty * unitPrice;
        String itemType = lblItemCategory3.getText();
        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        BARDRINKSspinnerQTY1.getValueFactory().setValue(1);
    }

    public void btnBardrinks2AddOnAction(ActionEvent actionEvent) {
        // Get the values from the spinner, labels, etc.
        int qty = BARDRINKSspinnerQTY2.getValue();
        String itemName = lblBARDRINKSItemName2.getText();
        double unitPrice = Double.parseDouble(lblBARDRINKSItemPrice2.getText());
        String itemType = lblItemCategory3.getText();

        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        BARDRINKSspinnerQTY2.getValueFactory().setValue(1);

    }

    public void btnBardrinks3AddOnAction(ActionEvent actionEvent) {
        // Get the values from the spinner, labels, etc.
        int qty = BARDRINKSspinnerQTY3.getValue();
        String itemName = lblBARDRINKSItemName3.getText();
        double unitPrice = Double.parseDouble(lblBARDRINKSItemPrice3.getText());
        String itemType = lblItemCategory3.getText();

        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        BARDRINKSspinnerQTY3.getValueFactory().setValue(1);

    }

    public void btnBardrinks5AddOnAction(ActionEvent actionEvent) {
        // Get the values from the spinner, labels, etc.
        int qty = BARDRINKSspinnerQTY5.getValue();
        String itemName = lblBARDRINKSItemName5.getText();
        double unitPrice = Double.parseDouble(lblBARDRINKSItemPrice5.getText());
        String itemType = lblItemCategory3.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        BARDRINKSspinnerQTY5.getValueFactory().setValue(1);

    }

    public void btnBardrinks4AddOnAction(ActionEvent actionEvent) {
        // Get the values from the spinner, labels, etc.
        int qty = BARDRINKSspinnerQTY4.getValue();
        String itemName = lblBARDRINKSItemName4.getText();
        double unitPrice = Double.parseDouble(lblBARDRINKSItemPrice4.getText());
        String itemType = lblItemCategory3.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        BARDRINKSspinnerQTY4.getValueFactory().setValue(1);

    }

    public void btnBeverages1AddOnAction(ActionEvent actionEvent) {
        int qty = BEVERAGESspinnerQTY1.getValue();
        String itemName = lblBEVERAGESItemName1.getText();
        double unitPrice = Double.parseDouble(lblBEVERAGESItemPrice1.getText());
        String itemType = lblItemCategory4.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        BEVERAGESspinnerQTY1.getValueFactory().setValue(1);
    }

    public void btnBeverages2AddOnAction(ActionEvent actionEvent) {
        int qty = BEVERAGESspinnerQTY2.getValue();
        String itemName = lblBEVERAGESItemName2.getText();
        double unitPrice = Double.parseDouble(lblBEVERAGESItemPrice2.getText());
        String itemType = lblItemCategory4.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        BEVERAGESspinnerQTY2.getValueFactory().setValue(1);
    }

    public void btnBeverages3AddOnAction(ActionEvent actionEvent) {
        int qty = BEVERAGESspinnerQTY3.getValue();
        String itemName = lblBEVERAGESItemName3.getText();
        double unitPrice = Double.parseDouble(lblBEVERAGESItemPrice3.getText());
        String itemType = lblItemCategory4.getText();

        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        BEVERAGESspinnerQTY3.getValueFactory().setValue(1);
    }

    public void btnBeverages5AddOnAction(ActionEvent actionEvent) {
        int qty = BEVERAGESspinnerQTY5.getValue();
        String itemName = lblBEVERAGESItemName5.getText();
        double unitPrice = Double.parseDouble(lblBEVERAGESItemPrice5.getText());
        String itemType = lblItemCategory4.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        BEVERAGESspinnerQTY5.getValueFactory().setValue(1);
    }

    public void btnBeverages4AddOnAction(ActionEvent actionEvent) {
        int qty = BEVERAGESspinnerQTY4.getValue();
        String itemName = lblBEVERAGESItemName4.getText();
        double unitPrice = Double.parseDouble(lblBEVERAGESItemPrice4.getText());
        String itemType = lblItemCategory4.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        BEVERAGESspinnerQTY4.getValueFactory().setValue(1);
    }

    public void btnSnackers1AddOnAction(ActionEvent actionEvent) {
        int qty = SNACKERSspinnerQTY1.getValue();
        String itemName = lblSNACKERSItemName1.getText();
        double unitPrice = Double.parseDouble(lblSNACKERSItemPrice1.getText());
        String itemType = lblItemCategory5.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        SNACKERSspinnerQTY1.getValueFactory().setValue(1);
    }

    public void btnSnackers2AddOnAction(ActionEvent actionEvent) {
        int qty = SNACKERSspinnerQTY2.getValue();
        String itemName = lblSNACKERSItemName2.getText();
        double unitPrice = Double.parseDouble(lblSNACKERSItemPrice2.getText());
        String itemType = lblItemCategory5.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        SNACKERSspinnerQTY2.getValueFactory().setValue(1);
    }

    public void btnSnackers5AddOnAction(ActionEvent actionEvent) {
        int qty = SNACKERSspinnerQTY5.getValue();
        String itemName = lblSNACKERSItemName5.getText();
        double unitPrice = Double.parseDouble(lblSNACKERSItemPrice5.getText());
        String itemType = lblItemCategory5.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        SNACKERSspinnerQTY5.getValueFactory().setValue(1);
    }

    public void btnSnackers3AddOnAction(ActionEvent actionEvent) {
        int qty = SNACKERSspinnerQTY3.getValue();
        String itemName = lblSNACKERSItemName3.getText();
        double unitPrice = Double.parseDouble(lblSNACKERSItemPrice3.getText());
        String itemType = lblItemCategory5.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        SNACKERSspinnerQTY3.getValueFactory().setValue(1);
    }

    public void btnSnackers4AddOnAction(ActionEvent actionEvent) {
        int qty = SNACKERSspinnerQTY4.getValue();
        String itemName = lblSNACKERSItemName4.getText();
        double unitPrice = Double.parseDouble(lblSNACKERSItemPrice4.getText());
        String itemType = lblItemCategory5.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        SNACKERSspinnerQTY4.getValueFactory().setValue(1);
    }

    public void btnDesserts1AddOnAction(ActionEvent actionEvent) {
        int qty = DESSERTSspinnerQTY1.getValue();
        String itemName = lblDESSERTSItemName1.getText();
        double unitPrice = Double.parseDouble(lblDESSERTSItemPrice1.getText());
        String itemType = lblItemCategory6.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        DESSERTSspinnerQTY1.getValueFactory().setValue(1);
    }

    public void btnDesserts4AddOnAction(ActionEvent actionEvent) {
        int qty = DESSERTSspinnerQTY4.getValue();
        String itemName = lblDESSERTSItemName4.getText();
        double unitPrice = Double.parseDouble(lblDESSERTSItemPrice4.getText());
        String itemType = lblItemCategory6.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        DESSERTSspinnerQTY4.getValueFactory().setValue(1);
    }

    public void btnDesserts2AddOnAction(ActionEvent actionEvent) {
        int qty = DESSERTSspinnerQTY2.getValue();
        String itemName = lblDESSERTSItemName2.getText();
        double unitPrice = Double.parseDouble(lblDESSERTSItemPrice2.getText());
        String itemType = lblItemCategory6.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        DESSERTSspinnerQTY2.getValueFactory().setValue(1);
    }

    public void btnDesserts5AddOnAction(ActionEvent actionEvent) {
        int qty = DESSERTSspinnerQTY5.getValue();
        String itemName = lblDESSERTSItemName5.getText();
        double unitPrice = Double.parseDouble(lblDESSERTSItemPrice5.getText());
        String itemType = lblItemCategory6.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        DESSERTSspinnerQTY5.getValueFactory().setValue(1);
    }

    public void btnDesserts3AddOnAction(ActionEvent actionEvent) {
        int qty = DESSERTSspinnerQTY3.getValue();
        String itemName = lblDESSERTSItemName3.getText();
        double unitPrice = Double.parseDouble(lblDESSERTSItemPrice3.getText());
        String itemType = lblItemCategory6.getText();
        // Calculate the total
        double total = qty * unitPrice;

        // Create a DTO with the data
        RestoRiseDTO restoRiseDTO = new RestoRiseDTO(itemName, unitPrice, qty, total,itemType);

        // Save to the database
        saveToDatabase(restoRiseDTO);

        // Clear the spinner value
        DESSERTSspinnerQTY3.getValueFactory().setValue(1);
    }

}

