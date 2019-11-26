package lk.ijse.dep.akashStainlessSteel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.akashStainlessSteel.business.BOFactory;
import lk.ijse.dep.akashStainlessSteel.business.BOTypes;
import lk.ijse.dep.akashStainlessSteel.business.custom.*;
import lk.ijse.dep.akashStainlessSteel.dto.*;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class PlaceOrderServiceController {
    public ImageView imgWorker;
    public ImageView imgSearch;
    public ImageView imgItem;
    public ImageView imgPlaceOrder;
    public ImageView imgCustomer;
    public Label lblDate;
    public JFXTextField txtName;
    public JFXTextField txtOrderId;
    public JFXButton btnNewOrder;
    public AnchorPane anpLoad;
    public AnchorPane anpCustomer;
    public JFXComboBox<String> cmbCustomerId;
    public ImageView imageHome;
    public JFXButton btnPlaceOrder;
    public ImageView imgJob;
    public JFXComboBox<String> cmbJobId;
    public JFXTextField txtEstimatedItemPrice;
    public JFXTextField txtAdditionalPrice;
    public JFXTextField txtFullPayment;
    public JFXTextField txtAdvancePayment;
    public JFXTextField txtRemainingPayment;
    public JFXButton btnUpdateOrder;
    public JFXComboBox <String>cmbOrderId;
    public JFXTextField txtCustomer;

    FnrOrderBO fnrOrderBO = BOFactory.getInstance().getBO(BOTypes.FNR_ORDER);
    JobBO jobBO = BOFactory.getInstance().getBO(BOTypes.JOB);
    CustomFnrOrderBO customFnrOrderBO = BOFactory.getInstance().getBO(BOTypes.QUERY_FNR_ORDER);
    EstimationDetailBO estimationDetailBO = BOFactory.getInstance().getBO(BOTypes.ESTIMATION_DETAIL);
    EstimationBO estimationBO = BOFactory.getInstance().getBO(BOTypes.ESTIMATION);
    ItemBO itemBO = BOFactory.getInstance().getBO(BOTypes.ITEM);

    public void initialize(){

       /* SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        lblDate.setText(formatter.format(date));*/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        lblDate.setText(formatter.format(sqlDate));



        try {


            List<CustomFnrOrderDTO> customFnrOrderDTOS = customFnrOrderBO.loadAllDataToFnrOrder();
            List<String> jobIDs = new ArrayList<>();
            for (CustomFnrOrderDTO customFnrOrderDTO : customFnrOrderDTOS) {
                jobIDs.add(new String(customFnrOrderDTO.getJobId()));
            }
            cmbJobId.setItems(FXCollections.observableArrayList(jobIDs));

            List<String> allFnrOrderIDs = fnrOrderBO.getAllFnrOrderIDs();
            cmbOrderId.setItems(FXCollections.observableList(allFnrOrderIDs));


        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }

        cmbJobId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(cmbJobId.getValue()==null){
                    return;
                }

                try {
                    String jobId = cmbJobId.getSelectionModel().getSelectedItem();
                    List<CustomFnrOrderDTO> customFnrOrderDTOS = customFnrOrderBO.loadAllDataToFnrOrder();
                    for (CustomFnrOrderDTO customFnrOrderDTO : customFnrOrderDTOS) {
                        if(customFnrOrderDTO.getJobId().equals(jobId)){
                            txtName.setText(customFnrOrderDTO.getName());
                            txtEstimatedItemPrice.setText(customFnrOrderDTO.getPrice()+"0");
                            txtCustomer.setText(customFnrOrderDTO.getCustomerId());
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
                }
                cmbJobId.setDisable(true);
            }
        });


        cmbOrderId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(cmbOrderId.getValue()==null){
                    return;
                }

                String fnrOrderId = cmbOrderId.getValue();
                try {
                    FnrOrderDTO fnrOrder = fnrOrderBO.findFnrOrder(fnrOrderId);
                    txtOrderId.setText(fnrOrderId);
                    lblDate.setText(fnrOrder.getDate()+"");

                    List<CustomFnrOrderDTO> customFnrOrderDTOS = customFnrOrderBO.loadAllDataToFnrOrder();
                    for (CustomFnrOrderDTO customFnrOrderDTO : customFnrOrderDTOS) {
                        if(customFnrOrderDTO.getJobId().equals(fnrOrder.getJobId())){
                            String name = customFnrOrderDTO.getName();
                            txtName.setText(name);
                            double price = customFnrOrderDTO.getPrice();
                            txtEstimatedItemPrice.setText(price+"0");
                        }
                    }
                    txtFullPayment.setText(fnrOrder.getPrice()+"0");
                    txtAdvancePayment.setText(fnrOrder.getAdvance()+"0");
                } catch (Exception e) {
                    e.printStackTrace();
                    Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
                }
                txtAdvancePayment.setDisable(false);
                txtFullPayment.setDisable(false);
                txtRemainingPayment.setDisable(false);
                txtAdvancePayment.selectAll();

            }
        });

        txtAdditionalPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(txtAdditionalPrice.getText()==null){
                    return;
                }

                double additionalPrice = Double.parseDouble(txtAdditionalPrice.getText());
                double estimatedItemPrice = Double.parseDouble(txtEstimatedItemPrice.getText());

                double fullPayment = additionalPrice + estimatedItemPrice;

                txtFullPayment.setText(fullPayment+"0");

            }
        });

        txtAdvancePayment.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if(txtAdvancePayment.getText() == null){
                    return;
                }

                double fullPayment = Double.parseDouble(txtFullPayment.getText());
                double advancePayment = Double.parseDouble(txtAdvancePayment.getText());

                double remainingPayment = fullPayment-advancePayment;

                txtRemainingPayment.setText(remainingPayment+"0");
            }
        });

        txtOrderId.setDisable(true);
        btnUpdateOrder.setDisable(false);
        btnPlaceOrder.setDisable(true);
        txtCustomer.setDisable(true);
        txtName.setDisable(true);
        txtEstimatedItemPrice.setDisable(true);
        txtAdditionalPrice.setDisable(true);
        txtAdvancePayment.setDisable(true);
        txtFullPayment.setDisable(true);
        txtRemainingPayment.setDisable(true);
        cmbOrderId.setDisable(false);
        lblDate.setDisable(true);
        cmbJobId.setDisable(true);
        cmbOrderId.setDisable(true);

    }

    public void btnNewOrder_OnAction(ActionEvent actionEvent) {

        int maxId =0;
        try {
            String lastEstimationId = fnrOrderBO.getLastFnrOrderId();

            if(lastEstimationId==null){
                maxId=0;
            }else {
                maxId = parseInt(lastEstimationId.replace("FNROI", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "FNROI00" + maxId;
            } else if (maxId < 100) {
                id = "FNROI0" + maxId;
            } else {
                id = "FNROI" + maxId;
            }
            txtOrderId.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }
        btnUpdateOrder.setDisable(true);
        btnPlaceOrder.setDisable(false);
        txtCustomer.setDisable(false);
        txtName.setDisable(false);
        txtEstimatedItemPrice.setDisable(false);
        txtAdditionalPrice.setDisable(false);
        txtAdvancePayment.setDisable(false);
        txtFullPayment.setDisable(false);
        txtFullPayment.setDisable(false);
        txtRemainingPayment.setDisable(false);
        cmbOrderId.setDisable(true);
        lblDate.setDisable(false);
        cmbJobId.setDisable(false);
        reset();

    }

    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) {

        if(cmbJobId.getValue()==null
                ||txtAdditionalPrice.getText().equals("")
                ||txtAdvancePayment.getText().equals("")){
            new Alert(Alert.AlertType.CONFIRMATION,"Check whether if all fields have been completed..").show();
            return;
        }

        Date now = new Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());

        FnrOrderDTO fnrOrderDTO = new FnrOrderDTO(
                txtOrderId.getText(),
                sqlDate,
                cmbJobId.getSelectionModel().getSelectedItem(),
                Double.parseDouble(txtFullPayment.getText()),
                Double.parseDouble(txtAdvancePayment.getText())
        );

        try {
            fnrOrderBO.saveFnrOrder(fnrOrderDTO);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }

        try {
            String jobId = cmbJobId.getValue();
            String selectENo =null;
            List<EstimationDTO> allEstimations = estimationBO.findAllEstimations();
            for (EstimationDTO allEstimation : allEstimations) {
                if(allEstimation.getJobId().equals(jobId)){
                    selectENo = allEstimation.getEstimationNo();
                }
            }


            /*EstimationDTO estimation = estimationBO.findEstimation(selectENo);*/
            List<EstimationDetailDTO> wontEstimation = new ArrayList<>();

            List<EstimationDetailDTO> allEstimationsDetail = null;
            allEstimationsDetail = estimationDetailBO.findAllEstimationsDetail();

            for (EstimationDetailDTO estimationDetailDTO : allEstimationsDetail) {
                if(estimationDetailDTO.getEstimationNo().equals(selectENo)){
                    wontEstimation.add(new EstimationDetailDTO(
                            estimationDetailDTO.getEstimationNo(),
                            estimationDetailDTO.getItemCode(),
                            estimationDetailDTO.getEiQuantity(),
                            estimationDetailDTO.getUnitPrice()
                    ));
                }
            }
            System.out.println(wontEstimation);

            for (EstimationDetailDTO estimationDetail : wontEstimation) {
                int eiQuantity = estimationDetail.getEiQuantity();
                String itemCode = estimationDetail.getItemCode();
                ItemDTO item = itemBO.findItem(itemCode);
                int qtyOnHand = item.getQtyOnHand();
                String description = item.getDescription();
                double unitPrice = item.getUnitPrice();
                int newQty = qtyOnHand-eiQuantity;
                itemBO.updateItem(new ItemDTO(
                        itemCode,
                        description,
                        unitPrice,newQty
                ));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        btnUpdateOrder.setDisable(false);
        btnPlaceOrder.setDisable(true);
        txtCustomer.setDisable(true);
        txtName.setDisable(true);
        txtEstimatedItemPrice.setDisable(true);
        txtAdditionalPrice.setDisable(true);
        txtAdvancePayment.setDisable(true);
        txtFullPayment.setDisable(true);
        txtFullPayment.setDisable(true);
        txtRemainingPayment.setDisable(true);
        cmbOrderId.setDisable(true);
        lblDate.setDisable(false);
        cmbJobId.setDisable(true);


        txtCustomer.clear();
        txtName.clear();
        txtEstimatedItemPrice.clear();
        //txtAdditionalPrice.clear();
        //txtAdvancePayment.clear();
        txtFullPayment.clear();
        txtFullPayment.clear();
        txtRemainingPayment.clear();
        cmbOrderId.getSelectionModel().clearSelection();
        lblDate.setText("");
        cmbJobId.getSelectionModel().clearSelection();
    }

    public void reset(){



        txtCustomer.clear();
        txtName.clear();
        txtEstimatedItemPrice.clear();
        txtAdditionalPrice.clear();
        txtAdvancePayment.clear();
        txtFullPayment.clear();
        txtFullPayment.clear();
        txtRemainingPayment.clear();
        cmbOrderId.getSelectionModel().clearSelection();
        lblDate.setText("");
        cmbJobId.getSelectionModel().clearSelection();

    }


    public void btnUpdateOrder_OnAction(ActionEvent actionEvent) {
        if(cmbOrderId.getValue()==null){
            cmbOrderId.setDisable(false);
            btnNewOrder.setDisable(true);
            return;
        }

        btnNewOrder.setDisable(true);
        btnPlaceOrder.setDisable(true);
        String fnrOrderId = cmbOrderId.getValue();
        try {
            FnrOrderDTO fnrOrder = fnrOrderBO.findFnrOrder(fnrOrderId);
            java.sql.Date date = fnrOrder.getDate();


            FnrOrderDTO fnrOrderDTO = new FnrOrderDTO(
                    txtOrderId.getText(),
                    date,
                    fnrOrder.getJobId(),
                    fnrOrder.getPrice(),
                    Double.parseDouble(txtAdvancePayment.getText())
            );
            boolean b = fnrOrderBO.updateFnrOrder(fnrOrderDTO);

            if(b){
                new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updated....").show();
            }
            btnUpdateOrder.setDisable(false);
            btnPlaceOrder.setDisable(true);
            txtCustomer.setDisable(true);
            txtName.setDisable(true);
            txtEstimatedItemPrice.setDisable(true);
            txtAdditionalPrice.setDisable(true);
            txtAdvancePayment.setDisable(true);
            txtFullPayment.setDisable(true);
            txtFullPayment.setDisable(true);
            txtRemainingPayment.setDisable(true);
            cmbOrderId.setDisable(true);
            lblDate.setDisable(false);
            cmbJobId.setDisable(true);


            txtCustomer.clear();
            txtName.clear();
            txtEstimatedItemPrice.clear();
            //txtAdditionalPrice.clear();
            //txtAdvancePayment.clear();
            txtFullPayment.clear();
            txtFullPayment.clear();
            txtRemainingPayment.clear();
            cmbOrderId.getSelectionModel().clearSelection();
            lblDate.setText("");
            cmbJobId.getSelectionModel().clearSelection();
            txtOrderId.clear();
            txtAdvancePayment.clear();
            btnNewOrder.setDisable(false);
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }
    }

    public void cmbCode_OnAction(ActionEvent actionEvent) {
    }

    public void txtId_OnAction(ActionEvent actionEvent) {
    }

    public void txtName_OnAction(ActionEvent actionEvent) {
    }

    public void EstimatedItemPrice_OnAction(ActionEvent actionEvent) {
    }

    public void txtAdditionalPrice_OnAction(ActionEvent actionEvent) {
    }

    public void FullPayment_OnAction(ActionEvent actionEvent) {
    }

    public void AdvancePayment_OnAction(ActionEvent actionEvent) {
    }

    public void RemainingPayment_OnAction(ActionEvent actionEvent) {
    }


    public void cmbOrderId_OnAction(ActionEvent actionEvent) {

    }

    public void txtCustomer_OnAction(ActionEvent actionEvent) {
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
