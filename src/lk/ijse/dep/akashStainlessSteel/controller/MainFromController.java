package lk.ijse.dep.akashStainlessSteel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;

import java.io.*;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
    

public class MainFromController {

    public ImageView imgOrder;
    public ImageView imgCustomer;
    public ImageView imgItem;
    public ImageView imgSearch;
    public Label lbl;
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgWorker;
    public AnchorPane anpLoad;
    public ImageView imgJob;
    public JFXButton btnRegistration;
    public JFXButton btnSignOut;
    public ImageView home;
    public JFXButton btnRestore;
    public JFXButton btnBackup;

    public JFXProgressBar progressBar;

    public void initialize(){
        this.progressBar.setVisible(false);
    }


    void show(String value) throws IOException {
        URL resource = this.getClass().getResource(value);
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage)( this.anpLoad.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        TranslateTransition tt = new TranslateTransition(Duration.millis(350), scene.getRoot());
        tt.setFromX(-scene.getWidth());
        tt.setToX(0);
        tt.play();
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch(icon.getId()){
                case "imgCustomer":
                    lblMenu.setText("Manage Customers");
                    lblDescription.setText("Click to add, edit, delete, search or view customers");
                    break;
                case "imgItem":
                    lblMenu.setText("Manage Items");
                    lblDescription.setText("Click to add, edit, delete, search or view items");
                    break;
                case "imgWorker":
                    lblMenu.setText("Manage Workers");
                    lblDescription.setText("Click to add, edit, delete, search or view workers");
                    break;
                case "imgJob":
                    lblMenu.setText("Manage Job");
                    lblDescription.setText("Click to add, edit, delete, search or view jobs");
                    break;
                case "imgOrder":
                    lblMenu.setText("Place Orders");
                    lblDescription.setText("Click here if you want to place a new order");
                    break;
                case "imgViewOrders":
                    lblMenu.setText("Search Orders");
                    lblDescription.setText("Click if you want to search orders");
                    break;
            }
            Theme.mouseEnteredImage(mouseEvent);
        }
    }
    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        Theme.mouseExitImage(mouseEvent);
        lblMenu.setText("Welcome");
        lblDescription.setText("Please select one of above main operations to proceed");
    }
    public void manageCustomer_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/CustomerLoginView.fxml");
    }
    public void placeOrder(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/OrderMain.fxml");
    }
    public void picSearch_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/SearchDashView.fxml");
    }
    public void picManageItems_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/ItemView.fxml");
    }
    public void picWorker_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/WorkerView.fxml");
    }
    public void picJob_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/JobView.fxml");
    }
    public void btnSignOut_OnAction(ActionEvent actionEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/LoginView.fxml");
    }

    public void btnRegistration_OnAction(ActionEvent actionEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/RegisterAccountView.fxml");
    }

    public void btnRestore_OnAction(ActionEvent actionEvent) {


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Let's restore the backup");
        fileChooser.getExtensionFilters().
                add(new FileChooser.ExtensionFilter("SQL File", "*.sql"));
        File file = fileChooser.showOpenDialog(this.anpLoad.getScene().getWindow());
        if (file != null){
            String[] commands;

            if (DBConnection.password.length() > 0){
                commands = new String[]{"mysql", "-h", DBConnection.host, "-u", DBConnection.username,
                        "-p" + DBConnection.password,"--port",DBConnection.port, DBConnection.db, "-e", "source " + file.getAbsolutePath()};
            }else{
                commands = new String[]{"mysql", "-h", DBConnection.host, "-u", DBConnection.username,"--port",DBConnection.port,
                        DBConnection.db, "-e", "source " + file.getAbsolutePath()};
            }
            commands = new String[]{"mysql", "-h", DBConnection.host, "-u", DBConnection.username,"--port",DBConnection.port,
                    DBConnection.db, "-e", "source " + file.getAbsolutePath()};


            this.anpLoad.getScene().setCursor(Cursor.WAIT);
            this.progressBar.setVisible(true);


            String[] finalCommands = commands;

            Task task = new Task<Void>(){
                @Override
                protected Void call() throws Exception {
                    Process process = Runtime.getRuntime().exec(finalCommands);
                    int exitCode = process.waitFor();
                    if (exitCode != 0) {
                        BufferedReader br = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                        br.lines().forEach(System.out::println);
                        br.close();
                        throw new RuntimeException("Some things wrong , Please contact service team");
                    } else {
                        return null;
                    }
                }
            };

            task.setOnSucceeded(event -> {
                this.progressBar.setVisible(false);
                this.anpLoad.getScene().setCursor(Cursor.DEFAULT);
                new Alert(Alert.AlertType.INFORMATION, "Restore process has been successfully done").show();
            });
            task.setOnFailed(event -> {
                this.progressBar.setVisible(false);
                this.anpLoad.getScene().setCursor(Cursor.DEFAULT);
                new Alert(Alert.AlertType.ERROR, "Failed to restore the backup. Please contact service team").show();
            } );
            new Thread(task).start();
        }
    }

    public void btnBackup_OnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the DB Backup");
        fileChooser.getExtensionFilters().
                add(new FileChooser.ExtensionFilter("SQL File", "*.sql"));
        File file = fileChooser.showSaveDialog(this.anpLoad.getScene().getWindow());
        if (file != null) {
            // Now, we have to backup the DB...
            try {
                Process process = Runtime.getRuntime().
                        exec("mysqldump -h" + DBConnection.host + " -u" + DBConnection.username +
                                " -p" + DBConnection.password + " " + DBConnection.db + " --result-file " +
                                file.getAbsolutePath() + ((file.getAbsolutePath().endsWith(".sql"))? "" : ".sql"));
                int exitCode = process.waitFor();
                // exitCode == 0; success
                // exitCode !=0; kachal
                if (exitCode != 0) {
                    // Let's read the damn error...!
                    InputStream errorStream = process.getErrorStream();
                    InputStreamReader isr = new InputStreamReader(errorStream); // chars
                    BufferedReader br = new BufferedReader(isr);    // string line by line
                    String out = "";
                    String line = null;
                    while ((line = br.readLine()) != null) {
                        out += line + "\n";
                    }
                    System.out.println(out);
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Database backup process has successfully done").show();
                }
                System.out.println(exitCode);
            } catch (IOException e) {
                e.printStackTrace();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }
        }
    }
}



