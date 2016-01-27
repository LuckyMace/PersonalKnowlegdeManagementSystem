package controller;

import javaclass.Database;
import javaclass.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import javax.swing.*;
import javax.xml.crypto.Data;

public class MainStageController {
    @FXML public static Stage newDocumentStage;
    @FXML public static Stage editThemesStage;
    @FXML public static Stage showSettingsStage;

    @FXML private AnchorPane mainAnchorPane;
    @FXML private Label lexemLabel;
    @FXML private Label filterLabel;
    @FXML private Label sortByLabel;
    @FXML private TextField lexemField;
    @FXML private ComboBox filterCombo;
    @FXML private ComboBox sortByCombo;
    @FXML private TreeView themesTree;
    @FXML private Button refreshButton;
    @FXML private Button lexemButton;
    @FXML private TextArea informationArea;
    @FXML private TitledPane documentInfPane;
    @FXML private MenuBar MainMenu;
    @FXML private HTMLEditor htmlEditor;

    public String documentName = "";
    public boolean addType = true;

    @FXML
    private void initialize() {
        filterCombo.getSelectionModel().select(0);
        filterCombo.getSelectionModel().select(0);
        if (Main.lang == 0)
            informationArea.setText("Назва документу: \nТема документу: \n" +
                    "Найбільш підходяща тема документа: \nДата створення документу:");
        if (Main.lang == 1)
            informationArea.setText("Название документа: \nТема документа: \n" +
                    "Наиболее подходящая тема документа: \nДата создания документа:");
        if (Main.lang == 2)
            informationArea.setText("Document name: \nDocument theme: \n" +
                    "The most suitable theme for document: \nDocument add date:");
        if (Main.lang == 0) {
            lexemLabel.setText("Лексема:");
            filterLabel.setText("Фільтр:");
            sortByLabel.setText("Сортувати за:");
            sortByCombo.getItems().add("Без сортування");
            sortByCombo.getItems().add("Алфавітом");
            sortByCombo.getItems().add("Датою створення");
            refreshButton.setText("Застосувати");
            lexemButton.setText("Додати");
            documentInfPane.setText("Інформація документа");
            MainMenu.getMenus().get(0).setText("Файл");
            MainMenu.getMenus().get(0).getItems().get(0).setText("Новий документ");
            MainMenu.getMenus().get(0).getItems().get(1).setText("Зберегти документ");
            MainMenu.getMenus().get(0).getItems().get(2).setText("Видалити документ");
            MainMenu.getMenus().get(0).getItems().get(3).setText("Зміна користувача");
            MainMenu.getMenus().get(0).getItems().get(4).setText("Вихід");
            MainMenu.getMenus().get(1).setText("Редагування");
            MainMenu.getMenus().get(1).getItems().get(0).setText("Редагувати теми");
            MainMenu.getMenus().get(2).setText("Налаштування");
            MainMenu.getMenus().get(2).getItems().get(0).setText("Відкрити налаштування");
            MainMenu.getMenus().get(3).setText("Допомога");
            MainMenu.getMenus().get(3).getItems().get(0).setText("Про програму");
        }
        if (Main.lang == 1) {
            lexemLabel.setText("Лексема:");
            filterLabel.setText("Фильтр:");
            sortByLabel.setText("Сортировать по:");
            sortByCombo.getItems().add("Без сортировки");
            sortByCombo.getItems().add("Алфавиту");
            sortByCombo.getItems().add("Дате создания");
            refreshButton.setText("Применить");
            lexemButton.setText("Добавить");
            documentInfPane.setText("Информация документа");
            MainMenu.getMenus().get(0).setText("Файл");
            MainMenu.getMenus().get(0).getItems().get(0).setText("Новый документ");
            MainMenu.getMenus().get(0).getItems().get(1).setText("Сохранить документ");
            MainMenu.getMenus().get(0).getItems().get(2).setText("Удалить документ");
            MainMenu.getMenus().get(0).getItems().get(3).setText("Смена пользователя");
            MainMenu.getMenus().get(0).getItems().get(4).setText("Выход");
            MainMenu.getMenus().get(1).setText("Редактирование");
            MainMenu.getMenus().get(1).getItems().get(0).setText("Редактировать теми");
            MainMenu.getMenus().get(2).setText("Настройки");
            MainMenu.getMenus().get(2).getItems().get(0).setText("Открыть настройки");
            MainMenu.getMenus().get(3).setText("Помощь");
            MainMenu.getMenus().get(3).getItems().get(0).setText("О программе");
        }
        if (Main.lang == 2) {
            lexemLabel.setText("Lexem:");
            filterLabel.setText("Filter:");
            sortByLabel.setText("Sort by:");
            sortByCombo.getItems().add("None");
            sortByCombo.getItems().add("Alphabet");
            sortByCombo.getItems().add("Date");
            refreshButton.setText("Apply");
            lexemButton.setText("Add");
            documentInfPane.setText("Document information");
            MainMenu.getMenus().get(0).setText("File");
            MainMenu.getMenus().get(0).getItems().get(0).setText("New document");
            MainMenu.getMenus().get(0).getItems().get(1).setText("Save document");
            MainMenu.getMenus().get(0).getItems().get(2).setText("Delete document");
            MainMenu.getMenus().get(0).getItems().get(3).setText("Logout");
            MainMenu.getMenus().get(0).getItems().get(4).setText("Exit");
            MainMenu.getMenus().get(1).setText("Edit");
            MainMenu.getMenus().get(1).getItems().get(0).setText("Edit themes");
            MainMenu.getMenus().get(2).setText("Settings");
            MainMenu.getMenus().get(2).getItems().get(0).setText("Show settings");
            MainMenu.getMenus().get(3).setText("Help");
            MainMenu.getMenus().get(3).getItems().get(0).setText("About");
        }
        sortByCombo.getSelectionModel().select(0);
        refreshTree();
    }

