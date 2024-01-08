package lk.restorise.restorisems.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.restorise.restorisems.dto.UserDTO;
import lk.restorise.restorisems.model.Usermodel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class SignUpController {
    private AnchorPane root;

    @FXML
    private JFXButton btnsignup;



    @FXML
    private Hyperlink hyplogin;

    @FXML
    private AnchorPane signuppane;
    @FXML
    private TextField txtemail;
    @FXML
    private TextField txtusername;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private ComboBox<?> cmbQuestion;
    @FXML
    private TextField txtanswer;






    @FXML
    void hyploginonaction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginForm.fxml")));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)signuppane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();

    }

    @FXML
    void signuponaction(ActionEvent event) {
        String email = txtemail.getText();
        String name = txtusername.getText();
        String password = txtpassword.getText();
        String select= String.valueOf(cmbQuestion.getValue());
        String answer = txtanswer.getText();



        var dto = new UserDTO(email,name,password,select,answer);

        try {
            boolean isSaved = Usermodel.saveUser(dto);

            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "User is saved!").show();
                signupafter();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void clearFields() {
        txtemail.setText("");
        txtusername.setText("");
        txtpassword.setText("");
        cmbQuestion.getValue();
        txtanswer.setText("");


    }
    void signupafter() throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/LoginForm.fxml")));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)signuppane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

}
