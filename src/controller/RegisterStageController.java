package controller;

import javaclass.Database;
import javaclass.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterStageController {
    @FXML private AnchorPane registerPane;
    @FXML private TextField loginField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField passwordField;
    @FXML private TextField eMailField;
    @FXML private ComboBox dayCombo;
    @FXML private ComboBox monthCombo;
    @FXML private ComboBox yearCombo;
    @FXML private ComboBox genderCombo;
    @FXML private ComboBox countryCombo;
    @FXML private Label errorLabel;
    @FXML private Label loginLabel;
    @FXML private Label firstNameLabel;
    @FXML private Label lastNameLabel;
    @FXML private Label passwordLabel;
    @FXML private Label eMailLabel;
    @FXML private Label birthdayLabel;
    @FXML private Label genderLabel;
    @FXML private Label countryLabel;
    @FXML private Button cancelButton;
    @FXML private Button registerButton;

    @FXML
    private void initialize() {
        if (Main.lang == 0) {
            loginLabel.setText("Логін:");
            passwordLabel.setText("Пароль:");
            firstNameLabel.setText("Ім'я:");
            lastNameLabel.setText("Прізвище:");
            genderLabel.setText("Стать:");
            birthdayLabel.setText("Дата народження:");
            countryLabel.setText("Країна:");
            loginField.setPromptText("логін");
            passwordField.setPromptText("пароль");
            firstNameField.setPromptText("ім'я");
            lastNameField.setPromptText("прізвище");
            cancelButton.setText("Назад");
            registerButton.setText("Реєстрація");
            monthCombo.getItems().add("Січень");
            monthCombo.getItems().add("Лютий");
            monthCombo.getItems().add("Березень");
            monthCombo.getItems().add("Квітень");
            monthCombo.getItems().add("Травень");
            monthCombo.getItems().add("Червень");
            monthCombo.getItems().add("Липень");
            monthCombo.getItems().add("Серпень");
            monthCombo.getItems().add("Вересень");
            monthCombo.getItems().add("Жовтень");
            monthCombo.getItems().add("Листопад");
            monthCombo.getItems().add("Грудень");
            genderCombo.getItems().add("Чоловіча");
            genderCombo.getItems().add("Жіноча");
        }
        if (Main.lang == 1) {
            loginLabel.setText("Логин:");
            passwordLabel.setText("Пароль:");
            firstNameLabel.setText("Имя:");
            lastNameLabel.setText("Фамилия:");
            genderLabel.setText("Пол:");
            birthdayLabel.setText("Дата рождения:");
            countryLabel.setText("Страна:");
            loginField.setPromptText("логин");
            passwordField.setPromptText("пароль");
            firstNameField.setPromptText("имя");
            lastNameField.setPromptText("фамилия");
            cancelButton.setText("Назад");
            registerButton.setText("Регистрация");
            monthCombo.getItems().add("Январь");
            monthCombo.getItems().add("Февраль");
            monthCombo.getItems().add("Март");
            monthCombo.getItems().add("Апрель");
            monthCombo.getItems().add("Май");
            monthCombo.getItems().add("Июнь");
            monthCombo.getItems().add("Июль");
            monthCombo.getItems().add("Август");
            monthCombo.getItems().add("Сентябрь");
            monthCombo.getItems().add("Октябрь");
            monthCombo.getItems().add("Ноябрь");
            monthCombo.getItems().add("Декабрь");
            genderCombo.getItems().add("Мужской");
            genderCombo.getItems().add("Женский");
        }
        if (Main.lang == 2) {
            loginLabel.setText("Login:");
            passwordLabel.setText("Password:");
            firstNameLabel.setText("First name:");
            lastNameLabel.setText("Last name:");
            genderLabel.setText("Gender:");
            birthdayLabel.setText("Birthday:");
            countryLabel.setText("Country:");
            loginField.setPromptText("login");
            passwordField.setPromptText("password");
            firstNameField.setPromptText("first name");
            lastNameField.setPromptText("last name");
            cancelButton.setText("Cancel");
            registerButton.setText("Register");
            monthCombo.getItems().add("January");
            monthCombo.getItems().add("February");
            monthCombo.getItems().add("March");
            monthCombo.getItems().add("April");
            monthCombo.getItems().add("May");
            monthCombo.getItems().add("June");
            monthCombo.getItems().add("July");
            monthCombo.getItems().add("August");
            monthCombo.getItems().add("September");
            monthCombo.getItems().add("October");
            monthCombo.getItems().add("November");
            monthCombo.getItems().add("December");
            genderCombo.getItems().add("Male");
            genderCombo.getItems().add("Female");
        }
        eMailLabel.setText("E-Mail:");
        eMailField.setPromptText("e-mail");
        genderCombo.getSelectionModel().select(0);
        countryCombo.getSelectionModel().select(0);
        dayCombo.getSelectionModel().select(0);
        monthCombo.getSelectionModel().select(0);
        yearCombo.getSelectionModel().select(30);
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
        LoginStageController.registerStage.close();
    }

    @FXML
    private void monthSelectionAction(ActionEvent event) {
        if (monthCombo.getSelectionModel().getSelectedIndex() == 0) {
            if (dayCombo.getItems().size() == 28) {
                addDays(2831);
            }
            if (dayCombo.getItems().size() == 29) {
                addDays(2931);
            }
            if (dayCombo.getItems().size() == 30) {
                addDays(3031);
            }
        }
        if (monthCombo.getSelectionModel().getSelectedIndex() == 1) {
            if (yearCombo.getSelectionModel().getSelectedIndex()%4 == 2) {
                if (dayCombo.getItems().size() == 31) {
                    deleteDays(3129);
                }
                if (dayCombo.getItems().size() == 30) {
                    deleteDays(3029);
                }
                if (dayCombo.getItems().size() == 28) {
                    addDays(2829);
                }
            } else {
                if (dayCombo.getItems().size() == 31) {
                    deleteDays(3128);
                }
                if (dayCombo.getItems().size() == 30) {
                    deleteDays(3028);
                }
                if (dayCombo.getItems().size() == 29) {
                    deleteDays(2928);
                }
            }
        }
        if (monthCombo.getSelectionModel().getSelectedIndex() == 2) {
            if (dayCombo.getItems().size() == 28) {
                addDays(2831);
            }
            if (dayCombo.getItems().size() == 29) {
                addDays(2931);
            }
            if (dayCombo.getItems().size() == 30) {
                addDays(3031);
            }
        }
        if (monthCombo.getSelectionModel().getSelectedIndex() == 3) {
            if (dayCombo.getItems().size() == 28) {
                addDays(2830);
            }
            if (dayCombo.getItems().size() == 29) {
                addDays(2930);
            }
            if (dayCombo.getItems().size() == 31) {
                deleteDays(3130);
            }
        }
        if (monthCombo.getSelectionModel().getSelectedIndex() == 4) {
            if (dayCombo.getItems().size() == 28) {
                addDays(2831);
            }
            if (dayCombo.getItems().size() == 29) {
                addDays(2931);
            }
            if (dayCombo.getItems().size() == 30) {
                addDays(3031);
            }
        }
        if (monthCombo.getSelectionModel().getSelectedIndex() == 5) {
            if (dayCombo.getItems().size() == 28) {
                addDays(2830);
            }
            if (dayCombo.getItems().size() == 29) {
                addDays(2930);
            }
            if (dayCombo.getItems().size() == 31) {
                deleteDays(3130);
            }
        }
        if (monthCombo.getSelectionModel().getSelectedIndex() == 6) {
            if (dayCombo.getItems().size() == 28) {
                addDays(2831);
            }
            if (dayCombo.getItems().size() == 29) {
                addDays(2931);
            }
            if (dayCombo.getItems().size() == 30) {
                addDays(3031);
            }
        }
        if (monthCombo.getSelectionModel().getSelectedIndex() == 7) {
            if (dayCombo.getItems().size() == 28) {
                addDays(2831);
            }
            if (dayCombo.getItems().size() == 29) {
                addDays(2931);
            }
            if (dayCombo.getItems().size() == 30) {
                addDays(3031);
            }
        }
        if (monthCombo.getSelectionModel().getSelectedIndex() == 8) {
            if (dayCombo.getItems().size() == 28) {
                addDays(2830);
            }
            if (dayCombo.getItems().size() == 29) {
                addDays(2930);
            }
            if (dayCombo.getItems().size() == 31) {
                deleteDays(3130);
            }
        }
        if (monthCombo.getSelectionModel().getSelectedIndex() == 9) {
            if (dayCombo.getItems().size() == 28) {
                addDays(2831);
            }
            if (dayCombo.getItems().size() == 29) {
                addDays(2931);
            }
            if (dayCombo.getItems().size() == 30) {
                addDays(3031);
            }
        }
        if (monthCombo.getSelectionModel().getSelectedIndex() == 10) {
            if (dayCombo.getItems().size() == 28) {
                addDays(2830);
            }
            if (dayCombo.getItems().size() == 29) {
                addDays(2930);
            }
            if (dayCombo.getItems().size() == 31) {
                deleteDays(3130);
            }
        }
        if (monthCombo.getSelectionModel().getSelectedIndex() == 11) {
            if (dayCombo.getItems().size() == 28) {
                addDays(2831);
            }
            if (dayCombo.getItems().size() == 29) {
                addDays(2931);
            }
            if (dayCombo.getItems().size() == 30) {
                addDays(3031);
            }
        }
    }

    @FXML
    private void yearSelectionAction(ActionEvent event) {
        if (yearCombo.getSelectionModel().getSelectedIndex()%4 == 2) {
            if (monthCombo.getSelectionModel().getSelectedIndex() == 1) {
                if (dayCombo.getItems().size() == 28) {
                    addDays(2829);
                }
            }
        }

    }

    @FXML
    private void registerButtonAction(ActionEvent event) {
        int err = 0;
        boolean error = false;

        if (loginField.getText().equals("")) {
            if (Main.lang == 0)
                error = writeError("Введіть логін.");
            if (Main.lang == 1)
                error = writeError("Введите логин.");
            if (Main.lang == 2)
                error = writeError("Write a login.");
        }

        if ((firstNameField.getText().equals("")) && (!error)) {
            if (Main.lang == 0)
                error = writeError("Введіть ім'я.");
            if (Main.lang == 1)
                error = writeError("Введите имя.");
            if (Main.lang == 2)
                error = writeError("Write a first name.");
        }

        if ((lastNameField.getText().equals("")) && (!error)) {
            if (Main.lang == 0)
                error = writeError("Введіть прізвище.");
            if (Main.lang == 1)
                error = writeError("Введите фамилию.");
            if (Main.lang == 2)
                error = writeError("Write a last name.");
        }

        if ((passwordField.getText().equals("")) && (!error)) {
            if (Main.lang == 0)
                error = writeError("Введіть пароль.");
            if (Main.lang == 1)
                error = writeError("Введите пароль.");
            if (Main.lang == 2)
                error = writeError("Write a password.");
        }

        if ((eMailField.getText().equals("")) && (!error)) {
            if (Main.lang == 0)
                error = writeError("Введіть e-Mail.");
            if (Main.lang == 1)
                error = writeError("Введите e-Mail.");
            if (Main.lang == 2)
                error = writeError("Write an e-Mail.");
        }

        if (!error) {
            int gender = 0;
            String month = "01";
            if (genderCombo.getSelectionModel().isSelected(0))
                gender = 0;
            if (genderCombo.getSelectionModel().isSelected(1))
                gender = 1;

            if (monthCombo.getSelectionModel().isSelected(0))
                month = "01";
            if (monthCombo.getSelectionModel().isSelected(1))
                month = "02";
            if (monthCombo.getSelectionModel().isSelected(2))
                month = "03";
            if (monthCombo.getSelectionModel().isSelected(3))
                month = "04";
            if (monthCombo.getSelectionModel().isSelected(4))
                month = "05";
            if (monthCombo.getSelectionModel().isSelected(5))
                month = "06";
            if (monthCombo.getSelectionModel().isSelected(6))
                month = "07";
            if (monthCombo.getSelectionModel().isSelected(7))
                month = "08";
            if (monthCombo.getSelectionModel().isSelected(8))
                month = "09";
            if (monthCombo.getSelectionModel().isSelected(9))
                month = "10";
            if (monthCombo.getSelectionModel().isSelected(10))
                month = "11";
            if (monthCombo.getSelectionModel().isSelected(11))
                month = "12";

            //err = Database.addUser(loginField.getText(), firstNameField.getText(), lastNameField.getText(), passwordField.getText(), eMailField.getText(), dayCombo.getValue().toString(), month, yearCombo.getValue().toString(), gender, countryCombo.getValue().toString());
            if (err == -1) {
                if (Main.lang == 0)
                    error = writeError("Помилка. Невірні дані.");
                if (Main.lang == 1)
                    error = writeError("Ошибка. Неверные данные.");
                if (Main.lang == 2)
                    error = writeError("Error. Writeen data is incorrect.");
            }
            if (err == 0) {
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
                LoginStageController.registerStage.close();
                if (Main.lang == 0)
                    JOptionPane.showMessageDialog(null, "Реєстрація завершена.", "Реєстрація", JOptionPane.INFORMATION_MESSAGE);
                if (Main.lang == 1)
                    JOptionPane.showMessageDialog(null, "Регистрация завершена.", "Регистрация", JOptionPane.INFORMATION_MESSAGE);
                if (Main.lang == 2)
                    JOptionPane.showMessageDialog(null, "Registration successful.", "Registration", JOptionPane.INFORMATION_MESSAGE);

            }
            if (err == 1) {
                if (Main.lang == 0)
                    error = writeError("Такий логін вже використовується.");
                if (Main.lang == 1)
                    error = writeError("Такой логин уже используется.");
                if (Main.lang == 2)
                    error = writeError("Such login has already been used.");
            }
            if (err == 2) {
                if (Main.lang == 0)
                    error = writeError("Такий e-Mail вже використовується.");
                if (Main.lang == 1)
                    error = writeError("Такой e-Mail уже используется.");
                if (Main.lang == 2)
                    error = writeError("Such e-Mail has already been used.");
            }
        }
    }

    public boolean writeError(String text) {
        errorLabel.setText(text);
        errorLabel.setVisible(true);
        errorLabel.setTextFill(Color.RED);
        return true;
    }

    public void addDays(int number) {
        if (number == 2829) {
            dayCombo.getItems().add(28, "29"); // add 29
        }
        if (number == 2930) {
            dayCombo.getItems().add(29, "30"); // add 30
        }
        if (number == 2830) {
            dayCombo.getItems().add(28, "29"); // add 29
            dayCombo.getItems().add(29, "30"); // add 30
        }
        if (number == 2831) {
            dayCombo.getItems().add(28, "29"); // add 29
            dayCombo.getItems().add(29, "30"); // add 30
            dayCombo.getItems().add(30, "31"); // add 31
        }
        if (number == 2931) {
            dayCombo.getItems().add(29, "30"); // add 30
            dayCombo.getItems().add(30, "31"); // add 31
        }
        if (number == 3031) {
            dayCombo.getItems().add(30, "31"); // add 31
        }
    }

    public void deleteDays(int number) {
        if (number == 2928) {
            dayCombo.getItems().remove(28); // delete 29
        }
        if (number == 3029) {
            dayCombo.getItems().remove(29); // delete 30
        }
        if (number == 3028) {
            dayCombo.getItems().remove(29); // delete 30
            dayCombo.getItems().remove(28); // delete 29
        }
        if (number == 3128) {
            dayCombo.getItems().remove(30); // delete 31
            dayCombo.getItems().remove(29); // delete 30
            dayCombo.getItems().remove(28); // delete 29
        }
        if (number == 3129) {
            dayCombo.getItems().remove(30); // delete 31
            dayCombo.getItems().remove(29); // delete 30
        }
        if (number == 3130) {
            dayCombo.getItems().remove(30); // delete 31
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
    private void firstTypedAction(KeyEvent event) {
        if (firstNameField.getLength() > 30) {
            firstNameField.setText(firstNameField.getText(0, 30));
            if (Main.lang == 0)
                writeError("Ім'я не може містити більше ніж 30 символів.");
            if (Main.lang == 1)
                writeError("Имя не может содержать больше чем 30 символов.");
            if (Main.lang == 2)
                writeError("First name can't be larger than 30 symbols.");
        }
    }

    @FXML
    private void lastTypedAction(KeyEvent event) {
        if (lastNameField.getLength() > 30) {
            lastNameField.setText(lastNameField.getText(0, 30));
            if (Main.lang == 0)
                writeError("Прізвище не може містити більше ніж 30 символів.");
            if (Main.lang == 1)
                writeError("Фамилия не может содержать больше чем 30 символов.");
            if (Main.lang == 2)
                writeError("Last name can't be larger than 30 symbols.");
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

    @FXML
    private void eMailTypedAction(KeyEvent event) {
        if (eMailField.getLength() > 30) {
            eMailField.setText(eMailField.getText(0, 30));
            if (Main.lang == 0)
                writeError("E-Mail не може містити більше ніж 30 символів.");
            if (Main.lang == 1)
                writeError("E-Mail не может содержать больше чем 30 символов.");
            if (Main.lang == 2)
                writeError("E-Mail can't be larger than 30 symbols.");
        }
    }
}