    @FXML
    private void refreshTree() {
        lexemButton.setDisable(true);
        htmlEditor.setHtmlText("");
        lexemField.setText("");
        addType = true;
        if (Main.lang == 0)
            lexemButton.setText("Додати");
        if (Main.lang == 1)
            lexemButton.setText("Добавить");
        if (Main.lang == 2)
            lexemButton.setText("Add");
        if (Main.lang == 0)
            informationArea.setText("Назва документу: \nТема документу: \n" +
                    "Найбільш підходяща тема документа: \nДата створення документу:");
        if (Main.lang == 1)
            informationArea.setText("Название документа: \nТема документа: \n" +
                    "Наиболее подходящая тема документа: \nДата создания документа:");
        if (Main.lang == 2)
            informationArea.setText("Document name: \nDocument theme: \n" +
                    "The most suitable theme for document: \nDocument add date:");

        ArrayList<String> themesList = new ArrayList<String>();

        if (filterCombo.getSelectionModel().isSelected(0)) {
            Database.createThemes(LoginStageController.userId);
            String elementName = "";
            while ((elementName = Database.getThemesByUserID(LoginStageController.userId)) != null) {
                themesList.add(elementName);
            }
        } else {
            themesList.add(filterCombo.getSelectionModel().getSelectedItem().toString());
        }


        File file = new File("icons\\folder_icon.png");
        String imagepath = null;
        try {
            imagepath = file.toURI().toURL().toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        ImageView rootImage = new ImageView(new Image(imagepath));
        rootImage.setFitHeight(25);
        rootImage.setFitWidth(25);
        Node rootIcon = rootImage;
        TreeItem<String> rootNode = new TreeItem<String>("Теми", rootIcon);

        for (int i = 0; i < themesList.size(); i++) {
            File iconFile = new File("icons\\"+ themesList.get(i) +".png");
            String iconFilePath = null;
            try {
                iconFilePath = iconFile.toURI().toURL().toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            ImageView themeImage = new ImageView(new Image(iconFilePath));
            themeImage.setFitHeight(25);
            themeImage.setFitWidth(25);
            Node themeIcon = themeImage;
            TreeItem<String> themeNode = new TreeItem<String>(themesList.get(i), themeIcon);

            Database.createThemes(LoginStageController.userId);
            int themeId = Database.getThemeIdByNameAndUserId(themesList.get(i), LoginStageController.userId);
            String documentName = "";
            if (sortByCombo.getSelectionModel().isSelected(0)) {
                Database.createDocumentsNone(themeId);
                while ((documentName = Database.getDocumentsNone(themeId)) != null) {
                    TreeItem<String> next_doc = new TreeItem<String>(documentName);
                    themeNode.getChildren().add(next_doc);
                }
            }
            if (sortByCombo.getSelectionModel().isSelected(1)) {
                Database.createDocumentsAlphabet(themeId);
                while ((documentName = Database.getDocumentsAlphabet(themeId)) != null) {
                    TreeItem<String> next_doc = new TreeItem<String>(documentName);
                    themeNode.getChildren().add(next_doc);
                }
            }
            if (sortByCombo.getSelectionModel().isSelected(2)) {
                Database.createDocumentsDate(themeId);
                while ((documentName = Database.getDocumentsDate(themeId)) != null) {
                    TreeItem<String> next_doc = new TreeItem<String>(documentName);
                    themeNode.getChildren().add(next_doc);
                }
            }

            rootNode.getChildren().add(themeNode);
        }
        themesTree.setRoot(rootNode);
    }

    @FXML
    private void refreshHTMLEditor() {
        if (themesTree.getSelectionModel().getSelectedItem() != null) {
            documentName = themesTree.getSelectionModel().getSelectedItem().toString().substring(18, themesTree.getSelectionModel().getSelectedItem().toString().length() - 2);

            boolean notDocument = Database.isDocumentAvailable(documentName);
            if (!notDocument) { // IF IT DOCUMENT BEGIN
                lexemButton.setDisable(false);

                try {
                    FileInputStream fis = new FileInputStream("documents\\" + documentName + ".docx");
                    try {
                        XWPFDocument docx = new XWPFDocument(fis);
                        XWPFWordExtractor we = new XWPFWordExtractor(docx);
                        String docxFileText = we.getText();
                        String currentWord = "";;
                        ArrayList<Integer> themesIdList = new ArrayList<Integer>();
                        ArrayList<Boolean> themesBanList = new ArrayList<Boolean>();
                        ArrayList<Double> themesProbList = new ArrayList<Double>();
                        ArrayList<Double> themesEpsiList = new ArrayList<Double>();
                        ArrayList<String> docxWordsList = new ArrayList<String>();
                        double lexemsNumber = 0.0;
                        double documentsNumber = 0.0;
                        double numbGoodThemes = 0.0;

                        Database.createThemes(LoginStageController.userId);
                        String elementName = "";
                        while ((elementName = Database.getThemesByUserID(LoginStageController.userId)) != null) {
                            themesIdList.add(Database.getThemeIdByNameAndUserId(elementName, LoginStageController.userId));
                        }

                        for (int i = 0; i < docxFileText.length(); i++) {
                            char ch = docxFileText.charAt(i);
                            if (isLetter(ch)||((((int) ch == 39) || ((int) ch == 45)) && (isLetter(docxFileText.charAt(i - 1))) && (isLetter(docxFileText.charAt(i + 1))))) {
                                currentWord += ch;
                            } else {
                                if (!currentWord.equals("")) {
                                    docxWordsList.add(currentWord);
                                    currentWord = "";
                                }
                            }
                        }

                        for (int i = 0; i < themesIdList.size(); i++) {
                            Database.createLexems(themesIdList.get(i));
                            while (Database.getLexems(themesIdList.get(i)) != null) {
                                lexemsNumber = lexemsNumber + 1.0;
                            }
                        }

                        for (int i = 0; i < themesIdList.size(); i++) {
                            Database.createDocumentsNone(themesIdList.get(i));
                            while (Database.getDocumentsNone(themesIdList.get(i)) != null) {
                                documentsNumber = documentsNumber + 1.0;
                            }
                        }

                        for (int i = 0; i < themesIdList.size(); i++) { //BEGIN FOR ALL THEMES
                            ArrayList<String> lexemsList = new ArrayList<String>();
                            ArrayList<Double> findList = new ArrayList<Double>();
                            double themeLexemNumber = 0.0;
                            double themeDocumentNumber = 0.0;

                            Database.createLexems(themesIdList.get(i));
                            String elementName2 = "";
                            while ((elementName2 = Database.getLexems(themesIdList.get(i))) != null) {
                                lexemsList.add(elementName2);
                                findList.add(1.0);
                                themeLexemNumber = themeLexemNumber + 1.0;
                            }

                            Database.createDocumentsNone(themesIdList.get(i));
                            while (Database.getDocumentsNone(themesIdList.get(i)) != null) {
                                themeDocumentNumber = themeDocumentNumber + 1.0;
                            }

                            if (!(themeLexemNumber == 0 || documentsNumber == 0)) {
                                themesBanList.add(false);
                                numbGoodThemes = numbGoodThemes + 1.0;
                                for (int j = 0; j < lexemsList.size(); j++) {
                                    for (int k = 0; k < docxWordsList.size(); k++) {
                                        if (lexemsList.get(j).equals(docxWordsList.get(k)))
                                            findList.set(j, findList.get(j) + 5.0);
                                    }
                                }

                                Double result = Math.log(themeDocumentNumber / documentsNumber);
                                for (int j = 0; j < themeLexemNumber; j++) {
                                    result += Math.log(findList.get(j) / (lexemsNumber + themeLexemNumber));
                                }
                                themesEpsiList.add(result);

                            } else {
                                themesEpsiList.add((double) -10000);
                                themesBanList.add(true);
                            }
                        } //END FOR ALL THEMES

                        for (int i = 0; i < themesEpsiList.size(); i++) {

                            if (!themesBanList.get(i)) {
                                Double summ = 0.0;
                                for (int j = 0; j < themesEpsiList.size(); j++) {
                                    if (!themesBanList.get(j)) {
                                        summ += Math.pow(2.718, themesEpsiList.get(j));
                                    }
                                }
                                themesProbList.add(Math.pow(2.718, themesEpsiList.get(i)) / summ);
                            } else {
                                themesProbList.add(0.0);
                            }
                        }

                        double maxProb = themesProbList.get(0);
                        int maxIndex = 0;
                        for (int i = 1; i < themesProbList.size(); i++) {
                            if (themesProbList.get(i) > maxProb) {
                                maxIndex = i;
                                maxProb = themesProbList.get(i);
                            }
                        }

                        String themeName = Database.getThemeNameByIdAndUserId(themesIdList.get(maxIndex), LoginStageController.userId);
                        maxProb = maxProb * 100.0;

                        Database.Document document = Database.getDocumentByName(documentName);
                        if (numbGoodThemes > 1) {
                            if (maxProb >= (120.0 / numbGoodThemes)) {
                                if (Main.lang == 0)
                                    informationArea.setText("Назва документу: " + document.Name +
                                            ";\nТема документа: " + Database.getThemeNameByIdAndUserId(document.ThemeID, LoginStageController.userId) +
                                            ";\nНайбільш підходяща тема документа: " + themeName +
                                            ";\nДата створення документа: " + document.AddDate + ";");
                                if (Main.lang == 1)
                                    informationArea.setText("Название документа: " + document.Name +
                                            ";\nТема документа: " + Database.getThemeNameByIdAndUserId(document.ThemeID, LoginStageController.userId) +
                                            ";\nНаиболее подходящая тема документа: " + themeName +
                                            ";\nДата создания документа: " + document.AddDate + ";");
                                if (Main.lang == 2)
                                    informationArea.setText("Document name: " + document.Name +
                                            ";\nDocument theme: " + Database.getThemeNameByIdAndUserId(document.ThemeID, LoginStageController.userId) +
                                            ";\nThe most suitable theme for document: " + themeName +
                                            ";\nDocument add date: " + document.AddDate + ";");
                            } else {
                                if (Main.lang == 0)
                                    informationArea.setText("Назва документу: " + document.Name +
                                            ";\nТема документа: " + Database.getThemeNameByIdAndUserId(document.ThemeID, LoginStageController.userId) +
                                            ";\nНайбільш підходяща тема документа: жодна не підходить" +
                                            ";\nДата створення документа: " + document.AddDate + ";");
                                if (Main.lang == 1)
                                    informationArea.setText("Название документа: " + document.Name +
                                            ";\nТема документа: " + Database.getThemeNameByIdAndUserId(document.ThemeID, LoginStageController.userId) +
                                            ";\nНаиболее подходящая тема документа: ни одна не подходит" +
                                            ";\nДата создания документа: " + document.AddDate + ";");
                                if (Main.lang == 2)
                                    informationArea.setText("Document name: " + document.Name +
                                            ";\nDocument theme: " + Database.getThemeNameByIdAndUserId(document.ThemeID, LoginStageController.userId) +
                                            ";\nThe most suitable theme for document: no one" +
                                            ";\nDocument add date: " + document.AddDate + ";");
                            }
                        } else {
                            if (Main.lang == 0)
                                informationArea.setText("Назва документу: " + document.Name +
                                        ";\nТема документа: " + Database.getThemeNameByIdAndUserId(document.ThemeID, LoginStageController.userId) +
                                        ";\nНайбільш підходяща тема документа: недостатня кількість даних для аналізу" +
                                        ";\nДата створення документа: " + document.AddDate + ";");
                            if (Main.lang == 1)
                                informationArea.setText("Название документа: " + document.Name +
                                        ";\nТема документа: " + Database.getThemeNameByIdAndUserId(document.ThemeID, LoginStageController.userId) +
                                        ";\nНаиболее подходящая тема документа: недостаточное количесво данных для анализа" +
                                        ";\nДата создания документа: " + document.AddDate + ";");
                            if (Main.lang == 2)
                                informationArea.setText("Document name: " + document.Name +
                                        ";\nDocument theme: " + Database.getThemeNameByIdAndUserId(document.ThemeID, LoginStageController.userId) +
                                        ";\nThe most suitable theme for document: insufficient data for analysis" +
                                        ";\nDocument add date: " + document.AddDate + ";");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    FileInputStream fis = new FileInputStream("documents\\"+ documentName +".docx");
                    try {
                        XWPFDocument docx = new XWPFDocument(fis);
                        XWPFWordExtractor we = new XWPFWordExtractor(docx);
                        String startText = we.getText();
                        String finishHTMLText = "<html><body>";
                        String currentWord = "";
                        ArrayList<String> lexemsList = new ArrayList<String>();

                        Database.createLexems(Database.getThemeIdByDocumentName(documentName));
                        String elementName = "";
                        while ((elementName = Database.getLexems(Database.getThemeIdByDocumentName(documentName))) != null) {
                            lexemsList.add(elementName);
                        }

                        for (int i = 0; i < startText.length(); i++) {
                            char ch = startText.charAt(i);
                            if (isLetter(ch)) {
                                currentWord += ch;
                            } else {
                                if ((((int) ch == 39) || ((int) ch == 45)) && (isLetter(startText.charAt(i - 1))) && (isLetter(startText.charAt(i + 1)))) {
                                    boolean isLexem = false;
                                    for (int j = 0; j < lexemsList.size(); j++) {
                                        if (currentWord.equals(lexemsList.get(j))) {
                                            isLexem = true;
                                            break;
                                        }
                                    }
                                    if (isLexem) {
                                        finishHTMLText += "<font color=\"red\">" + currentWord + "</font>";
                                        currentWord = "";
                                    } else {
                                        currentWord += ch;
                                    }
                                } else {
                                    if (currentWord.equals("")) {
                                        finishHTMLText += ch;
                                    } else {
                                        boolean isLexem = false;
                                        for (int j = 0; j < lexemsList.size(); j++) {
                                            if (currentWord.equals(lexemsList.get(j))) {
                                                isLexem = true;
                                                break;
                                            }
                                        }
                                        if (isLexem) {
                                            finishHTMLText += "<font color=\"red\">" + currentWord + "</font>" + ch;
                                            currentWord = "";
                                        } else {
                                            finishHTMLText += currentWord + ch;
                                            currentWord = "";
                                        }
                                    }
                                }
                            }
                        }
                        htmlEditor.setHtmlText(finishHTMLText + "</body></html>");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            } // IF IT DOCUMENT END
        }
    }

    @FXML
    private void newDocumentAction(ActionEvent event) {
        Stage newDocumentStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/NewDocumentFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Main.lang == 0)
            newDocumentStage.setTitle("Новий документ");
        if (Main.lang == 1)
            newDocumentStage.setTitle("Новый документ");
        if (Main.lang == 2)
            newDocumentStage.setTitle("New document");
        newDocumentStage.setScene(new Scene(root, 246, 241));
        newDocumentStage.show();
        newDocumentStage.setResizable(false);
        this.newDocumentStage = newDocumentStage;
        mainAnchorPane.setDisable(true);
    }

    @FXML
    private void saveDocumentAction(ActionEvent event) {
        if (themesTree.getSelectionModel().getSelectedItem() != null) {
            documentName = themesTree.getSelectionModel().getSelectedItem().toString().substring(18, themesTree.getSelectionModel().getSelectedItem().toString().length() - 2);
            boolean notDocument = Database.isDocumentAvailable(documentName);
            if (!notDocument) { // IF IT DOCUMENT BEGIN
                XWPFDocument document = new XWPFDocument();
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(new File("documents\\"+ documentName + ".docx"));
                    XWPFParagraph tmpParagraph = document.createParagraph();
                    XWPFRun tmpRun = tmpParagraph.createRun();
                    tmpRun.setText(stripHTMLTags(htmlEditor.getHtmlText()));
                    refreshTree();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    document.write(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @FXML
    private void deleteDocumentAction(ActionEvent event) {
        if (themesTree.getSelectionModel().getSelectedItem() != null) {
            documentName = themesTree.getSelectionModel().getSelectedItem().toString().substring(18, themesTree.getSelectionModel().getSelectedItem().toString().length() - 2);
            boolean notDocument = Database.isDocumentAvailable(documentName);
            if (!notDocument) { // IF IT DOCUMENT BEGIN
                Database.deleteDocumentByName(documentName);
                refreshTree();
            }
        }
    }

    @FXML
    private void logoutAction(ActionEvent event) {
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
        LoginStageController.mainStage.close();
    }

    @FXML
    private void exitAction(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void editThemesAction(ActionEvent event) {
        Stage editThemesStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/EditThemesFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Main.lang == 0)
            editThemesStage.setTitle("Редагувати теми");
        if (Main.lang == 1)
            editThemesStage.setTitle("Редактировать темы");
        if (Main.lang == 2)
            editThemesStage.setTitle("Edit themes");
        editThemesStage.setScene(new Scene(root, 524, 331));
        editThemesStage.show();
        editThemesStage.setResizable(false);
        this.editThemesStage = editThemesStage;
        mainAnchorPane.setDisable(true);
    }

    @FXML
    private void showSettingsAction(ActionEvent event) {
        Stage showSettingsStage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/SettingsFXML.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (Main.lang == 0)
            showSettingsStage.setTitle("Налаштування");
        if (Main.lang == 1)
            showSettingsStage.setTitle("Настройки");
        if (Main.lang == 2)
            showSettingsStage.setTitle("Settings");
        showSettingsStage.setScene(new Scene(root, 204, 84));
        showSettingsStage.show();
        showSettingsStage.setResizable(false);
        this.showSettingsStage = showSettingsStage;
        mainAnchorPane.setDisable(true);
    }

    @FXML
    private void aboutAction(ActionEvent event) {
        if (Main.lang == 0)
            JOptionPane.showMessageDialog(null, "Програма була розроблена Рубаном Павлом.\n" +
                    "Усі права захищені(c). 2016", "Про програму", JOptionPane.INFORMATION_MESSAGE);
        if (Main.lang == 1)
            JOptionPane.showMessageDialog(null, "Программа была розработана Рубаном Павлом.\n" +
                    "Все права защищены(c). 2016", "О программе", JOptionPane.INFORMATION_MESSAGE);
        if (Main.lang == 2)
            JOptionPane.showMessageDialog(null, "The program was created by Pavlo Ruban.\n" +
                    "All rights reserved(c). 2016", "About", JOptionPane.INFORMATION_MESSAGE);
    }

    @FXML
    private void refreshButtonAction(ActionEvent event) {
        refreshTree();
    }

    @FXML
    private void lexemAction(ActionEvent event) {
        if (addType) { // ADD LEXEM
            boolean error = false;

            if (lexemField.getText().equals("")) {
                if (Main.lang == 0)
                    JOptionPane.showMessageDialog(null, "Помилка. Введіть назву лексеми.", "Помилка", JOptionPane.ERROR_MESSAGE);
                if (Main.lang == 1)
                    JOptionPane.showMessageDialog(null, "Ошибка. Введите название лексемы.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                if (Main.lang == 2)
                    JOptionPane.showMessageDialog(null, "Error. Write a lexem name.", "Error", JOptionPane.ERROR_MESSAGE);
                error = true;
            }

            if (!error) {
                Database.addLexem(lexemField.getText(), Database.getThemeIdByDocumentName(documentName));
            }
        } else { // DELETE LEXEM
            Database.deleteLexemByNameAndThemeId(lexemField.getText(), Database.getThemeIdByDocumentName(documentName));
        }
        lexemField.setText("");
        refreshHTMLEditor();
        if (Main.lang == 0)
            lexemButton.setText("Додати");
        if (Main.lang == 1)
            lexemButton.setText("Добавить");
        if (Main.lang == 2)
            lexemButton.setText("Add");
        addType = true;
    }

    @FXML
    private void lexemTypedAction(KeyEvent event) {
        boolean invalidLexem = false;
        String newText = "";
        for (int i = 0; i < lexemField.getLength(); i++) {
            char ch = lexemField.getText().charAt(i);
            if ((isLetter(ch))||((int) ch == 39)||((int) ch == 45)) {
                newText += ch;
            } else {
                invalidLexem = true;
            }
        }
        if (invalidLexem)
            lexemField.setText(newText);

        if (lexemField.getLength() > 40) {
            lexemField.setText(lexemField.getText(0, 40));
            if (Main.lang == 0)
                JOptionPane.showMessageDialog(null, "Помилка. Лексема не може перевищувати 40 символів.", "Помилка", JOptionPane.ERROR_MESSAGE);
            if (Main.lang == 1)
                JOptionPane.showMessageDialog(null, "Ошибка. Лексема не может превышать 40 символов.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            if (Main.lang == 2)
                JOptionPane.showMessageDialog(null, "Error. Lexem can't be larger than 40 symbols.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            ArrayList<String> lexemsList = new ArrayList<String>();

            Database.createLexems(Database.getThemeIdByDocumentName(documentName));
            String elementName = "";
            while ((elementName = Database.getLexems(Database.getThemeIdByDocumentName(documentName))) != null) {
                lexemsList.add(elementName);
            }
            boolean type = false;
            for (int i = 0; i < lexemsList.size(); i++) {
                if (lexemsList.get(i).equals(lexemField.getText())) {

                    type = true;
                }
                if (type) {
                    if (Main.lang == 0)
                        lexemButton.setText("Видалити");
                    if (Main.lang == 1)
                        lexemButton.setText("Удалить");
                    if (Main.lang == 2)
                        lexemButton.setText("Delete");
                    addType = false;
                } else {
                    if (Main.lang == 0)
                        lexemButton.setText("Додати");
                    if (Main.lang == 1)
                        lexemButton.setText("Добавить");
                    if (Main.lang == 2)
                        lexemButton.setText("Add");
                    addType = true;
                }

            }
        }
    }

    @FXML
    private void filterComboAction(ActionEvent event) {
        if (filterCombo.getSelectionModel().isSelected(0) && filterCombo.getItems().get(0).equals("Show filters")) {
            while (filterCombo.getItems().size() != 0) {
                filterCombo.getItems().remove(0);
            }
            if (Main.lang == 0)
                filterCombo.getItems().add("Без фільтру");
            if (Main.lang == 1)
                filterCombo.getItems().add("Без фильтра");
            if (Main.lang == 2)
                filterCombo.getItems().add("None");

            Database.createThemes(LoginStageController.userId);
            String elementName = "";
            while ((elementName = Database.getThemesByUserID(LoginStageController.userId)) != null) {
                filterCombo.getItems().add(elementName);
            }
        }
    }

    @FXML
    private void treeMouseClicked(MouseEvent event) {
        refreshHTMLEditor();
        lexemField.setText("");
        addType = true;
        if (Main.lang == 0) {
            lexemButton.setText("Додати");
        }
        if (Main.lang == 1) {
            lexemButton.setText("Добавить");
        }
        if (Main.lang == 2) {
            lexemButton.setText("Add");
        }
    }

    public boolean isLetter(char symbol) {
        int code = (int) symbol;
        if (((code >= 65)&&(code <= 90))||((code >= 97)&&(code <= 122))||((code >= 1040)&&(code <= 1103))||(code == 1105)||(code == 1125)||(code == 1111)||(code == 1110)||(code == 1031)||(code == 1030))
            return true;
        else
            return false;
    }

    private String stripHTMLTags(String htmlText) {
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(htmlText);
        final StringBuffer sb = new StringBuffer(htmlText.length());
        while(matcher.find()) {
            matcher.appendReplacement(sb, " ");
        }
        matcher.appendTail(sb);
        return sb.toString().trim();

    }

}
