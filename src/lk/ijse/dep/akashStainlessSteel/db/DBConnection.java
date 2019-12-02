package lk.ijse.dep.akashStainlessSteel.db;

import javafx.scene.control.Alert;
import lk.ijse.dep.crypto.DEPCrypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class DBConnection {

    public static String host;
    public static String db;
    public static String username;
    public static String password;
    public static String port;
    private static DBConnection dbConnection;
    private Connection connection;

    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Properties properties = new Properties();
            File file = new File("/home/alisa/myProject/1-SSMS_Hibernate/resources/application.properties");
            FileInputStream fis = new FileInputStream(file);
            properties.load(fis);
            fis.close();

            String ip = properties.getProperty("akashStainlessSteel.ip");
            DBConnection.host = ip;
            String port = properties.getProperty("akashStainlessSteel.port");
            String db = properties.getProperty("akashStainlessSteel.db");
            DBConnection.db = db;
            String user = DEPCrypt.decode(properties.getProperty("akashStainlessSteel.user"),"123");
            DBConnection.username = user;
            String password = DEPCrypt.decode(properties.getProperty("akashStainlessSteel.password"),"123");
            DBConnection.password = password;



            connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db + "?createDatabaseIfNotExist=true&allowMultiQueries=true", user, password);
            PreparedStatement pstm = connection.prepareStatement("SHOW TABLES");
            ResultSet resultSet = pstm.executeQuery();
            if (!resultSet.next()){
                File dbScriptFile = new File("ASSMS-backup.sql");
                if (!dbScriptFile.exists()){
                    new Alert(Alert.AlertType.ERROR,"Database can't found,Please contact service team").show();
                    throw new RuntimeException("Unable to find the DB Script");
                }
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(dbScriptFile)));
                bufferedReader.lines().forEach(s -> stringBuilder.append(s));
                bufferedReader.close();
                PreparedStatement preparedStatement = connection.prepareStatement(stringBuilder.toString());
                preparedStatement.execute();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnection getInstance() {
        return (dbConnection == null) ? (dbConnection = new DBConnection()) : dbConnection;
    }

    public Connection getConnection() {
        return connection;
    }

}