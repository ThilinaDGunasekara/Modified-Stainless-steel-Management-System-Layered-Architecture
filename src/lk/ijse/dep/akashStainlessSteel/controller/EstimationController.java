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
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.akashStainlessSteel.business.BOFactory;
import lk.ijse.dep.akashStainlessSteel.business.BOTypes;
import lk.ijse.dep.akashStainlessSteel.business.custom.*;
import lk.ijse.dep.akashStainlessSteel.dto.*;
import lk.ijse.dep.akashStainlessSteel.tm.EstimationTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class EstimationController {
    public AnchorPane anpCustomer;
    public AnchorPane anpLoad;
    public ImageView picNewCus_id;
    public JFXButton btnItemReport;
    public JFXTextField txtEstimationNo;
    public JFXTextField txtItemName;
    public TableView<EstimationTM> tblList;
    public JFXButton btnDelete;
    public JFXButton btnAdd;
    public JFXComboBox<String> cmbJobId;
    public JFXComboBox<String> cmbWorkerId;
    public JFXComboBox<String> cmbItemCode;
    public JFXTextArea txtAreaDescription;
    public JFXButton btnSave;
    public ImageView imgCustomer;
    public ImageView imgPlaceOrder;
    public ImageView imgItem;
    public ImageView imgSearch;
    public ImageView imgWorker;
    public ImageView imageHome;
    public ImageView imgJob;
    public ImageView picLogOut;
    public JFXTextField txtQuantity;

    private EstimationBO estimationBO = BOFactory.getInstance().getBO(BOTypes.ESTIMATION);
    private CustomJobWhoNotHaveEstimationBO customJobWhoNotHaveEstimationBO = BOFactory.getInstance().getBO(BOTypes.QUERY_JOB_WHO_NOT_HAVE_ESTIMATION);
    private WorkerBO workerBO = BOFactory.getInstance().getBO(BOTypes.WORKER);
    private ItemBO itemBO = BOFactory.getInstance().getBO(BOTypes.ITEM);
    private JobBO jobBO = BOFactory.getInstance().getBO(BOTypes.JOB);
    private EstimationDetailBO estimationDetailBO = BOFactory.getInstance().getBO(BOTypes.ESTIMATION_DETAIL);

    private List<EstimationTM> holdAllItems = new ArrayList<>();

    public void initialize(){
        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("quantity"));

        refresh();

        try {
            List<CustomEstimationDTO> customEstimationDTOS = customJobWhoNotHaveEstimationBO.loadJobsNotHaveEstimation();
            List<String> jobIDs = new ArrayList<>();
            for (CustomEstimationDTO customEstimationDTO : customEstimationDTOS) {
                jobIDs.add(new String(
                        customEstimationDTO.getJobId()
                ));
            }
            cmbJobId.setItems(FXCollections.observableList(jobIDs));

            List<String> allWorkerIDs = workerBO.getAllWorkerIDs();
            cmbWorkerId.setItems(FXCollections.observableList(allWorkerIDs));

            List<String> allItemIDs = itemBO.getAllItemIDs();
            cmbItemCode.setItems(FXCollections.observableList(allItemIDs));


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }


        cmbItemCode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(cmbItemCode.getValue()==null){
                    return;
                }

                String selectedItem = String.valueOf(cmbItemCode.getSelectionModel().getSelectedItem());
                try {
                    ItemDTO item = itemBO.findItem(selectedItem);
                    txtItemName.setText(item.getDescription());
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
                }
            }
        });



        cmbJobId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String selectedJob = String.valueOf(cmbJobId.getSelectionModel().getSelectedItem());

                if(cmbJobId.getValue()==null){
                    return;
                }
                try {
                    JobDTO job = jobBO.findJob(selectedJob);
                    txtAreaDescription.setText(job.getDescription());
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
                }
            }
        });

        tblList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EstimationTM>() {
            @Override
            public void changed(ObservableValue<? extends EstimationTM> observable, EstimationTM oldValue, EstimationTM newValue) {
                EstimationTM selectedItem = tblList.getSelectionModel().getSelectedItem();
                if(selectedItem ==null){
                    return;
                }
            }
        });


        if(!holdAllItems.isEmpty()){
            holdAllItems.clear();
        }
    }
    private void refresh(){
        txtEstimationNo.setDisable(true);
        txtAreaDescription.setDisable(true);
        txtItemName.setDisable(true);
        txtQuantity.setDisable(true);

        txtAreaDescription.clear();
        txtItemName.clear();
        txtEstimationNo.clear();
        txtQuantity.clear();

        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
        btnSave.setDisable(true);

        cmbItemCode.setDisable(true);
        cmbJobId.setDisable(true);
        cmbWorkerId.setDisable(true);
    }

    public void picNewCus_OnClick(MouseEvent mouseEvent) {

        txtEstimationNo.setDisable(false);
        txtAreaDescription.setDisable(false);
        txtItemName.setDisable(false);
        txtQuantity.setDisable(false);
        tblList.getItems().clear();

        txtAreaDescription.clear();
        txtItemName.clear();
        txtEstimationNo.clear();
        txtQuantity.clear();
        cmbItemCode.getSelectionModel().clearSelection();
        cmbJobId.getSelectionModel().clearSelection();
        cmbWorkerId.getSelectionModel().clearSelection();

        btnAdd.setDisable(false);
        btnDelete.setDisable(false);
        btnSave.setDisable(true);

        cmbItemCode.setDisable(false);
        cmbJobId.setDisable(false);
        cmbWorkerId.setDisable(false);


        int maxId =0;
        try {
            String lastEstimationId = estimationBO.getLastEstimationId();

            if(lastEstimationId==null){
                maxId=0;


            }else {
                maxId = parseInt(lastEstimationId.replace("E", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "E00" + maxId;
            } else if (maxId < 100) {
                id = "E0" + maxId;
            } else {
                id = "E" + maxId;
            }
            txtEstimationNo.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }
    }
    public void btnDelete_OnAction(ActionEvent actionEvent) {
        EstimationTM selectedItem = tblList.getSelectionModel().getSelectedItem();

        if(selectedItem==null){
            new Alert(Alert.AlertType.CONFIRMATION,"First select the table item...").show();
            return;
        }

            tblList.getItems().remove(selectedItem.getItemCode());
        for (int i = 0; i <holdAllItems.size(); i++) {
            if(selectedItem.getItemCode().equals(holdAllItems.get(i).getItemCode())){
                holdAllItems.remove(i);
            }
        }

        ObservableList<EstimationTM> items = tblList.getItems();
        items.clear();
        for (EstimationTM holdAllItem : holdAllItems) {
            items.add(new EstimationTM(
                    holdAllItem.getItemCode(),
                    holdAllItem.getDescription(),
                    holdAllItem.getQuantity()
            ));
        }

    }
    public void btnAdd_OnAction(ActionEvent actionEvent) {

        if(txtItemName.getText().equals("")
                ||txtQuantity.getText().equals("")
                ||txtAreaDescription.getText().equals("")
                ||cmbItemCode.getValue().equals(null)
                ||cmbWorkerId.getValue().equals(null)
                ||cmbJobId.getValue().equals(null)){

            new Alert(Alert.AlertType.CONFIRMATION,"Check whether if all fields have been completed..").show();
            return;
        }

        String selectedItem = String.valueOf(cmbItemCode.getSelectionModel().getSelectedItem());
        ObservableList<EstimationTM> items = tblList.getItems();
        List<EstimationTM> holdItems = new ArrayList<>();

        if(items!= null){
            for (EstimationTM item : items) {

                holdItems.add(new EstimationTM(
                        item.getItemCode(),
                        item.getDescription(),
                        item.getQuantity()
                ));
            }
            items.clear();
        }
        holdItems.add(new EstimationTM(
                selectedItem,
                txtItemName.getText(),
                Integer.parseInt(txtQuantity.getText())
        ));

        holdAllItems.clear();

        for (EstimationTM holdItem : holdItems) {
            holdAllItems.add(new EstimationTM(
                    holdItem.getItemCode(),
                    holdItem.getDescription(),
                    holdItem.getQuantity()
            ));
        }

        for (EstimationTM holdItem : holdItems) {
            items.add(new EstimationTM(
                    holdItem.getItemCode(),
                    holdItem.getDescription(),
                    holdItem.getQuantity()
            ));
        }

        tblList.refresh();
        txtQuantity.clear();
        txtItemName.clear();
        btnSave.setDisable(false);

        cmbItemCode.getSelectionModel().clearSelection();


        txtEstimationNo.setDisable(true);
        txtAreaDescription.setDisable(true);
        cmbJobId.setDisable(true);
        cmbWorkerId.setDisable(true);

    }

    private double estimationPrice = 0;
    public void btnSave_OnAction(ActionEvent actionEvent) {
        if(txtItemName.getText().equals("")
                ||txtQuantity.getText().equals("")
                ||txtAreaDescription.getText().equals("")
                ||cmbItemCode.getValue().equals(null)
                ||cmbWorkerId.getValue().equals(null)
                ||cmbJobId.getValue().equals(null)){

            new Alert(Alert.AlertType.CONFIRMATION,"Check whether if all fields have been completed..").show();
        }



        try {
            for (EstimationTM holdAllItem : holdAllItems) {
                double unitPrice = (itemBO.findItem(holdAllItem.getItemCode())).getUnitPrice();
                int quantity = holdAllItem.getQuantity();

                double rowPrice = unitPrice*quantity;

                estimationPrice += rowPrice;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(estimationPrice);

        String selectedJobId = String.valueOf(cmbJobId.getSelectionModel().getSelectedItem());
        String workerId = String.valueOf(cmbWorkerId.getSelectionModel().getSelectedItem());

        try {
            estimationBO.saveEstimation(
                    new EstimationDTO(
                            txtEstimationNo.getText(),
                            selectedJobId,
                            workerId,
                            estimationPrice
                    ));
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
        }
        try {
            for (EstimationTM holdAllItem : holdAllItems) {
                estimationDetailBO.saveEstimationDetail(new EstimationDetailDTO(
                        txtEstimationNo.getText(),
                        holdAllItem.getItemCode(),
                        holdAllItem.getQuantity(),
                        (itemBO.findItem(holdAllItem.getItemCode())).getUnitPrice()
                ));
            }

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }

        refresh();
        holdAllItems.clear();
        System.out.println(holdAllItems);
        tblList.getItems().clear();
        estimationPrice=0;
    }



    public void picSearch_OnClick(MouseEvent mouseEvent) {
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
    public void picLogOut_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/LoginView.fxml");
    }
}
