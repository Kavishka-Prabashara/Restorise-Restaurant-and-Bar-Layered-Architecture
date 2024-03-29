package lk.restorise.restorisems;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AppInitializer extends Application {
    public static void main(String[] args){ launch(args);}

@Override
public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/WelcomeForm.fxml"))));
        stage.centerOnScreen();
        stage.setTitle("Welcome Form");
        stage.fullScreenProperty();
        stage.setResizable(false);

        stage.show();
}}


