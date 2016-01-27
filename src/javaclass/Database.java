package javaclass;

import java.sql.*;

public class Database {

    private static final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";                //Назва драйвера JDBC
    private static String DB_URL = "jdbc:oracle:thin:@//localhost:1521/orcl";    //Адреса розміщення бази даних
    private static String USER = "DIPLOM_RUBAN";                                        //Ім'я користувача
    private static String PASS = "DIPLOM_RUBAN";                                    //Пароль
    private static Connection conn = null;                                            //
    private static Statement stmt = null;
    private static ResultSet res = null;
    private static int N = 0;
    public static final double version = 1.2;
    //private static String[] result;

    //-------------------------------------------------------------------------------------------------------------------
    //Цей метод викликається 1 раз на початку, для підключення до бази даних. Він повертає true якщо підключення було вдале, і false якщо невдале.

    public static boolean connect() {

        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return false;
        }
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        try {
            stmt = conn.createStatement();
            return true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void setDataBaseSettings(String hostname, String port, String username, String password, String SID){
        DB_URL = "jdbc:oracle:thin:@//" + hostname +":"+ port +"/"+SID;
        USER = username;
        PASS = password;
    }
    public static String getDataBaseUserName(){
        return USER;
    }
    public static String getDataBaseUserPassword(){
        return PASS;
    }
    public static String getDataBaseHostName(){
        int end = DB_URL.indexOf(':', 20);
        return DB_URL.substring(20, end);
    }
    public static String getDataBasePort(){
        int begin = DB_URL.indexOf(':', 20);
        int end = DB_URL.indexOf('/', begin);
        return DB_URL.substring(begin+1, end);
    }

    public static int addUser(String Login, String Firstname, String Lastname, String Password, String EMail, String Birthday_Day, String Birthday_Month, String Birthday_Year, int Gender, String Country){
        if(!isLoginAvailable(Login))
            return 1;
        if(isEMailInDataBase(EMail))
            return 2;
        String insert = "Insert into \"Users\" (\"User_id\","+
                "\"User_login\","+
                "\"User_firstname\","+
                "\"User_lastname\","+
                "\"User_password\","+
                "\"User_email\","+
                "\"User_birthday\","+
                "\"User_gender\","+
                "\"User_country\","+
                "\"User_regdate\") "+
                "values (\"IncUserId\".nextval,"+
                "'" + Login + "',"+
                "'" + Firstname + "',"+
                "'" + Lastname + "',"+
                "'" + Password + "',"+
                "'" + EMail + "',"+
                " to_date('" + Birthday_Day + "." + Birthday_Month + "." + Birthday_Year +"','DD.MM.YYYY'),"+
                "" + Gender + ","+
                "'" + Country + "',"+
                "SYSDATE)";
        return executeUpdate(insert);
    }

    //----------------------------------------------------------------------------------------------------------------------
    // Метод перевіряє логін і пароль користувача і повертає ID користувача
    // У випадку помилки повертає 0

    public static int  getUserIdByLoginAndPassword(String login, String password)
    {
        if(login.isEmpty())
            return 0;
        if(password.isEmpty())
            return 0;
        try {
            res = stmt.executeQuery("SELECT \"Users\".\"User_id\" FROM \"Users\" WHERE \"Users\".\"User_login\" = '" + login +"' AND \"Users\".\"User_password\" = '" + password + "'");
            try {
                if(res.next()){
                    int id = res.getInt("User_id");
                    return id;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return 0;
    }

    private static int executeUpdate(String st){
        try{
            stmt.executeUpdate(st);
            return 0;
        }catch(SQLException e){
            return -1;
        }
    }

    // true - login вільний, false - login зайнятий
    public static boolean isLoginAvailable(String Login){
        try {
            res = stmt.executeQuery("SELECT \"Users\".\"User_id\" FROM \"Users\" WHERE \"Users\".\"User_login\" = '"+ Login +"'");
            try {
                if(res.next()){
                    res.getString("User_id");
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    // true - EMail є, false - EMail немає
    public static boolean isEMailInDataBase(String EMail){
        try {
            res = stmt.executeQuery("SELECT \"Users\".\"User_id\" FROM \"Users\" WHERE \"Users\".\"User_email\" = '"+ EMail +"'");
            try {
                if(res.next()){
                    res.getString("User_id");
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    /*
	    *
	    * 	Метод перевіряє наявність користувача з даним логіном в базі даних
	    *
	    * 	Метод повертає true якщо користувач є і false якщо користувача немає
	    *
	    */
    public static boolean isUserInDataBaseByLogin(String login)
    {
        if(login.isEmpty())
            return false;
        try {
            res = stmt.executeQuery("SELECT \"Users\".\"User_id\" FROM \"Users\" WHERE \"Users\".\"User_login\" = '"+ login +"'");
            try {
                if(res.next()){
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean isUserInDataBaseByPassword(String login, String password)
    {
        if(password.isEmpty())
            return false;
        try {
            res = stmt.executeQuery("SELECT \"Users\".\"User_id\" FROM \"Users\" WHERE \"Users\".\"User_login\" = '" + login +"' AND \"Users\".\"User_password\" = '" + password + "'");
            try {
                if(res.next()){
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static User getUserById(int ID_User){
        User user = new User();
        try {
            res = stmt.executeQuery("SELECT * FROM \"Users\" WHERE \"Users\".\"User_id\" = '"+ ID_User +"'");
            try {
                if(res.next()){
                    user.ID = res.getInt("User_id");
                    user.Login = res.getString("User_login");
                    user.Firstname = res.getString("User_firstname");
                    user.Lastname = res.getString("User_lastname");
                    user.Password = res.getString("User_password");
                    user.Email = res.getString("User_email");
                    user.Birthday = res.getDate("User_birthday");
                    user.Gender = res.getInt("User_gender");
                    user.Country = res.getString("User_country");
                    return user;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static class User{
        public int ID;
        public String Login;
        public String Firstname;
        public String Lastname;
        public String Password;
        public String Email;
        public Date Birthday;
        public int Gender;
        public String Country;
    }

    public static int addTheme(String Name, int UserID){
        String insert = "Insert into \"Themes\" (\"Theme_id\","+
                "\"Theme_name\","+
                "\"User_id\") "+
                "values (\"IncThemeId\".nextval,"+
                "'" + Name + "',"+
                "" + UserID + ")";
        return executeUpdate(insert);
    }

    public static int deleteThemeByNameAndUserId(String Name, int userID){
        String insert = "DELETE FROM \"Themes\" WHERE \"Themes\".\"Theme_name\" ='"+ Name +"' AND \"Themes\".\"User_id\" = " + userID;
        return executeUpdate(insert);
    }

    // true - login вільний, false - login зайнятий
    public static boolean isThemeNameAvailable(String themeName, int userID){
        try {
            res = stmt.executeQuery("SELECT \"Themes\".\"Theme_id\" FROM \"Themes\" WHERE \"Themes\".\"Theme_name\" = '"+ themeName +"' AND \"Themes\".\"User_id\" = " + userID);
            try {
                if(res.next()){
                    res.getString("Theme_id");
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    //                         !!! GET Themes !!!
    public static String getThemesByUserID(int userID){
        try {
            res = stmt.executeQuery("SELECT * FROM \"GetThemes\" WHERE \"User_id\" = "+ userID +" AND rank = "+ (N+1));
            try {
                if(res.next()){
                    String name = new String();
                    name = res.getString("Theme_name");
                    N = N + 1;
                    return name;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                N = 0;
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            N = 0;
            return null;
        }
        N = 0;
        return null;
    }

    public static void createThemes(int userID){
        try {
            res = stmt.executeQuery("CREATE OR REPLACE FORCE VIEW \"GetThemes\" (\"RANK\", \"Theme_name\", \"User_id\") AS \n" +
                    "  SELECT row_number() over (order by \"Themes\".\"Theme_name\") AS rank, \"Themes\".\"Theme_name\", \"Themes\".\"User_id\" \n" +
                    "FROM \"Themes\" WHERE \"Themes\".\"User_id\" = "+ userID +" ORDER BY \"Themes\".\"Theme_name\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getThemeNameByIdAndUserId(int themeID, int userID){
        String st = null;
        try {
            res = stmt.executeQuery("SELECT \"Themes\".\"Theme_name\" FROM \"Themes\" WHERE \"Themes\".\"Theme_id\" = " + themeID + " AND \"Themes\".\"User_id\" = " + userID);
            try {
                if(res.next()){
                    st = res.getString("Theme_name");
                    return st;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static int getThemeIdByNameAndUserId(String Name, int userID){
        int st = 0;
        try {
            res = stmt.executeQuery("SELECT \"Themes\".\"Theme_id\" FROM \"Themes\" WHERE \"Themes\".\"Theme_name\" = '" + Name + "' AND \"Themes\".\"User_id\" = " + userID);
            try {
                if(res.next()){
                    st = res.getInt("Theme_id");
                    return st;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    public static int getThemeIdByDocumentName(String Name){
        int st = 0;
        try {
            res = stmt.executeQuery("SELECT \"Documents\".\"Theme_id\" FROM \"Documents\" WHERE \"Documents\".\"Document_name\" = '" + Name + "'");
            try {
                if(res.next()){
                    st = res.getInt("Theme_id");
                    return st;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    public static int addDocument(String Name, int themeID){
        String insert = "Insert into \"Documents\" (\"Document_id\","+
                "\"Document_name\","+
                "\"Document_adddate\","+
                "\"Theme_id\") "+
                "values (\"IncDocumentId\".nextval,"+
                "'" + Name + "', " +
                "SYSDATE, "+
                "" + themeID + ")";
        return executeUpdate(insert);
    }

    public static int deleteDocumentByName(String Name){
        String insert = "DELETE FROM \"Documents\" WHERE \"Documents\".\"Document_name\" ='"+ Name +"'";
        return executeUpdate(insert);
    }

    public static int deleteAllDocumentByThemeId(int themeID){
        String insert = "DELETE FROM \"Documents\" WHERE \"Documents\".\"Theme_id\" = " + themeID;
        return executeUpdate(insert);
    }

    // true - login вільний, false - login зайнятий
    public static boolean isDocumentAvailable(String documentName){
        try {
            res = stmt.executeQuery("SELECT \"Documents\".\"Document_id\" FROM \"Documents\" WHERE \"Documents\".\"Document_name\" = '"+ documentName +"'");
            try {
                if(res.next()){
                    res.getString("Document_id");
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //                         !!! GET Documents !!!
    public static String getDocumentsNone(int themeID){
        try {
            res = stmt.executeQuery("SELECT * FROM \"GetDocumentsNone\" WHERE \"Theme_id\" = "+ themeID +" AND rank = "+ (N+1));
            try {
                if(res.next()){
                    String name = new String();
                    name = res.getString("Document_name");
                    N = N + 1;
                    return name;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                N = 0;
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            N = 0;
            return null;
        }
        N = 0;
        return null;
    }

    public static void createDocumentsNone(int themeID){
        try {
            res = stmt.executeQuery("CREATE OR REPLACE FORCE VIEW \"GetDocumentsNone\" (\"RANK\", \"Document_name\", \"Theme_id\") AS \n" +
                    "  SELECT row_number() over (order by \"Documents\".\"Document_id\") AS rank, \"Documents\".\"Document_name\", \"Documents\".\"Theme_id\"\n" +
                    "FROM \"Documents\" WHERE \"Theme_id\" = "+ themeID +" ORDER BY \"Documents\".\"Document_id\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getDocumentsAlphabet(int themeID){
        try {
            res = stmt.executeQuery("SELECT * FROM \"GetDocumentsAlphabet\" WHERE \"Theme_id\" = "+ themeID +" AND rank = "+ (N+1));
            try {
                if(res.next()){
                    String name = new String();
                    name = res.getString("Document_name");
                    N = N + 1;
                    return name;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                N = 0;
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            N = 0;
            return null;
        }
        N = 0;
        return null;
    }

    public static void createDocumentsAlphabet(int themeID){
        try {
            res = stmt.executeQuery("CREATE OR REPLACE FORCE VIEW \"GetDocumentsAlphabet\" (\"RANK\", \"Document_name\", \"Theme_id\") AS \n" +
                    "  SELECT row_number() over (order by \"Documents\".\"Document_name\") AS rank, \"Documents\".\"Document_name\", \"Documents\".\"Theme_id\"\n" +
                    "FROM \"Documents\" WHERE \"Theme_id\" = "+ themeID +" ORDER BY \"Documents\".\"Document_name\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getDocumentsDate(int themeID){
        try {
            res = stmt.executeQuery("SELECT * FROM \"GetDocumentsDate\" WHERE \"Theme_id\" = "+ themeID +" AND rank = "+ (N+1));
            try {
                if(res.next()){
                    String name = new String();
                    name = res.getString("Document_name");
                    N = N + 1;
                    return name;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                N = 0;
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            N = 0;
            return null;
        }
        N = 0;
        return null;
    }

    public static void createDocumentsDate(int themeID){
        try {
            res = stmt.executeQuery("CREATE OR REPLACE FORCE VIEW \"GetDocumentsDate\" (\"RANK\", \"Document_name\", \"Theme_id\") AS \n" +
                    "  SELECT row_number() over (order by \"Documents\".\"Document_adddate\") AS rank, \"Documents\".\"Document_name\", \"Documents\".\"Theme_id\"\n" +
                    "FROM \"Documents\" WHERE \"Theme_id\" = "+ themeID +" ORDER BY \"Documents\".\"Document_adddate\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class Document{
        public int ID;
        public String Name;
        public Date AddDate;
        public int ThemeID;
    }

    public static Document getDocumentByName(String docname){
        Document document = new Document();
        try {
            res = stmt.executeQuery("SELECT * FROM \"Documents\" WHERE \"Documents\".\"Document_name\" = '"+ docname +"'");
            try {
                if(res.next()){
                    document.ID = res.getInt("Document_id");
                    document.Name = res.getString("Document_name");
                    document.AddDate = res.getDate("Document_adddate");
                    document.ThemeID = res.getInt("Theme_id");
                    return document;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static int addLexem(String Name, int themeID){
        String insert = "Insert into \"Lexems\" (\"Lexem_id\","+
                "\"Lexem_name\","+
                "\"Theme_id\") "+
                "values (\"IncLexemId\".nextval,"+
                "'" + Name + "', " +
                "" + themeID + ")";
        return executeUpdate(insert);
    }

    //                         !!! GET Lexems !!!
    public static String getLexems(int themeID){
        try {
            res = stmt.executeQuery("SELECT * FROM \"GetLexems\" WHERE \"Theme_id\" = "+ themeID +" AND rank = "+ (N+1));
            try {
                if(res.next()){
                    String name = new String();
                    name = res.getString("Lexem_name");
                    N = N + 1;
                    return name;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                N = 0;
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            N = 0;
            return null;
        }
        N = 0;
        return null;
    }

    public static void createLexems(int themeID){
        try {
            res = stmt.executeQuery("CREATE OR REPLACE FORCE VIEW \"GetLexems\" (\"RANK\", \"Lexem_name\", \"Theme_id\") AS \n" +
                    "  SELECT row_number() over (order by \"Lexems\".\"Lexem_name\") AS rank, \"Lexems\".\"Lexem_name\", \"Lexems\".\"Theme_id\"\n" +
                    "FROM \"Lexems\" WHERE \"Theme_id\" = "+ themeID +" ORDER BY \"Lexems\".\"Lexem_name\"");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int deleteLexemByNameAndThemeId(String Name, int themeID){
        String insert = "DELETE FROM \"Lexems\" WHERE \"Lexems\".\"Lexem_name\" ='"+ Name +"' AND \"Lexems\".\"Theme_id\" = " + themeID;
        return executeUpdate(insert);
    }

    public static int deleteAllLexemByThemeId(int themeID){
        String insert = "DELETE FROM \"Lexems\" WHERE \"Lexems\".\"Theme_id\" = " + themeID;
        return executeUpdate(insert);
    }

    // true - login вільний, false - login зайнятий
    public static boolean isLexemAvailable(String lexemName, int themeID){
        try {
            res = stmt.executeQuery("SELECT \"Lexems\".\"Lexem_id\" FROM \"Lexems\" WHERE \"Lexems\".\"Lexem_name\" = '"+ lexemName +"' AND \"Lexems\".\"Theme_id\" = " + themeID);
            try {
                if(res.next()){
                    res.getString("Lexem_id");
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
