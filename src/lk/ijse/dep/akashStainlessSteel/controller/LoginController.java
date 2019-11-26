package lk.ijse.dep.akashStainlessSteel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.akashStainlessSteel.AppInitializer;
import lk.ijse.dep.akashStainlessSteel.business.BOFactory;
import lk.ijse.dep.akashStainlessSteel.business.BOTypes;
import lk.ijse.dep.akashStainlessSteel.business.custom.RegisterBO;
import lk.ijse.dep.akashStainlessSteel.dto.RegisterDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class LoginController {
    public AnchorPane anpCustomer;
    public AnchorPane anpLoad;
    public JFXTextField txtUserName;
    public JFXComboBox<String> cmbUserType;
    public JFXPasswordField password;
    public JFXButton btnLogin;
    public JFXButton btnRegister;
    public Label lblMenu;
    public JFXButton btnTempLoginWorker;
    public JFXButton btnTempLogAdmin;
    public JFXButton btnRestore;
    public JFXButton btnBackup;

    RegisterBO registerBO = BOFactory.getInstance().getBO(BOTypes.REGISTER);

    public void initialize() throws IOException {

        if(!AppInitializer.isSplashLoad){
            showSplashScreen();
        }

        ArrayList<String> string = new ArrayList<>();
        string.add(0,"Admin");
        string.add(1,"Employee");

        cmbUserType.setItems(FXCollections.observableList(string));


    }
    public void txtUserName_OnAction(ActionEvent actionEvent) {

    }

    public void cmbUserType_OnAction(ActionEvent actionEvent) {

    }

    public void btnLogin_OnAction(ActionEvent actionEvent) {

        if(txtUserName.getText().equals("")
                ||password.getText().equals("")
                    ||cmbUserType.getValue()== null){

            new Alert(Alert.AlertType.CONFIRMATION,"Check whether if all fields have been completed..").show();
            return;
        }

        String type = cmbUserType.getValue();
        String userName = txtUserName.getText();
        String password = this.password.getText();

        try {

            String userTypeIter = null;
            String userNameIter = null;
            String passwordIter = null;
            List<RegisterDTO> allRegisters = registerBO.findAllRegisters();
            for (RegisterDTO allRegister : allRegisters) {

                if(allRegister.getUserType().equals(type) && allRegister.getUserName().equals(userName) && allRegister.getPassword().equals(password)) {
                    userTypeIter = allRegister.getUserType();
                    userNameIter = allRegister.getUserName();
                    passwordIter = allRegister.getPassword();
                }

            }

            if(userTypeIter==null || userNameIter ==null || passwordIter ==null){
                new Alert(Alert.AlertType.ERROR,"Incorrect user name, password or userType..").show();
                return;
            }
            if (userTypeIter.equals("Admin")) {
                show("/lk/ijse/dep/akashStainlessSteel/view/MainFromDash.fxml");
                return;
            }else if(userTypeIter.equals("Employee")){
                show("/lk/ijse/dep/akashStainlessSteel/view/ManageEstimationView.fxml");
                return;
            }


        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }

    }

    public void password_OnAction(ActionEvent actionEvent) {

    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {

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

    public void showSplashScreen() throws IOException {

        AppInitializer.isSplashLoad =true;

        AnchorPane root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/akashStainlessSteel/view/SplashScreenView.fxml"));
        anpLoad.getChildren().setAll(root);

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), root);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setCycleCount(1);

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), root);
        fadeOut.setFromValue(1);
        fadeOut.setToValue(0);
        fadeOut.setCycleCount(1);

        fadeIn.play();

        fadeIn.setOnFinished(event -> {
            fadeOut.play();
        });
        fadeOut.setOnFinished(event -> {
            try {
                Parent root1 = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/akashStainlessSteel/view/LoginView.fxml"));
                Scene scene = new Scene(root1);
                Stage primaryStage = (Stage) this.anpLoad.getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.centerOnScreen();
                primaryStage.setResizable(false);

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void btnTempLogAdmin_OnAction(ActionEvent actionEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/MainFromDash.fxml");
    }
    public void btnTempLoginWorker_OnAction(ActionEvent actionEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/ManageEstimationView.fxml");
    }

    public void btnRegister_OnAction(ActionEvent actionEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/LoginAdminView.fxml");
    }
}
