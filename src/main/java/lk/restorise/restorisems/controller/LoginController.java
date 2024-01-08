package lk.restorise.restorisems.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.restorise.restorisems.model.LoginModel;
import lombok.SneakyThrows;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

public class LoginController {
        private AnchorPane root;

        @FXML
        private Hyperlink btnhypsignup;

        @FXML
        private JFXButton btnlogin;

        @FXML
        private AnchorPane signuppane;

        @FXML
        private PasswordField txtpassword;

        @FXML
        private TextField txtusername;

        @SneakyThrows
        @FXML
        void hypsnuponaction(ActionEvent event) {
                AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/SignUpForm.fxml")));
                Scene scene = new Scene(anchorPane);

                Stage stage = (Stage)signuppane.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Login Form");
                stage.centerOnScreen();

        }

        @FXML
        void loginonaction(ActionEvent event) {
                String username = txtusername.getText();
                String password = txtpassword.getText();

                try {
                        boolean isValidUser = LoginModel.validateUser(username, password);

                        if (isValidUser) {

                                loginsuccessafter();

                        } else {
                                new Alert(Alert.AlertType.ERROR, "Invalid username or password!").show();
                        }
                } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                } catch (IOException e) {
                        throw new RuntimeException(e);
                }
        }


        void loginsuccessafter() throws IOException {
                AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/DashboardMenuForm.fxml")));
                Scene scene = new Scene(anchorPane);

                Stage stage = (Stage)signuppane.getScene().getWindow();

                stage.setScene(scene);
                stage.setTitle("Login Form");
                stage.centerOnScreen();


        }

    public void btnuserOnAction(ActionEvent actionEvent) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/UserDetailsChangeForm.fxml")));
            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage)signuppane.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Login Form");
            stage.centerOnScreen();

    }
}

