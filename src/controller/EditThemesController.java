package controller;

import javaclass.Database;
import javaclass.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.*;
import java.net.MalformedURLException;
import java.nio.channels.FileChannel;

public class EditThemesController {
    @FXML private Label errorLabel;
    @FXML private Label errorLexemLabel;
    @FXML private Label themesLabel;
    @FXML private Label lexemsLabel;
    @FXML private Label themeNameLabel;
    @FXML private Label iconLabel;
    @FXML private Label lexemNameLabel;
    @FXML private ListView<String> themesList;
    @FXML private ListView<String> lexemsList;
    @FXML private TextField themeNameField;
    @FXML private TextField lexemNameField;
    @FXML private ImageView iconImage;
    @FXML private Button deleteButton;
    @FXML private Button backButton;
    @FXML private Button browseButton;
    @FXML private Button addButton;
    @FXML private Button deleteLexemButton;
    @FXML private Button addLexemButton;
    @FXML private TitledPane newLexemTitledPane;
    @FXML private TitledPane newThemeTitledPane;

    public static String fileAddress = null;
    public static boolean selectedFile = false;
    public static boolean themeSelected = false;

    @FXML
    private void initialize() {
        refreshList(true, false);
        if (Main.lang == 0) {
            themesLabel.setText("Теми:");
            lexemsLabel.setText("Лексеми:");
            themeNameLabel.setText("Назва теми:");
            lexemNameLabel.setText("Назва лексеми:");
            iconLabel.setText("Встановити іконку теми:");
            themeNameField.setPromptText("назва");
            lexemNameField.setPromptText("лексема");
            deleteButton.setText("Видалити");
            backButton.setText("Назад");
            browseButton.setText("Відкрити");
            addButton.setText("Додати");
            deleteLexemButton.setText("Видалити");
            addLexemButton.setText("Додати");
            newThemeTitledPane.setText("Додати нову тему");
            newLexemTitledPane.setText("Додати нову лексему");
        }
        if (Main.lang == 1) {
            themesLabel.setText("Темы:");
            lexemsLabel.setText("Лексемы:");
            themeNameLabel.setText("Название темы:");
            lexemNameLabel.setText("Название лексемы:");
            iconLabel.setText("Выбрать иконку темы:");
            themeNameField.setPromptText("название");
            lexemNameField.setPromptText("лексема");
            deleteButton.setText("Удалить");
            backButton.setText("Назад");
            browseButton.setText("Открыть");
            addButton.setText("Добавить");
            deleteLexemButton.setText("Удалить");
            addLexemButton.setText("Добавить");
            newThemeTitledPane.setText("Добавить новую тему");
            newLexemTitledPane.setText("Добавить новую лексему");
        }
        if (Main.lang == 2) {
            themesLabel.setText("Themes:");
            lexemsLabel.setText("Lexems:");
            themeNameLabel.setText("New theme name:");
            lexemNameLabel.setText("New lexem name:");
            iconLabel.setText("Set theme icon:");
            themeNameField.setPromptText("name");
            lexemNameField.setPromptText("lexem");
            deleteButton.setText("Delete");
            backButton.setText("Back");
            browseButton.setText("Browse");
            addButton.setText("Add");
            deleteLexemButton.setText("Delete");
            addLexemButton.setText("Add");
            newThemeTitledPane.setText("Add new theme");
            newLexemTitledPane.setText("Add new lexem");
        }
        deleteButton.setDisable(true);
        deleteLexemButton.setDisable(true);
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        int n = 0;
        if (Main.lang == 0) {
            Object[] options = {"Так",
                    "Ні"};
            n = JOptionPane.showOptionDialog(null,
                    "Усі документи та лексеми обраної теми будуть видалені. Продовжити?",
                    "Підтвердження видалення",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]); //default button title

        }
        if (Main.lang == 1) {
            Object[] options = {"Да",
                    "Нет"};
            n = JOptionPane.showOptionDialog(null,
                    "Все документы и лексемы выбранной темы будут удалены. Продолжить?",
                    "Подтверждение удаления",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]); //default button title
        }
        if (Main.lang == 2) {
            Object[] options = {"Yes",
                    "No"};
            n = JOptionPane.showOptionDialog(null,
                    "All the documents and lexems will be deleted with the theme. Continue?",
                    "Confirm delete",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,     //do not use a custom Icon
                    options,  //the titles of buttons
                    options[0]); //default button title
        }
        if (n == 0) {
            Database.deleteAllDocumentByThemeId(Database.getThemeIdByNameAndUserId(themesList.getSelectionModel().getSelectedItem(), LoginStageController.userId));
            Database.deleteAllLexemByThemeId(Database.getThemeIdByNameAndUserId(themesList.getSelectionModel().getSelectedItem(), LoginStageController.userId));
            Database.deleteThemeByNameAndUserId(themesList.getSelectionModel().getSelectedItem(), LoginStageController.userId);
            deleteButton.setDisable(true);
            deleteLexemButton.setDisable(true);
            refreshList(true, false);
        }
    }

    @FXML
    private void deleteLexemAction(ActionEvent event) {
        Database.deleteLexemByNameAndThemeId(lexemsList.getSelectionModel().getSelectedItem(), Database.getThemeIdByNameAndUserId(themesList.getSelectionModel().getSelectedItem(), LoginStageController.userId));
        refreshList(false, true);
    }

    @FXML
    private void browseAction(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        if (Main.lang == 0) {
            chooser.setTitle("Відкрити файл");
        }
        if (Main.lang == 1) {
            chooser.setTitle("Открыть файл");
        }
        if (Main.lang == 2) {
            chooser.setTitle("Open File");
        }
        File file = chooser.showOpenDialog(new Stage());
        if(file != null) {
            selectedFile = true;
            fileAddress = file.getPath();
            String imagepath = null;
            try {
                imagepath = file.toURI().toURL().toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Image image = new Image(imagepath);
            iconImage.setImage(image);
        }
    }

    @FXML
    private void addAction(ActionEvent event) {
        boolean error = false;

        if (themeNameField.getText().equals("")) {
            if (Main.lang == 0)
                error = writeError("Введіть назву теми.", 1);
            if (Main.lang == 1)
                error = writeError("Введите название темы.", 1);
            if (Main.lang == 2)
                error = writeError("Write a theme name.", 1);
        }

        if (!error && !selectedFile) {
            if (Main.lang == 0)
                error = writeError("Виберіть іконку теми.", 1);
            if (Main.lang == 1)
                error = writeError("Выберите иконку темы.", 1);
            if (Main.lang == 2)
                error = writeError("Select theme icon.", 1);
        }

        if (!error && !Database.isThemeNameAvailable(themeNameField.getText(), LoginStageController.userId)) {
            if (Main.lang == 0)
                error = writeError("Така назва вже використана.", 1);
            if (Main.lang == 1)
                error = writeError("Такое название уже использовано.", 1);
            if (Main.lang == 2)
                error = writeError("Such name has already been used.", 1);
        }

        if (!error) {
            Database.addTheme(themeNameField.getText(), LoginStageController.userId);
            refreshList(true, false);
            deleteButton.setDisable(true);
            deleteLexemButton.setDisable(true);
            File source = new File(fileAddress);
            File dest = new File("icons\\"+ themeNameField.getText() + ".png");
            themeNameField.setText("");
            FileChannel sourceChannel = null;
            try {
                sourceChannel = new FileInputStream(source).getChannel();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            FileChannel destChannel = null;
            try {
                destChannel = new FileOutputStream(dest).getChannel();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                destChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                sourceChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            File file = new File("icons\\default_icon.png");
                    String imagepath = null;
            try {
                imagepath = file.toURI().toURL().toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Image image = new Image(imagepath);
            iconImage.setImage(image);
        }
    }

    @FXML
    private void addLexemAction(ActionEvent event) {
        boolean error = false;

        if (lexemNameField.getText().equals("")) {
            if (Main.lang == 0)
                error = writeError("Введіть назву лексеми.", 2);
            if (Main.lang == 1)
                error = writeError("Введите название лексемы.", 2);
            if (Main.lang == 2)
                error = writeError("Write a lexem name.", 2);
        }

        if (!error && !themeSelected) {
            if (lexemNameField.getText().equals("")) {
                if (Main.lang == 0)
                    error = writeError("Виберіть тему.", 2);
                if (Main.lang == 1)
                    error = writeError("Выберите тему.", 2);
                if (Main.lang == 2)
                    error = writeError("Select a theme.", 2);
            }
        }

        if (!error && !Database.isLexemAvailable(lexemNameField.getText(), Database.getThemeIdByNameAndUserId(themesList.getSelectionModel().getSelectedItem(), LoginStageController.userId))) {
            if (Main.lang == 0)
                error = writeError("Така назва вже використана.", 2);
            if (Main.lang == 1)
                error = writeError("Такое название уже использовано.", 2);
            if (Main.lang == 2)
                error = writeError("Such name has already been used.", 2);
        }

        if (!error) {
            int err = Database.addLexem(lexemNameField.getText(), Database.getThemeIdByNameAndUserId(themesList.getSelectionModel().getSelectedItem(), LoginStageController.userId));
            refreshList(false, true);
            deleteLexemButton.setDisable(true);
            lexemNameField.setText("");
        }
    }

    @FXML
    private void backAction(ActionEvent event) {
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
        MainStageController.editThemesStage.close();
    }

    @FXML
    private void themeMouseClicked(MouseEvent event) {
        if (themesList.getSelectionModel().getSelectedItem() != null) {
            refreshList(false, true);
            deleteButton.setDisable(false);
            deleteLexemButton.setDisable(true);
            themeSelected = true;
        }
    }

    @FXML
    private void lexemMouseClicked(MouseEvent event) {
        if (lexemsList.getSelectionModel().getSelectedItem() != null) {
            deleteLexemButton.setDisable(false);
        }
    }

    @FXML
    private void nameTypedAction(KeyEvent event) {
        if (themeNameField.getLength() > 20) {
            themeNameField.setText(themeNameField.getText(0, 20));
            if (Main.lang == 0)
                writeError("Назва не може перевищувати 20 символів.", 1);
            if (Main.lang == 1)
                writeError("Название не может превышать 20 символов.", 1);
            if (Main.lang == 2)
                writeError("Name can't be larger than 20 symbols.", 1);
        }
    }

    @FXML
    private void lexemTypedAction(KeyEvent event) {
        if (lexemNameField.getLength() > 40) {
            lexemNameField.setText(lexemNameField.getText(0, 40));
            if (Main.lang == 0)
                writeError("Лексема не може перевищувати 40 символів.", 2);
            if (Main.lang == 1)
                writeError("Лексема не может превышать 40 символов.", 2);
            if (Main.lang == 2)
                writeError("Lexem can't be larger than 40 symbols.", 2);
        }
    }

    public boolean writeError(String text, int error) {
        if (error == 1) {
            errorLabel.setText(text);
            errorLabel.setVisible(true);
            errorLabel.setTextFill(Color.RED);
            return true;
        }
        if (error == 2) {
            errorLexemLabel.setText(text);
            errorLexemLabel.setVisible(true);
            errorLexemLabel.setTextFill(Color.RED);
            return true;
        }
        return true;
    }

    private void refreshList(boolean addThemes, boolean addLexems) {
        if (addThemes) {
            while (themesList.getItems().size() != 0) {
                themesList.getItems().remove(0);
            }
            Database.createThemes(LoginStageController.userId);
            String elementName = "";
            while ((elementName = Database.getThemesByUserID(LoginStageController.userId)) != null) {
                themesList.getItems().add(elementName);
            }

            while (lexemsList.getItems().size() != 0) {
                lexemsList.getItems().remove(0);
            }
        }
        if (addLexems) {
            while (lexemsList.getItems().size() != 0) {
                lexemsList.getItems().remove(0);
            }

            Database.createLexems(Database.getThemeIdByNameAndUserId(themesList.getSelectionModel().getSelectedItem(), LoginStageController.userId));
            String lexemName = "";
            while ((lexemName = Database.getLexems(Database.getThemeIdByNameAndUserId(themesList.getSelectionModel().getSelectedItem(), LoginStageController.userId))) != null) {
                lexemsList.getItems().add(lexemName);
            }
        }
    }

}
