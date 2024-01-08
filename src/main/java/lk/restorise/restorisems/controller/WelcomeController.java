package lk.restorise.restorisems.controller;

import animatefx.animation.FadeInDown;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.time.LocalDate;

import java.io.IOException;
import java.util.Objects;

public class WelcomeController {
    private AnchorPane root;

    @FXML
    private JFXButton btnswipe;

    @FXML
    private AnchorPane welcomepane;

    @FXML
    public Label labelDate;

    public void initialize(){
        String date = String.valueOf(LocalDate.now());
        labelDate.setText(date);
    }

    @FXML
    void btnswipeonaction(ActionEvent event) throws IOException {


      AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginForm.fxml")));
      Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)welcomepane.getScene().getWindow();

        stage.setScene(scene);


        stage.setTitle("Login Form");
        stage.centerOnScreen();


    }

}
