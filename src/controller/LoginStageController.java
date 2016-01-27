package controller;

import javaclass.Database;
import javaclass.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;
import java.util.Scanner;

import static javaclass.Main.loginStage;

public class LoginStageController {
    @FXML public static Stage connectStage;
    @FXML public static Stage registerStage;
    @FXML public static Stage mainStage;

    @FXML private AnchorPane loginPane;
    @FXML private TextField loginField;
    @FXML private TextField passwordField;
    @FXML private Label errorLabel;
    @FXML private Label loginLabel;
    @FXML private Label passwordLabel;
    @FXML private Button connectButton;
    @FXML private Button registerButton;
    @FXML private Button loginButton;
    @FXML private Button exitButton;

    public static int userId;

    @FXML
    private void initialize() {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("temp.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(fis);
        int i = 0;
        while(sc.hasNext())
        {
            if (i==0)  {
                loginField.setText(sc.nextLine());
            }
            if (i==1) {
                passwordField.setText(sc.nextLine());
            }
            i++;
        }

        if (Main.lang == 0) {
            loginLabel.setText("Логін:");
            passwordLabel.setText("Пароль:");
            loginField.setPromptText("логін");
            passwordField.setPromptText("пароль");
            connectButton.setText("Підключення");
            registerButton.setText("Реєстрація");
            loginButton.setText("Вхід");
            exitButton.setText("Вихід");
        }
        if (Main.lang == 1) {
            loginLabel.setText("Логин:");
            passwordLabel.setText("Пароль:");
            loginField.setPromptText("логин");
            passwordField.setPromptText("пароль");
            connectButton.setText("Подключение");
            registerButton.setText("Регистрация");
            loginButton.setText("Вход");
            exitButton.setText("Выход");
        }
        if (Main.lang == 2) {
            loginLabel.setText("Login:");
            passwordLabel.setText("Password:");
            loginField.setPromptText("login");
            passwordField.setPromptText("password");
            connectButton.setText("Connect");
            registerButton.setText("Register");
            loginButton.setText("Login");
            exitButton.setText("Exit");
        }
    }

    @FXML
    private void exitButtonAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void connectButtonAction(ActionEvent event) {
        PrintWriter zzz = null;
        try
        {
            zzz = new PrintWriter(new FileOutputStream("temp.txt"));
        }
        catch(FileNotFoundException e)
        {
        }
        zzz.println(loginField.getText());
        zzz.println(passwordField.getText());
        zzz.close();
        Stage connectStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/ConnectFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Main.lang == 0)
            connectStage.setTitle("Підключення до бази даних");
        if (Main.lang == 1)
            connectStage.setTitle("Подключение к базе данных");
        if (Main.lang == 2)
            connectStage.setTitle("Connect to database");
        connectStage.setScene(new Scene(root, 280, 206.5));
        connectStage.show();
        connectStage.setResizable(false);
        this.connectStage = connectStage;
        errorLabel.setVisible(false);
        loginPane.setDisable(true);   /*  Отключить панель (кнопка закрыть свободна)  */
    }

    @FXML
    private void registerButtonAction(ActionEvent event) {
        if (Database.connect()) {
            PrintWriter zzz = null;
            try
            {
                zzz = new PrintWriter(new FileOutputStream("temp.txt"));
            }
            catch(FileNotFoundException e)
            {
            }
            zzz.println(loginField.getText());
            zzz.println(passwordField.getText());
            zzz.close();
            Stage registerStage = new Stage();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/fxml/RegisterFXML.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (Main.lang == 0)
                registerStage.setTitle("Реєстрація нового користувача");
            if (Main.lang == 1)
                registerStage.setTitle("Регистрация нового пользователя");
            if (Main.lang == 2)
                registerStage.setTitle("Register new account");
            registerStage.setScene(new Scene(root, 285.0, 266.0));
            registerStage.show();
            registerStage.setResizable(false);
            this.registerStage = registerStage;
            errorLabel.setVisible(false);
            loginPane.setDisable(true);

        } else {
            if (Main.lang == 0)
                writeError("Необхідно під'єднатись до бази даних.");
            if (Main.lang == 1)
                writeError("Необходимо подключиться к базе даных.");
            if (Main.lang == 2)
                writeError("You have to connect to database firstly.");
        }

    }

    @FXML
    private void loginButtonAction(ActionEvent event) {
        boolean error = false;
        if (Database.connect()) {
            if (loginField.getText().equals("")) {
                if (Main.lang == 0)
                    error = writeError("Введіть логін.");
                if (Main.lang == 1)
                    error = writeError("Введите логин.");
                if (Main.lang == 2)
                    error = writeError("Write a login.");
            }

            if ((passwordField.getText().equals("")) && (!error)) {
                if (Main.lang == 0)
                    error = writeError("Введіть пароль.");
                if (Main.lang == 1)
                    error = writeError("Введите пароль.");
                if (Main.lang == 2)
                    error = writeError("Write a password.");
            }

            if ((!Database.isUserInDataBaseByLogin(loginField.getText())) && (!error)) {
                if (Main.lang == 0)
                    error = writeError("Невірний логін чи пароль.");
                if (Main.lang == 1)
                    error = writeError("Неверный логин или пароль.");
                if (Main.lang == 2)
                    error = writeError("Invalid login or password.");
            }

            if ((!Database.isUserInDataBaseByPassword(loginField.getText(), passwordField.getText())) && (!error)) {
                if (Main.lang == 0)
                    error = writeError("Невірний логін чи пароль.");
                if (Main.lang == 1)
                    error = writeError("Неверный логин или пароль.");
                if (Main.lang == 2)
                    error = writeError("Invalid login or password.");
            }

            if (!error) {
                userId = Database.getUserIdByLoginAndPassword(loginField.getText(), passwordField.getText());
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
                this.mainStage = mainStage;
                loginStage.close();
            }
        } else {
            if (Main.lang == 0)
                writeError("Необхідно під'єднатись до бази даних.");
            if (Main.lang == 1)
                writeError("Необходимо подключиться к базе даных.");
            if (Main.lang == 2)
                writeError("You have to connect to database firstly.");
        }
    }

    @FXML
    private void loginTypedAction(KeyEvent event) {
        if (loginField.getLength() > 16) {
            loginField.setText(loginField.getText(0, 16));
            if (Main.lang == 0)
                writeError("Логін не може містити більше ніж 16 символів.");
            if (Main.lang == 1)
                writeError("Логин не может содержать больше чем 16 символов.");
            if (Main.lang == 2)
                writeError("Login can't be larger than 16 symbols.");
        }
    }

    @FXML
    private void passwordTypedAction(KeyEvent event) {
        if (passwordField.getLength() > 16) {
            passwordField.setText(passwordField.getText(0, 16));
            if (Main.lang == 0)
                writeError("Пароль не може містити більше ніж 16 символів.");
            if (Main.lang == 1)
                writeError("Пароль не может содержать больше чем 16 символов.");
            if (Main.lang == 2)
                writeError("Password can't be larger than 16 symbols.");
        }
    }

    public boolean writeError(String text) {
        errorLabel.setText(text);
        errorLabel.setVisible(true);
        errorLabel.setTextFill(Color.RED);
        return true;
    }

}