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
import lk.ijse.dep.akashStainlessSteel.business.custom.WorkerBO;
import lk.ijse.dep.akashStainlessSteel.business.exception.AlreadyExistsInWorkerException;
import lk.ijse.dep.akashStainlessSteel.dto.WorkerDTO;
import lk.ijse.dep.akashStainlessSteel.tm.WorkerTM;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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

import static java.lang.Integer.parseInt;

public class WorkerController {
    public ImageView imageHome;
    public ImageView imgWorker;
    public ImageView imgSearch;
    public ImageView imgItem;
    public ImageView imgPlaceOrder;
    public ImageView imgCustomer;
    public JFXButton btnSave;
    public JFXButton btnDelete_Id;
    public TableView<WorkerTM> tblList;
    public JFXTextField txtContactNo;
    public JFXTextField txtAddress;
    public JFXTextField txtName;
    public JFXTextField txtId;
    public JFXButton btnItemReport;
    public ImageView picNewCus_id;
    public AnchorPane anpLoad;
    public AnchorPane anpCustomer;
    public ImageView imgJob;
    public ImageView picNewWorker;
    public JFXTextField txtSalary;
    
    WorkerBO workerBO = BOFactory.getInstance().getBO(BOTypes.WORKER);

    public void initialize(){
        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("workerId"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        tblList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("salary"));

        txtId.setDisable(true);
        txtName.setDisable(true);
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        btnSave.setDisable(true);
        btnDelete_Id.setDisable(true);
        btnItemReport.setDisable(true);
        txtSalary.setDisable(true);

        try {
            List<WorkerDTO> allWorker = workerBO.findAllWorkers();
            ObservableList<WorkerTM> items = tblList.getItems();
            items.clear();
            for (WorkerDTO workerDTO : allWorker) {
                items.add(new WorkerTM(
                        workerDTO.getWorkerId(),
                        workerDTO.getName(),
                        workerDTO.getAddress(),
                        workerDTO.getContactNo(),
                        workerDTO.getSalary()
                ));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }

        tblList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WorkerTM>() {
            @Override
            public void changed(ObservableValue<? extends WorkerTM> observable, WorkerTM oldValue, WorkerTM newValue) {
                WorkerTM selectedItem = tblList.getSelectionModel().getSelectedItem();
                if(selectedItem==null){
                    btnSave.setText("Save");
                    btnDelete_Id.setDisable(true);
                    btnItemReport.setDisable(true);
                    return;
                }

                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete_Id.setDisable(false);
                txtName.setDisable(false);
                txtAddress.setDisable(false);
                txtContactNo.setDisable(false);
                txtSalary.setDisable(false);
                txtId.setText(selectedItem.getWorkerId());
                txtName.setText(selectedItem.getName());
                txtAddress.setText(selectedItem.getAddress());
                txtContactNo.setText(String.valueOf(selectedItem.getContactNo()));
                txtSalary.setText(String.valueOf(selectedItem.getSalary()));

            }
        });
    }


    public void picNewCus_OnClick(MouseEvent mouseEvent) {
    }

    public void btnItemReport_OnAction(ActionEvent actionEvent) throws Exception {
        ObservableList<WorkerTM> customers = tblList.getItems();
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ijse/dep/akashStainlessSteel/report/workerReport.jasper"));
        Map<String,Object> params = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(customers));
        JasperViewer.viewReport(jasperPrint,false);
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {

        WorkerTM workerTM = tblList.getSelectionModel().getSelectedItem();
        String workerId = workerTM.getWorkerId();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this customer?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get()==ButtonType.YES){

            try {
                workerBO.deleteWorker(workerId);
                initialize();
            }catch (AlreadyExistsInWorkerException e){
                new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            }
        }

    }

    public void btnSave_OnAction(ActionEvent actionEvent) {

        ObservableList<WorkerTM> items = tblList.getItems();

        if(btnSave.getText().equals("Save")){
            WorkerDTO workerDTO = new WorkerDTO(
                    txtId.getText(),
                    txtName.getText(),
                    txtAddress.getText(),
                    Integer.parseInt(txtContactNo.getText()),
                    Double.parseDouble(txtSalary.getText()
                    ));

            try {
                workerBO.saveWorker(workerDTO);
                items.add(new WorkerTM(
                        workerDTO.getWorkerId(),
                        workerDTO.getName(),
                        workerDTO.getAddress(),
                        workerDTO.getContactNo(),
                        workerDTO.getSalary()
                ));
                btnItemReport.setDisable(false);
                clear();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
                e.printStackTrace();
            }

        }else {

            try {
                workerBO.updateWorker(new WorkerDTO(
                        txtId.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        parseInt(txtContactNo.getText()),
                        Double.parseDouble(txtSalary.getText()
                )));
                initialize();
                tblList.refresh();
                clear();

            } catch (Exception e) {
                /*new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();*/
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
                e.printStackTrace();
            }
        }
        btnSave.setText("Save");
        txtId.clear();
        txtSalary.clear();
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
    }


    public void picNewWorker_OnClick(MouseEvent mouseEvent) {

        int maxId =0;
        try {
            String lastWorkerId = workerBO.getLastWorkerId();

            if(lastWorkerId==null){
                maxId=0;


            }else {
                maxId = parseInt(lastWorkerId.replace("W", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "W00" + maxId;
            } else if (maxId < 100) {
                id = "W0" + maxId;
            } else {
                id = "W" + maxId;
            }
            txtId.setText(id);
            setDisableFalse();
            btnSave.setDisable(false);
            btnItemReport.setDisable(false);
            btnDelete_Id.setDisable(false);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }

        btnSave.setText("Save");
        txtId.clear();
        txtSalary.clear();
        txtName.clear();
        txtAddress.clear();
        txtContactNo.clear();
    }
    public void setDisableFalse(){
        txtAddress.setDisable(false);
        txtContactNo.setDisable(false);
        txtName.setDisable(false);
        txtSalary.setDisable(false);
    }
    public void setDisableTrue(){
        txtAddress.setDisable(true);
        txtContactNo.setDisable(true);
        txtName.setDisable(true);
        txtSalary.setDisable(true);
    }
    public void clear(){
        txtContactNo.clear();
        txtAddress.clear();
        txtName.clear();
        txtSalary.clear();
        txtId.clear();
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
    public void picSearch_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/SearchDashView.fxml");
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
