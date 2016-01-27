package controller;

import javaclass.Database;
import javaclass.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class ConnectStageController {
    @FXML private AnchorPane connectPane;
    @FXML private TextField hostnameField;
    @FXML private TextField portField;
    @FXML private TextField usernameField;
    @FXML private TextField passwordField;
    @FXML private TextField sidField;
    @FXML private Label errorLabel;

    @FXML
    private void initialize() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("DataBaseSettings.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(fis);
        int i = 0;
        while(sc.hasNext())
        {
            if (i==0)  {
                hostnameField.setText(sc.nextLine());
            }
            if (i==1) {
                portField.setText(sc.nextLine());
            }
            if (i==2) {
                usernameField.setText(sc.nextLine());
            }
            if (i==3) {
                passwordField.setText(sc.nextLine());
            }
            if (i==4) {
                sidField.setText(sc.nextLine());
            }
            i++;
        }
    }

    @FXML
    private void cancelButtonAction(ActionEvent event) {
        Main.loginStage.close();
        Stage loginStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/LoginFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Main.lang == 0)
            loginStage.setTitle("Вхід");
        if (Main.lang == 1)
            loginStage.setTitle("Вход");
        if (Main.lang == 2)
            loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root, 305, 185));
        loginStage.show();
        loginStage.setResizable(false);
        Main.loginStage = loginStage;
        LoginStageController.connectStage.close();
    }

    @FXML
    private void connectButtonAction(ActionEvent event) {
        boolean error = false;

        if (hostnameField.getText().equals("")) {
            if (Main.lang == 0)
                error = writeError("Введіть host name.");
            if (Main.lang == 1)
                error = writeError("Введите host name.");
            if (Main.lang == 2)
                error = writeError("Write a host name.");

        }

        if (portField.getText().equals("")) {
            if (Main.lang == 0)
                error = writeError("Введіть port.");
            if (Main.lang == 1)
                error = writeError("Введите port.");
            if (Main.lang == 2)
                error = writeError("Write a port.");
        }

        if (usernameField.getText().equals("")) {
            if (Main.lang == 0)
                error = writeError("Введіть user name.");
            if (Main.lang == 1)
                error = writeError("Введите user name.");
            if (Main.lang == 2)
                error = writeError("Write a user name.");
        }

        if (passwordField.getText().equals("")) {
            if (Main.lang == 0)
                error = writeError("Введіть password.");
            if (Main.lang == 1)
                error = writeError("Введите password.");
            if (Main.lang == 2)
                error = writeError("Write a password.");
        }

        if (sidField.getText().equals("")) {
            if (Main.lang == 0)
                error = writeError("Введіть SID.");
            if (Main.lang == 1)
                error = writeError("Введите SID.");
            if (Main.lang == 2)
                error = writeError("Write a SID.");
        }

        if (!error) {
            Database.setDataBaseSettings(hostnameField.getText(), portField.getText(), usernameField.getText(), passwordField.getText(), sidField.getText());
            if (Database.connect()) {
                PrintWriter zzz = null;
                try
                {
                    zzz = new PrintWriter(new FileOutputStream("DataBaseSettings.txt"));
                }
                catch(FileNotFoundException e)
                {
                }
                zzz.println(hostnameField.getText());
                zzz.println(portField.getText());
                zzz.println(usernameField.getText());
                zzz.println(passwordField.getText());
                zzz.println(sidField.getText());
                zzz.close();
                Main.loginStage.close();
                Stage loginStage = new Stage();
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/fxml/LoginFXML.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Main.lang == 0)
                    loginStage.setTitle("Вхід");
                if (Main.lang == 1)
                    loginStage.setTitle("Вход");
                if (Main.lang == 2)
                    loginStage.setTitle("Login");
                loginStage.setScene(new Scene(root, 305, 185));
                loginStage.show();
                loginStage.setResizable(false);
                Main.loginStage = loginStage;
                LoginStageController.connectStage.close();
                JOptionPane.showMessageDialog(null, "Connection successful", "Connection", JOptionPane.INFORMATION_MESSAGE);
            } else {
                if (Main.lang == 0)
                    error = writeError("Помилка. Неможливо підключитися до бази даних.");
                if (Main.lang == 1)
                    error = writeError("Ошибка. Невозможно подключится к базе данных.");
                if (Main.lang == 2)
                    error = writeError("Error. Could not connect to database.");
            }
        }
    }

    @FXML
    private void hostnameTypedAction(KeyEvent event) {
        if (hostnameField.getLength() > 20) {
            hostnameField.setText(hostnameField.getText(0, 20));
            if (Main.lang == 0)
                writeError("Host name не може містити більше ніж 20 символів.");
            if (Main.lang == 1)
                writeError("Host name не может содержать больше чем 20 символов.");
            if (Main.lang == 2)
                writeError("Host name can't be larger than 20 symbols.");
        }
    }

    @FXML
    private void portTypedAction(KeyEvent event) {
        if (portField.getLength() > 20) {
            portField.setText(portField.getText(0, 20));
            if (Main.lang == 0)
                writeError("Port не може містити більше ніж 20 символів.");
            if (Main.lang == 1)
                writeError("Port не может содержать больше чем 20 символов.");
            if (Main.lang == 2)
                writeError("Port can't be larger than 20 symbols.");
        }
    }

    @FXML
    private void usernameTypedAction(KeyEvent event) {
        if (usernameField.getLength() > 20) {
            usernameField.setText(usernameField.getText(0, 20));
            if (Main.lang == 0)
                writeError("User name не може містити більше ніж 20 символів.");
            if (Main.lang == 1)
                writeError("User name не может содержать больше чем 20 символов.");
            if (Main.lang == 2)
                writeError("User name can't be larger than 20 symbols.");
        }
    }

    @FXML
    private void passwordTypedAction(KeyEvent event) {
        if (passwordField.getLength() > 20) {
            passwordField.setText(passwordField.getText(0, 20));
            if (Main.lang == 0)
                writeError("Password не може містити більше ніж 20 символів.");
            if (Main.lang == 1)
                writeError("Password не может содержать больше чем 20 символов.");
            if (Main.lang == 2)
                writeError("Password can't be larger than 20 symbols.");
        }
    }

    @FXML
    private void sidTypedAction(KeyEvent event) {
        if (sidField.getLength() > 10) {
            sidField.setText(sidField.getText(0, 10));
            if (Main.lang == 0)
                writeError("SID не може містити більше ніж 10 символів.");
            if (Main.lang == 1)
                writeError("SID не может содержать больше чем 10 символов.");
            if (Main.lang == 2)
                writeError("SID can't be larger than 10 symbols.");
        }
    }

    public boolean writeError(String text) {
        errorLabel.setText(text);
        errorLabel.setVisible(true);
        errorLabel.setTextFill(Color.RED);
        return true;
    }
}
