package lk.ijse.dep.akashStainlessSteel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.akashStainlessSteel.business.BOFactory;
import lk.ijse.dep.akashStainlessSteel.business.BOTypes;
import lk.ijse.dep.akashStainlessSteel.business.custom.RegisterBO;
import lk.ijse.dep.akashStainlessSteel.dto.RegisterDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginAdminController {
    public AnchorPane anpCustomer;
    public AnchorPane anpLoad;
    public JFXPasswordField password;
    public Label lblMenu;
    public AnchorPane anpLoad1;
    public JFXButton btnLogin1;
    public JFXTextField txtUserName1;
    public JFXPasswordField password1;
    public JFXButton btnTempLogAdmin1;

    RegisterBO registerBO = BOFactory.getInstance().getBO(BOTypes.REGISTER);
    public void txtUserName_OnAction(ActionEvent actionEvent) {
    }

    public void btnLogin_OnAction(ActionEvent actionEvent) {

        String userName = txtUserName1.getText();
        String password = password1.getText();

        try {
            String userType = null;
            String userName1 = null;
            String password2 = null;

            List<RegisterDTO> allRegisters = registerBO.findAllRegisters();
            for (RegisterDTO allRegister : allRegisters) {
                if(allRegister.getUserName().equals(userName) && allRegister.getPassword().equals(password)) {
                    userType = allRegister.getUserType();
                    userName1 = allRegister.getUserName();
                    password2 = allRegister.getPassword();
                }

            }
            if(userType==null || userName1 ==null || password2 ==null){
                new Alert(Alert.AlertType.ERROR,"Incorrect user name, password or userType..").show();
                return;
            }
            if(userType.equals("Admin")){
                show("/lk/ijse/dep/akashStainlessSteel/view/RegisterAccountView.fxml");
            }else{
                new Alert(Alert.AlertType.ERROR,"Invalid login , Your not a admin..").show();
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }

        txtUserName1.clear();
        password1.clear();
    }

    public void password_OnAction(ActionEvent actionEvent) {
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }

    public void btnTempLogAdmin_OnAction(ActionEvent actionEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/RegisterAccountView.fxml");
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
}
