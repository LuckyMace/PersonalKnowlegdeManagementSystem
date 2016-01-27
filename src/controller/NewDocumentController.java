package controller;

import javaclass.Database;
import javaclass.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class NewDocumentController {
    @FXML private RadioButton manualChooseRadio;
    @FXML private RadioButton autoChooseRadio;
    @FXML private TextField nameField;
    @FXML private ComboBox themeCombo;
    @FXML private TextField resultField;
    @FXML private TextField percentField;
    @FXML private Label errorLabel;
    @FXML private Label nameLabel;
    @FXML private Label themeLabel;
    @FXML private Label selectLabel;
    @FXML private Label fullThemeLabel;
    @FXML private Label resultLabel;
    @FXML private Label byLabel;
    @FXML private Button browseButton;
    @FXML private Button cancelButton;
    @FXML private Button addButton;

    public static String fileAddress = null;
    public static boolean selectedFile = false;

    @FXML
    private void initialize() {
        if (Main.lang == 0) {
            nameLabel.setText("Назва документу:");
            themeLabel.setText("Тема:");
            selectLabel.setText("Виберіть документ:");
            fullThemeLabel.setText("Тема документу:");
            resultLabel.setText("Результат:");
            byLabel.setText("на");
            browseButton.setText("Відкрити");
            cancelButton.setText("Назад");
            addButton.setText("Додати");
            nameField.setPromptText("назва");
            manualChooseRadio.setText("Вибір теми вручну");
            autoChooseRadio.setText("Вибір оптимальної теми");
        }
        if (Main.lang == 1) {
            nameLabel.setText("Название документа:");
            themeLabel.setText("Тема:");
            selectLabel.setText("Выберите документ:");
            fullThemeLabel.setText("Тема документа:");
            resultLabel.setText("Результат:");
            byLabel.setText("на");
            browseButton.setText("Открыть");
            cancelButton.setText("Назад");
            addButton.setText("Добавить");
            nameField.setPromptText("название");
            manualChooseRadio.setText("Выбор темы вручную");
            autoChooseRadio.setText("Выбор оптимальной темы");
        }
        if (Main.lang == 2) {
            nameLabel.setText("Document name:");
            themeLabel.setText("Theme:");
            selectLabel.setText("Select document:");
            fullThemeLabel.setText("Document theme:");
            resultLabel.setText("Result:");
            byLabel.setText("by");
            browseButton.setText("Browse");
            cancelButton.setText("Cancel");
            addButton.setText("Add");
            nameField.setPromptText("name");
            manualChooseRadio.setText("Manual theme choose");
            autoChooseRadio.setText("Auto theme choose");
        }
        themeCombo.getSelectionModel().select(0);
    }

    @FXML
    private void browseAction(ActionEvent event) {
        FileChooser chooser = new FileChooser();
        if (Main.lang == 0)
            chooser.setTitle("Відкрити файл");
        if (Main.lang == 1)
            chooser.setTitle("Открыть файл");
        if (Main.lang == 2)
            chooser.setTitle("Open File");
        File file = chooser.showOpenDialog(new Stage());
        if(file != null) {
            selectedFile = true;
            fileAddress = file.getPath();
        }
    }

    @FXML
    private void cancelAction(ActionEvent event) {
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
        MainStageController.newDocumentStage.close();
    }

    @FXML
    private void addAction(ActionEvent event) {
        boolean error = false;

        if (nameField.getText().equals("")) {
            if (Main.lang == 0)
                error = writeError("Введіть назву документу.");
            if (Main.lang == 1)
                error = writeError("Введите название документа.");
            if (Main.lang == 2)
                error = writeError("Write a document name.");

        }

//        if (!selectedFile && !error) {
//            if (Main.lang == 0)
//                error = writeError("Виберіть документ.");
//            if (Main.lang == 1)
//                error = writeError("Выберите документ.");
//            if (Main.lang == 2)
//                error = writeError("Select a document.");
//        }

        if (!manualChooseRadio.isSelected() && !autoChooseRadio.isSelected() && !error) {
            if (Main.lang == 0)
                error = writeError("Виберіть тип теми документа.");
            if (Main.lang == 1)
                error = writeError("Выберите тип темы документа.");
            if (Main.lang == 2)
                error = writeError("Select document theme type.");
        }

        if ((manualChooseRadio.isSelected() || autoChooseRadio.isSelected()) && !error && !Database.isDocumentAvailable(nameField.getText())) {
            if (Main.lang == 0)
                error = writeError("Така назва вже була використана.");
            if (Main.lang == 1)
                error = writeError("Такое имя уже было использовано.");
            if (Main.lang == 2)
                error = writeError("Such document name has already been used.");
        }

        if (!error) {
            int themeID = 0;
            if (manualChooseRadio.isSelected()) {
                themeID = Database.getThemeIdByNameAndUserId(themeCombo.getSelectionModel().getSelectedItem().toString(), LoginStageController.userId);
            } else {
                themeID = Database.getThemeIdByNameAndUserId(resultField.getText(), LoginStageController.userId);
            }
            if (!selectedFile) {
                XWPFDocument document= new XWPFDocument();
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(new File("documents\\"+ nameField.getText() + ".docx"));
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
            } else {
                Database.addDocument(nameField.getText(), themeID);
                File source = new File(fileAddress);
                File dest = new File("documents\\"+ nameField.getText() + ".docx");
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
            }


            nameField.setText("");
            manualChooseRadio.setSelected(false);
            autoChooseRadio.setSelected(false);
            resultField.setText("");
            percentField.setText("");
            themeCombo.getSelectionModel().select(0);
            //MainStageController.newDocumentStage.close();
        }
    }

    @FXML
    private void manualAction(ActionEvent event) {
        if (manualChooseRadio.isSelected()) {
            autoChooseRadio.setSelected(false);
        }
    }

    @FXML
    private void autoAction(ActionEvent event) {
        if (!selectedFile) {
            if (Main.lang == 0)
                writeError("Виберіть документ.");
            if (Main.lang == 1)
                writeError("Выберите документ.");
            if (Main.lang == 2)
                writeError("Select a document.");
            autoChooseRadio.setSelected(false);
        } else {
            if (autoChooseRadio.isSelected()) {
                manualChooseRadio.setSelected(false);
            }

            try {
                FileInputStream fis = new FileInputStream(fileAddress);
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
                            lexemsNumber++;
                        }
                    }

                    for (int i = 0; i < themesIdList.size(); i++) {
                        Database.createDocumentsNone(themesIdList.get(i));
                        while (Database.getDocumentsNone(themesIdList.get(i)) != null) {
                            documentsNumber++;
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
                            findList.add(70.0);
                            themeLexemNumber++;
                        }

                        Database.createDocumentsNone(themesIdList.get(i));
                        while (Database.getDocumentsNone(themesIdList.get(i)) != null) {
                            themeDocumentNumber++;
                        }

                        if (!(themeLexemNumber == 0 || documentsNumber == 0)) {
                            themesBanList.add(false);
                            for (int j = 0; j < lexemsList.size(); j++) {
                                for (int k = 0; k < docxWordsList.size(); k++) {
                                    if (lexemsList.get(j).equals(docxWordsList.get(k)))
                                        findList.set(j, findList.get(j) + 70.0);
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
//                        System.out.println("Theme " + themesIdList.get(i));
//                        System.out.println("EPSI " + themesEpsiList.get(i));
                        if (!themesBanList.get(i)) {
                            Double summ = 0.0;
                            for (int j = 0; j < themesEpsiList.size(); j++) {
                                if (!themesBanList.get(j)) {
                                    summ += Math.pow(2.718, themesEpsiList.get(j));
                                }
                            }
                            themesProbList.add(Math.pow(2.718, themesEpsiList.get(i)) / summ);
//                            System.out.println("верх " + Math.pow(2.718, themesEpsiList.get(i)));
//                            System.out.println("низ " + summ + "\n");
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

                    resultField.setText(Database.getThemeNameByIdAndUserId(themesIdList.get(maxIndex), LoginStageController.userId));
                    percentField.setText(String.valueOf(maxProb * 100));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void showThemesAction(ActionEvent event) {
        if (themeCombo.getSelectionModel().isSelected(0) && themeCombo.getItems().get(0).equals("Show themes")) {
            while (themeCombo.getItems().size() != 0) {
                themeCombo.getItems().remove(0);
            }

            Database.createThemes(LoginStageController.userId);
            String elementName = "";
            while ((elementName = Database.getThemesByUserID(LoginStageController.userId)) != null) {
                themeCombo.getItems().add(elementName);
            }
        }
    }

    @FXML
    private void nameTypedAction(KeyEvent event) {
        if (nameField.getLength() > 100) {
            nameField.setText(nameField.getText(0, 100));
            if (Main.lang == 0)
                writeError("Назва не може перевищувати 100 символів.");
            if (Main.lang == 1)
                writeError("Название не может превышать 100 символов.");
            if (Main.lang == 2)
                writeError("Name can't be larger than 100 symbols.");
        }
    }

    public boolean writeError(String text) {
        errorLabel.setText(text);
        errorLabel.setVisible(true);
        errorLabel.setTextFill(Color.RED);
        return true;
    }

    public boolean isLetter(char symbol) {
        int code = (int) symbol;
        if (((code >= 65)&&(code <= 90))||((code >= 97)&&(code <= 122))||((code >= 1040)&&(code <= 1103))||(code == 1105)||(code == 1125)||(code == 1111)||(code == 1110)||(code == 1031)||(code == 1030))
            return true;
        else
            return false;
    }
}