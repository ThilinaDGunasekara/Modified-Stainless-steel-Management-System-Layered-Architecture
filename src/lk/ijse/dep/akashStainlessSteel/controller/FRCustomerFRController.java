package lk.ijse.dep.akashStainlessSteel.controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.akashStainlessSteel.business.BOFactory;
import lk.ijse.dep.akashStainlessSteel.business.BOTypes;
import lk.ijse.dep.akashStainlessSteel.business.custom.FrCustomerBO;
import lk.ijse.dep.akashStainlessSteel.business.exception.AlreadyExistsInOrderException;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;
import lk.ijse.dep.akashStainlessSteel.dto.FrCustomerDTO;
import lk.ijse.dep.akashStainlessSteel.tm.FrCustomerTM;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FRCustomerFRController {

    public ImageView picNewCus_id;
    public JFXTextField txtContactNo;
    public JFXButton btnDelete_Id;
    public JFXButton btnSave;
    public ImageView imageHome;
    public ImageView imgWorker;
    public ImageView imgSearch;
    public ImageView imgItem;
    public ImageView imgPlaceOrder;
    public ImageView imgCustomer;
    public TableView<FrCustomerTM> tblList;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXTextField txtId;
    public JFXButton btnItemReport;
    public AnchorPane anpCustomer;
    public AnchorPane anpLoad;
    public ImageView imgJob;
    public JFXButton btnReport;

    private FrCustomerBO frCustomerBO = BOFactory.getInstance().getBO(BOTypes.FR_CUSTOMER);

    public void initialize() {
        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNo"));

        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        btnSave.setDisable(true);
        btnDelete_Id.setDisable(true);
        btnReport.setDisable(false);

        loadToTable();

        tblList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FrCustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends FrCustomerTM> observable, FrCustomerTM oldValue, FrCustomerTM newValue) {
                FrCustomerTM selectedItem = tblList.getSelectionModel().getSelectedItem();
                if(selectedItem==null){
                    btnSave.setText("Save");
                    btnDelete_Id.setDisable(true);
                    btnReport.setDisable(true);
                    return;
                }

                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete_Id.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtContactNo.setDisable(false);
                txtId.setText(selectedItem.getCustomerId());
                txtName.setText(selectedItem.getName());
                txtAddress.setText(selectedItem.getAddress());
                txtContactNo.setText(selectedItem.getContactNo());

            }
        });
    }

    public void loadToTable(){
        try {
            List<FrCustomerDTO> allFrCustomers = frCustomerBO.findAllFrCustomers();
            ObservableList<FrCustomerTM> items = tblList.getItems();
            items.clear();
            for (FrCustomerDTO allFrCustomer : allFrCustomers) {
                items.add(new FrCustomerTM(
                        allFrCustomer.getCustomerId(),
                        allFrCustomer.getName(),
                        allFrCustomer.getAddress(),
                        allFrCustomer.getContactNo()
                ));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }
    }

    public void picNewCus_OnClick(MouseEvent mouseEvent) {

        reset();
        txtId.setDisable(true);

        int maxId =0;
        try {
            String lastFrCustomerId = frCustomerBO.getLastFrCustomerId();

            if(lastFrCustomerId==null){
                maxId=0;
            }else {
                maxId = Integer.parseInt(lastFrCustomerId.replace("FRC", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "FRC00" + maxId;
            } else if (maxId < 100) {
                id = "FRC0" + maxId;
            } else {
                id = "FRC" + maxId;
            }
            txtId.setText(id);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }

        btnSave.setText("Save");

    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {





        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this customer?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get()==ButtonType.YES){


            try {
                FrCustomerTM selectedItem = tblList.getSelectionModel().getSelectedItem();
                String customerId = selectedItem.getCustomerId();
                frCustomerBO.deleteFrCustomer(customerId);
                tblList.getItems().remove(customerId);
                loadToTable();
            }catch (AlreadyExistsInOrderException e){
                new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            }
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        ObservableList<FrCustomerTM> items = tblList.getItems();

        if(btnSave.getText().equals("Save")){
            FrCustomerDTO frCustomerDTO = new FrCustomerDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtContactNo.getText()
            );

            try {
                frCustomerBO.saveFrCustomer(frCustomerDTO);
                items.add(new FrCustomerTM(
                        frCustomerDTO.getCustomerId(),
                        frCustomerDTO.getName(),
                        frCustomerDTO.getAddress(),
                        frCustomerDTO.getContactNo()
                ));
                reset();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }

        }else {

            try {
                frCustomerBO.updateFrCustomer(new FrCustomerDTO(
                        txtId.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        txtContactNo.getText()
                ));
                initialize();
                tblList.refresh();
                reset();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }
        }
        reset();
        btnSave.setText("Save");
        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        btnSave.setDisable(true);
        btnDelete_Id.setDisable(true);
        btnReport.setDisable(true);
    }



    public void btnReport_OnAction(ActionEvent actionEvent) throws Exception {

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ijse/dep/akashStainlessSteel/report/customerReportFR.jasper"));
        Map<String,Object> params = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }
    public void reset() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
        txtName.requestFocus();
        txtId.setDisable(false);
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtContactNo.setDisable(false);
        btnSave.setDisable(false);
        btnDelete_Id.setDisable(true);
        btnReport.setDisable(true);
    }




    void show(String value) throws IOException {
        URL resource = this.getClass().getResource(value);
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.anpLoad.getScene().getWindow());
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