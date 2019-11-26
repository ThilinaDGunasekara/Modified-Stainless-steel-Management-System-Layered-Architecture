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
import lk.ijse.dep.akashStainlessSteel.business.custom.FnrCustomerBO;
import lk.ijse.dep.akashStainlessSteel.business.exception.AlreadyExistsInJobException;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;
import lk.ijse.dep.akashStainlessSteel.dto.FnrCustomerDTO;
import lk.ijse.dep.akashStainlessSteel.tm.FnrCustomerTM;
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

public class FNRCustomerController {
    public AnchorPane anpCustomer;
    public AnchorPane anpLoad;
    public ImageView picNewCus_id;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtContactNo;
    public TableView<FnrCustomerTM> tblList;
    public JFXButton btnDelete_Id;
    public JFXButton btnSave;
    public ImageView imgCustomer;
    public ImageView imgPlaceOrder;
    public ImageView imgItem;
    public ImageView imgSearch;
    public ImageView imgWorker;
    public ImageView imageHome;
    public ImageView imgJob;
    public JFXButton btnReport;

    private FnrCustomerBO fnrCustomerBO = BOFactory.getInstance().getBO(BOTypes.FNR_CUSTOMER);

    public void initialize(){
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
        btnReport.setDisable(true);

        try {
            List<FnrCustomerDTO> allFnrCustomers = fnrCustomerBO.findAllFnrCustomers();
            ObservableList<FnrCustomerTM> items = tblList.getItems();
            items.clear();
            for (FnrCustomerDTO allFnrCustomer : allFnrCustomers) {
                items.add(new FnrCustomerTM(
                        allFnrCustomer.getCustomerId(),
                        allFnrCustomer.getName(),
                        allFnrCustomer.getAddress(),
                        allFnrCustomer.getContactNo()
                ));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }
        
        tblList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FnrCustomerTM>() {
            @Override
            public void changed(ObservableValue<? extends FnrCustomerTM> observable, FnrCustomerTM oldValue, FnrCustomerTM newValue) {
                FnrCustomerTM selectedItem = tblList.getSelectionModel().getSelectedItem();
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
        
        btnReport.setDisable(false);

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
        Theme.mouseEnteredImage(mouseEvent);
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        Theme.mouseExitImage(mouseEvent);
    }

    public void manageCustomer_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/CustomerLoginView.fxml");
    }

    public void placeOrder_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/OrderMain.fxml");
    }

    public void picManageItems_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/ItemView.fxml");
    }

    public void picSearch_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/SearchDashView.fxml");
    }

    public void Home_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/MainFromDash.fxml");
    }
    public void picWorker_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/WorkerView.fxml");
    }

    public void picJob_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/JobView.fxml");
    }


    public void picNewCus_OnClick(MouseEvent mouseEvent) {
       reset();

       int maxId =0;
        try {
            String lastFnrCustomerId = fnrCustomerBO.getLastFnrCustomerId();

            if(lastFnrCustomerId==null){
                maxId=0;


            }else {
                maxId = Integer.parseInt(lastFnrCustomerId.replace("FNRC", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "FNRC00" + maxId;
            } else if (maxId < 100) {
                id = "FNRC0" + maxId;
            } else {
                id = "FNRC" + maxId;
            }
            txtId.setText(id);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }
    }
    
    public void btnDelete_OnAction(ActionEvent actionEvent) {
        FnrCustomerTM selectedItem = tblList.getSelectionModel().getSelectedItem();
        String customerId = selectedItem.getCustomerId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this customer?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get()==ButtonType.YES){

            try {
                fnrCustomerBO.deleteFnrCustomer(customerId);
                initialize();
            }catch (AlreadyExistsInJobException e){
                new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }
        }

    }

    public void btnSave_OnAction(ActionEvent actionEvent) {

        ObservableList<FnrCustomerTM> items = tblList.getItems();

        if(btnSave.getText().equals("Save")){
            FnrCustomerDTO fnrCustomerDTO = new FnrCustomerDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtContactNo.getText()
            );

            try {
                fnrCustomerBO.saveFnrCustomer(fnrCustomerDTO);
                items.add(new FnrCustomerTM(
                        fnrCustomerDTO.getCustomerId(),
                        fnrCustomerDTO.getName(),
                        fnrCustomerDTO.getAddress(),
                        fnrCustomerDTO.getContactNo()
                ));
                reset();

            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }

        }else {

            try {
                 fnrCustomerBO.updateFnrCustomer(new FnrCustomerDTO(
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
        btnReport.setDisable(false);
    }


    public void btnReport_OnAction(ActionEvent actionEvent ) throws Exception {
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ijse/dep/akashStainlessSteel/report/customerReportFNR.jasper"));
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
        btnReport.setDisable(false);
    }
}
