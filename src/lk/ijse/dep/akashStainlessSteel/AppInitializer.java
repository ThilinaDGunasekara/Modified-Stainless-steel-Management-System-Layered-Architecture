package lk.ijse.dep.akashStainlessSteel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppInitializer extends Application {

    public static boolean isSplashLoad = false;

    public static void main(String[] args) {

        launch(args);
        try {
            DBConnection.getInstance().getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        Logger rootLogger= Logger.getLogger("");
        FileHandler fileHandler=new FileHandler("exception.log",true);
        fileHandler.setFormatter(new SimpleFormatter());
        fileHandler.setLevel(Level.FINEST);
        rootLogger.setLevel(Level.FINEST);
        rootLogger.addHandler(fileHandler);


        URL resource = this.getClass().getResource("/lk/ijse/dep/akashStainlessSteel/view/LoginView.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("@@@ AKASH STAINLESS STEEL(Pvt)Ltd @@@");
        primaryStage.show();
    }
}
