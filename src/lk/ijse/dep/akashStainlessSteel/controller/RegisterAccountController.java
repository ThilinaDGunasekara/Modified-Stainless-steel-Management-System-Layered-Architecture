package lk.ijse.dep.akashStainlessSteel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.akashStainlessSteel.business.BOFactory;
import lk.ijse.dep.akashStainlessSteel.business.BOTypes;
import lk.ijse.dep.akashStainlessSteel.business.custom.RegisterBO;
import lk.ijse.dep.akashStainlessSteel.dto.RegisterDTO;
import lk.ijse.dep.akashStainlessSteel.tm.RegisterTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterAccountController {
    public AnchorPane anpCustomer;
    public AnchorPane anpLoad;
    public JFXTextField txtUserName;
    public JFXButton btnRegister;
    public JFXButton btnDelete;
    public TableView<RegisterTM> tblList;
    public ImageView imgCustomer;
    public ImageView imgPlaceOrder;
    public ImageView imgItem;
    public ImageView imgSearch;
    public ImageView imgWorker;
    public ImageView imageHome;
    public ImageView imgJob;
    public ImageView picNewRegisterAccount;
    public JFXRadioButton rbtnAdmin;
    public JFXRadioButton rbtnWorker;
    public JFXTextField txtPassword1;

    RegisterBO registerBO = BOFactory.getInstance().getBO(BOTypes.REGISTER);

    public void initialize(){
        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("userType"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("userName"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("password"));

        loadTable();

        tblList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RegisterTM>() {
            @Override
            public void changed(ObservableValue<? extends RegisterTM> observable, RegisterTM oldValue, RegisterTM newValue) {
                RegisterTM selectedItem = tblList.getSelectionModel().getSelectedItem();
                if(selectedItem==null){
                    return;
                }

                String userName = selectedItem.getUserName();
                String password = selectedItem.getPassword();
                String userType = selectedItem.getUserType();

                if(userType.equals("Admin")){
                    rbtnAdmin.setSelected(true);
                    rbtnWorker.setSelected(false);
                }else {
                    rbtnWorker.setSelected(true);
                    rbtnAdmin.setSelected(false);
                }
                txtUserName.setText(userName);
                txtPassword1.setText(password);

                btnRegister.setText("Update Register");
                btnDelete.setDisable(false);
                btnRegister.setDisable(false);
            }
        });


        txtUserName.setDisable(true);
        txtPassword1.setDisable(true);
        btnRegister.setDisable(true);
        btnDelete.setDisable(true);
        tblList.setDisable(true);
        rbtnAdmin.setDisable(true);
        rbtnWorker.setDisable(true);
        picNewRegisterAccount.setDisable(false);
    }
    public void loadTable(){
        try {
            List<RegisterDTO> allRegisters = registerBO.findAllRegisters();
            ObservableList<RegisterTM> items = tblList.getItems();
            items.clear();
            for (RegisterDTO allRegister : allRegisters) {
                items.add(new RegisterTM(
                        allRegister.getUserType(),
                        allRegister.getUserName(),
                        allRegister.getPassword()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void btnRegister_OnAction(ActionEvent actionEvent) {
        if(txtPassword1.getText().equals("")
                        || txtUserName.getText().equals("")){
            new Alert(Alert.AlertType.CONFIRMATION,"Check whether if all fields have been completed..").show();
            return;
        }

        String type = null;
        if(btnRegister.getText().equals("Register")) {

            if (rbtnAdmin.isSelected()) {
                type = rbtnAdmin.getText();
            } else {
                type = rbtnWorker.getText();
            }

            String userName = txtUserName.getText();
            String password = txtPassword1.getText();
            try {
                boolean b = registerBO.saveRegister(new RegisterDTO(
                        type,
                        userName,
                        password
                ));
                loadTable();

                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Registered....").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }
        }else {
            RegisterTM selectedItem = tblList.getSelectionModel().getSelectedItem();
            if (rbtnAdmin.isSelected()) {
                type = rbtnAdmin.getText();
            } else {
                type = rbtnWorker.getText();
            }

            try {
                boolean b = registerBO.updateRegister(new RegisterDTO(
                        type,
                        txtUserName.getText(),
                        txtPassword1.getText()
                ));
                loadTable();
                if (b) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Successfully Registered....").show();
                }
            } catch (Exception e) {
                e.printStackTrace();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }
            btnRegister.setText("Register");
        }

        txtUserName.clear();
        txtPassword1.clear();
        rbtnWorker.setSelected(false);
        rbtnAdmin.setSelected(false);
        btnRegister.setDisable(true);
    }

    public void txtUserName_OnAction(ActionEvent actionEvent) {
    }
    public void btnDelete_OnAction(ActionEvent actionEvent) {
        RegisterTM selectedItem = tblList.getSelectionModel().getSelectedItem();
        String userName = selectedItem.getUserName();
        try {
            boolean b = registerBO.deleteRegister(userName);
            loadTable();
            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Deleted....").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }
    }

    public void picNewRegisterAccount_OnClick(MouseEvent mouseEvent) {

        txtUserName.setDisable(false);
        txtPassword1.setDisable(false);
        btnRegister.setDisable(true);
        btnDelete.setDisable(true);
        tblList.setDisable(false);
        rbtnAdmin.setDisable(false);
        rbtnWorker.setDisable(false);
        picNewRegisterAccount.setDisable(false);

        txtUserName.clear();
        txtPassword1.clear();
        rbtnWorker.setSelected(false);
        rbtnAdmin.setSelected(false);

    }
    public void rbtnAdmin_OnAction(ActionEvent actionEvent) {
        btnRegister.setDisable(false);
        rbtnWorker.setSelected(false);
    }
    public void rbtnWorker_OnAction(ActionEvent actionEvent) {
        btnRegister.setDisable(false);
        rbtnAdmin.setSelected(false);
    }
    public void txtPassword_OnAction(ActionEvent actionEvent) {
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
    public void picSearch_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/SearchDashView.fxml");
    }
    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        Theme.mouseEnteredImage(mouseEvent);
    }
    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        Theme.mouseExitImage(mouseEvent);
    }
    public void manageCustomer_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/CustomerLoginView.fxml");
    }
    public void picManageItems_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/ItemView.fxml");
    }
    public void Home_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/MainFromDash.fxml");
    }
    public void picWorker_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/WorkerView.fxml");
    }
    public void placeOrder_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/OrderMain.fxml");
    }
    public void picJob_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/JobView.fxml");
    }
}
