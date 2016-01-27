package javaclass;

import controller.ConnectStageController;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @FXML public static Stage loginStage;

    public static int lang = 0;

    @Override
    public void start(Stage loginStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/LoginFXML.fxml"));
        loginStage.setTitle("Вхід");
        loginStage.setScene(new Scene(root, 305, 185));
        loginStage.show();
        loginStage.setResizable(false);
        this.loginStage = loginStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}