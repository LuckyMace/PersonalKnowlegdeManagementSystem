package controller;


import javaclass.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SettingsStageController {

    @FXML private Label languageLabel;
    @FXML private ComboBox languageCombo;
    @FXML private Button applyButton;

    @FXML
    private void initialize() {
        if (Main.lang == 0) {
            languageLabel.setText("Мова:");
            languageCombo.getSelectionModel().select(0);
            applyButton.setText("Примінити");
        }
        if (Main.lang == 1) {
            languageLabel.setText("Язык:");
            languageCombo.getSelectionModel().select(1);
            applyButton.setText("Применить");
        }
        if (Main.lang == 2) {
            languageLabel.setText("Language:");
            languageCombo.getSelectionModel().select(2);
            applyButton.setText("Apply");
        }
    }

    @FXML
    private void applyAction(ActionEvent event) {
        if (languageCombo.getSelectionModel().isSelected(0))
            Main.lang = 0;
        if (languageCombo.getSelectionModel().isSelected(1))
            Main.lang = 1;
        if (languageCombo.getSelectionModel().isSelected(2))
            Main.lang = 2;
        LoginStageController.mainStage.close();
        Stage mainStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/MainFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainStage.setTitle("Personal Ideas Note");
        mainStage.setScene(new Scene(root, 909.0, 592.0));
        mainStage.show();
        mainStage.setResizable(false);
        LoginStageController.mainStage = mainStage;
        MainStageController.showSettingsStage.close();
    }
}
