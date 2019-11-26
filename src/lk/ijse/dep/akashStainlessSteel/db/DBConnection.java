package lk.ijse.dep.akashStainlessSteel.db;

import lk.ijse.dep.crypto.DEPCrypt;

import java.io.File;
import java.io.FileInputStream;
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
                String sql = "CREATE TABLE frCustomer(\n" +
                        "    customerId VARCHAR(10) PRIMARY KEY ,\n" +
                        "    name VARCHAR(20),\n" +
                        "    address VARCHAR(20),\n" +
                        "    contactNo int(10)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE frOrder(\n" +
                        "    orderId VARCHAR(10) PRIMARY KEY ,\n" +
                        "    date date,\n" +
                        "    frCustomerId VARCHAR(10),\n" +
                        "    CONSTRAINT FOREIGN KEY(frCustomerId) REFERENCES frCustomer(customerId)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE item(\n" +
                        "    itemCode VARCHAR(10) PRIMARY KEY ,\n" +
                        "    description VARCHAR(20),\n" +
                        "    unitPrice DECIMAL(10),\n" +
                        "    qtyOnHand int(10)\n" +
                        "\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE orderDetail(\n" +
                        "    orderId VARCHAR(10),\n" +
                        "    itemCode VARCHAR(10),\n" +
                        "    unitPrice DECIMAL(10),\n" +
                        "    quantity INt(10),\n" +
                        "    CONSTRAINT FOREIGN KEY(orderId) REFERENCES frOrder(orderId),\n" +
                        "    CONSTRAINT FOREIGN KEY(itemCode) REFERENCES item(itemCode),\n" +
                        "    PRIMARY KEY(orderId,itemCode)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE fnrCustomer(\n" +
                        "     customerId VARCHAR(10) PRIMARY KEY ,\n" +
                        "     name VARCHAR(20),\n" +
                        "     address VARCHAR(20),\n" +
                        "     contactNo int(10)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE fnrOrder(\n" +
                        "     orderId VARCHAR(10) PRIMARY KEY ,\n" +
                        "     date date,\n" +
                        "     jobId VARCHAR(10),\n" +
                        "     price DECIMAL(10),\n" +
                        "     advance DECIMAL(10),\n" +
                        "     CONSTRAINT FOREIGN KEY(jobId) REFERENCES job(jobId)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE job(\n" +
                        "      jobId VARCHAR(10)PRIMARY KEY ,\n" +
                        "      customerId VARCHAR(10),\n" +
                        "      description VARCHAR(100),\n" +
                        "      CONSTRAINT FOREIGN KEY(customerId) REFERENCES fnrCustomer(customerId)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE worker(\n" +
                        "      workerId VARCHAR(10) PRIMARY KEY ,\n" +
                        "      name VARCHAR(20),\n" +
                        "      address VARCHAR(50),\n" +
                        "      contactNo Int(10),\n" +
                        "      salary DECIMAL(10)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE jobDetail(\n" +
                        "      jobId VARCHAR(10),\n" +
                        "      workerId VARCHAR(10),\n" +
                        "      PRIMARY KEY(jobId,workerId),\n" +
                        "      CONSTRAINT FOREIGN KEY(jobId) REFERENCES job(jobId),\n" +
                        "      CONSTRAINT FOREIGN KEY(workerId) REFERENCES worker(workerId)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE estimation(\n" +
                        "      estimationNo VARCHAR(10) PRIMARY KEY ,\n" +
                        "      jobId VARCHAR(10),\n" +
                        "      workerId VARCHAR(10),\n" +
                        "      price DECIMAL(10),\n" +
                        "      CONSTRAINT FOREIGN KEY(jobId) REFERENCES job(jobId)\n" +
                        ");\n" +
                        "\n" +
                        "CREATE TABLE estimationDetail(\n" +
                        "      estimationNo VARCHAR(10),\n" +
                        "      itemCode VARCHAR(10),\n" +
                        "      eiQuantity int(10),\n" +
                        "      unitPrice DECIMAL(10),\n" +
                        "      PRIMARY KEY(estimationNo,itemCode),\n" +
                        "      CONSTRAINT FOREIGN KEY(estimationNo) REFERENCES estimation(estimationNo),\n" +
                        "      CONSTRAINT FOREIGN KEY(itemCode) REFERENCES item(itemCode)\n" +
                        ");";
                pstm = connection.prepareStatement(sql);
                pstm.execute();
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