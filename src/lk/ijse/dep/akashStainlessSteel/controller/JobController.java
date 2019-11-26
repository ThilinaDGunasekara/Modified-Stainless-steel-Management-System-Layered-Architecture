package lk.ijse.dep.akashStainlessSteel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
import lk.ijse.dep.akashStainlessSteel.business.custom.CustomJobBO;
import lk.ijse.dep.akashStainlessSteel.business.custom.FnrCustomerBO;
import lk.ijse.dep.akashStainlessSteel.business.custom.JobBO;
import lk.ijse.dep.akashStainlessSteel.business.exception.AlreadyExistsInOrderAndEstimationException;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;
import lk.ijse.dep.akashStainlessSteel.dto.CustomJobDTO;
import lk.ijse.dep.akashStainlessSteel.dto.FnrCustomerDTO;
import lk.ijse.dep.akashStainlessSteel.dto.JobDTO;
import lk.ijse.dep.akashStainlessSteel.tm.JobTM;
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

public class JobController {
    public AnchorPane anpCustomer;
    public AnchorPane anpLoad;
    public ImageView picNewJob;
    public JFXButton btnItemReport;
    public JFXTextField txtJobId;
    public JFXTextField txtName;
    public TableView<JobTM> tblList;
    public JFXButton btnDelete_Id;
    public JFXButton btnSave;
    public JFXTextField txtCustomerId;
    public JFXTextArea jobDescription;
    public ImageView imgCustomer;
    public ImageView imgPlaceOrder;
    public ImageView imgItem;
    public ImageView imgSearch;
    public ImageView imgWorker;
    public ImageView imageHome;
    public ImageView imgJob;
    public JFXComboBox cmbCustomerId;
    public JFXTextArea txtDescription;


    JobBO jobBO = BOFactory.getInstance().getBO(BOTypes.JOB);
    FnrCustomerBO fnrCustomerBO = BOFactory.getInstance().getBO(BOTypes.FNR_CUSTOMER);
    CustomJobBO customJobBO = BOFactory.getInstance().getBO(BOTypes.QUERY_JOB);

    public void initialize(){

        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("jobId"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("description"));

        disableTrue();

        try {
            List<String> allFnrCustomerIDs = fnrCustomerBO.getAllFnrCustomerIDs();
            cmbCustomerId.setItems(FXCollections.observableList(allFnrCustomerIDs));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }

        try {

            List<CustomJobDTO> customJobDTOS = customJobBO.loadToJob();
            ObservableList<JobTM> items = tblList.getItems();
            items.clear();
            for (CustomJobDTO customJobDTO : customJobDTOS) {
                items.add(new JobTM(
                        customJobDTO.getJobId(),
                        customJobDTO.getCustomerId(),
                        customJobDTO.getName(),
                        customJobDTO.getDescription()
                ));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                if(cmbCustomerId.getValue()==null){
                    return;
                }
                String selectedCusId = String.valueOf(cmbCustomerId.getSelectionModel().getSelectedItem());

                txtName.setText("");

                try {
                    FnrCustomerDTO fnrCustomer = fnrCustomerBO.findFnrCustomer(selectedCusId);
                    txtName.setText(fnrCustomer.getName());
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
                    Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
                }
            }
        });

        tblList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<JobTM>() {
            @Override
            public void changed(ObservableValue<? extends JobTM> observable, JobTM oldValue, JobTM newValue) {

                JobTM selectedItem = tblList.getSelectionModel().getSelectedItem();
                btnSave.setText("Update");
                if(selectedItem==null){
                    return;
                }

                disableFalse();
                btnSave.setDisable(false);
                btnDelete_Id.setDisable(false);


                cmbCustomerId.setId(selectedItem.getJobId());
                txtJobId.setText(selectedItem.getJobId());
                txtDescription.setText(selectedItem.getDescription());
                txtName.setText(selectedItem.getName());
            }
        });
    }


    public void picNewCus_OnClick(MouseEvent mouseEvent) {
        disableFalse();
        btnSave.setDisable(false);
        btnItemReport.setDisable(false);


        int maxId =0;
        try {
            String lastJobId = jobBO.getLastJobId();

            txtJobId.clear();
            txtDescription.clear();
            txtName.clear();

            if(lastJobId==null){
                maxId=0;


            }else {
                maxId = Integer.parseInt(lastJobId.replace("J", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "J00" + maxId;
            } else if (maxId < 100) {
                id = "J0" + maxId;
            } else {
                id = "J" + maxId;
            }
            txtJobId.setText(id);
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }
    }

    public void disableFalse(){
        cmbCustomerId.setDisable(false);
        txtDescription.setDisable(false);
        txtJobId.setDisable(false);
        txtName.setDisable(false);
    }
    void disableTrue(){
        txtJobId.setDisable(true);
        cmbCustomerId.setDisable(true);
        txtName.setDisable(true);
        txtDescription.setDisable(true);

        btnDelete_Id.setDisable(true);
        btnItemReport.setDisable(true);
        btnSave.setDisable(true);
    }
    public void btnItemReport_OnAction(ActionEvent actionEvent) throws Exception {
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ijse/dep/akashStainlessSteel/report/jobReport.jasper"));
        Map<String,Object> params = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);

    }
    public void btnDelete_OnAction(ActionEvent actionEvent) {
        JobTM selectedItem = tblList.getSelectionModel().getSelectedItem();
        String jobId = selectedItem.getJobId();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this customer?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get()==ButtonType.YES){
            try {
                jobBO.deleteJob(jobId);
                initialize();
            }catch (AlreadyExistsInOrderAndEstimationException e){
                new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }
        }
    }
    public void btnSave_OnAction(ActionEvent actionEvent) {
        ObservableList<JobTM> jobTMS = tblList.getItems();
        String selectedItem = String.valueOf(cmbCustomerId.getSelectionModel().getSelectedItem());
        if(btnSave.getText().equals("Save")) {
            JobDTO jobDTO = new JobDTO(
                    txtJobId.getText(),
                    selectedItem,
                    txtDescription.getText()
            );

            JobTM jobTM = new JobTM(
                    txtJobId.getText(),
                    selectedItem,
                    txtName.getText(),
                    txtDescription.getText());

            try {
                jobBO.saveJob(jobDTO);
                jobTMS.add(jobTM);
                initialize();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, Please contact Mr.Thilina").show();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }
        }else{

            try {
                jobBO.updateJob(new JobDTO(
                        txtJobId.getText(),
                        selectedItem,
                        txtDescription.getText()
                ));
                btnSave.setText("Save");
                initialize();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, Please contact Mr.Thilina").show();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }
        }

        txtJobId.clear();
        txtDescription.clear();
        txtName.clear();
    }
    public void cmbCustomerId_OnAction(ActionEvent actionEvent) {

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
