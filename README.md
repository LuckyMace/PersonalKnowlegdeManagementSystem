# PersonalKnowlegdeManagementSystem
Personal Knowledge Management System with use of classification and clusterization algorithms
Java (IntelliJ IDEA 14.2, JDK 1.7) + JDBC + Oracle 11gR2 + Apache POI + JavaFX

System was created like a viewer of documents (.docx files) with use of algorithms for automatization user's work.
- File dbcreate.sql IS USING to create database in DBMS Oracle 11gR2;
- File DataBaseSettings.txt IS USING to enter DB settings like a: 1. Hostname 2. Password 3. Username 4. Password 5. SID;
- Files Sport.png, Religion.png, Economics.png, Psycology.png IS USING for creation an icons of sample document's themes;
- Folders Sport, Religion, Economics, Psycology IS USING for storing a .docx documents for sample themes;
- Folder "icons" IS USING to store themes's icons which is added in DB;
- Folder "documents" IS USING to store .docx documents which is added in DB;
- File "ojdbc6.jar" is a library for using a JDBC for work with DB.

Let's go to see the code! Folder "src" stores all the packages.


1) package "javaclass" CONTAINS Main.java and Database.java:

- Main.java. Run class with main method and creation a Login Stage (.fxml file, created in JavaFX Scene Builder);
- Database.java. Class with methods for connecting to DB, select, insert and delete functions for Tables and Views in DB;

2) package "fxml" CONTAINS the next .fxml files (created in JavaFX Scene Builder):

- LoginFXML.fxml. File contains the construction of Login Stage;
- ConnectFXML.fxml. File contains the construction of Connection to DB Stage;
- RegisterFXML.fxml. File contains the construction of Register new account Stage;
- MainFXML.fxml. File contains the construction of Main Stage with user's work;
- SettingsFXML.fxml. File contains the construction of Settings Stage;
- NewDocumentFXML.fxml. File contains the construction of New Document Stage;
- EditThemesFXML.fxml. File contains the construction of Edit Themes and Lexems Stage.

3) package "controller" CONTAINS the next classes:

- LoginStageController.java. Class contains the controller, applied to LoginFXML.fxml file;
- ConectStageController.java. Class contains the controller, applied to ConnectFXML.fxml file;
- RegisterStageController.java. Class contains the controller, applied to RegisterFXML.fxml file;
- MainStageController.java. Class contains the controller, applied to MainFXML.fxml file;
- SettingsStageController.java. Class contains the controller, applied to SettingsFXML.fxml file;
- NewDocumentController.java. Class contains the controller, applied to NewDocumentFXML.fxml file;
- EditThemesController.java. Class contains the controller, applied to EditThemesFXML.fxml file.
