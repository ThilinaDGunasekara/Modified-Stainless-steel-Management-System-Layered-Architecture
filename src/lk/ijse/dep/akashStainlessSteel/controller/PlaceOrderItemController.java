package lk.ijse.dep.akashStainlessSteel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.akashStainlessSteel.business.BOFactory;
import lk.ijse.dep.akashStainlessSteel.business.BOTypes;
import lk.ijse.dep.akashStainlessSteel.business.custom.FrCustomerBO;
import lk.ijse.dep.akashStainlessSteel.business.custom.FrOrderBO;
import lk.ijse.dep.akashStainlessSteel.business.custom.FrOrderDetailBO;
import lk.ijse.dep.akashStainlessSteel.business.custom.ItemBO;
import lk.ijse.dep.akashStainlessSteel.dto.FrCustomerDTO;
import lk.ijse.dep.akashStainlessSteel.dto.FrOrderDTO;
import lk.ijse.dep.akashStainlessSteel.dto.ItemDTO;
import lk.ijse.dep.akashStainlessSteel.dto.OrderDetailDTO;
import lk.ijse.dep.akashStainlessSteel.tm.FrOrderTM;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;

public class PlaceOrderItemController {
    public AnchorPane anpCustomer;
    public AnchorPane anpLoad;
    public JFXButton btnNewOrder;
    public JFXComboBox<String> cmbCode;
    public JFXTextField txtDescription;
    public JFXTextField txtOrderId;
    public JFXTextField txtQuantity;
    public JFXTextField txtUnitPrice;
    public JFXComboBox<String> cmbCustomerId;
    public JFXTextField txtName;
    public JFXButton btnAdd;
    public Label lblDate;
    public ImageView imgCustomer;
    public ImageView imgPlaceOrder;
    public ImageView imgItem;
    public ImageView imgSearch;
    public ImageView imgWorker;
    public ImageView imageHome;
    public JFXTextField txtTotal;
    public JFXButton btnPlaceOrder;
    public TableView<FrOrderTM> tblList_Id;
    public ImageView imgJob;


    private FrCustomerBO frCustomerBO = BOFactory.getInstance().getBO(BOTypes.FR_CUSTOMER);
    private ItemBO itemBO = BOFactory.getInstance().getBO(BOTypes.ITEM);
    private FrOrderBO frOrderBO = BOFactory.getInstance().getBO(BOTypes.FR_ORDER);
    FrOrderDetailBO frOrderDetailBO = BOFactory.getInstance().getBO(BOTypes.ORDER_DETAIL);

    List<FrOrderTM> holdAllIemDetail = new ArrayList<>();

    private double fullTotal=0;

    boolean alreadyAdd=false;


    public void initialize(){

       /* SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        lblDate.setText(formatter.format(date));*/
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        Date now = new Date();
        java.sql.Date sqlDate = new java.sql.Date(now.getTime());
        lblDate.setText(formatter.format(sqlDate));


        tblList_Id.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblList_Id.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblList_Id.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tblList_Id.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblList_Id.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblList_Id.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("delete"));


        try {

            List<String> ids = frCustomerBO.getAllFrCustomerIDs();
            cmbCustomerId.setItems(FXCollections.observableArrayList(ids));
            List<String> tableItemCode = new ArrayList<>();

            ObservableList<FrOrderTM> items = tblList_Id.getItems();
            for (int i = 0; i <items.size() ; i++) {
                tableItemCode.add(items.get(i).getItemCode());
            }

            List<String> codes = itemBO.getAllItemIDs();

            
            cmbCode.setItems(FXCollections.observableArrayList(codes));

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(cmbCustomerId.getValue()==null){
                    return;
                }
                String selectedItem = cmbCustomerId.getSelectionModel().getSelectedItem();

                try {
                    FrCustomerDTO frCustomer = frCustomerBO.findFrCustomer(selectedItem);
                    txtName.setText(frCustomer.getName());
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
                    Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
                }
            }
        });

        cmbCode.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(cmbCode.getValue()==null){
                    return;
                }
                String selectedItem = cmbCode.getSelectionModel().getSelectedItem();

                try {
                    ItemDTO item = itemBO.findItem(selectedItem);
                    txtDescription.setText(item.getDescription());
                    txtUnitPrice.setText(String.valueOf(item.getUnitPrice()));
                } catch (Exception e) {
                    new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
                    Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
                }
            }
        });

        tblList_Id.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<FrOrderTM>() {
            @Override
            public void changed(ObservableValue<? extends FrOrderTM> observable, FrOrderTM oldValue, FrOrderTM newValue) {


                FrOrderTM selectedItem = tblList_Id.getSelectionModel().getSelectedItem();
                if(selectedItem==null){
                    return;
                }

                btnAdd.setText("Update");

                String itemCode = selectedItem.getItemCode();
                int quantity = selectedItem.getQuantity();
                double unitPrice = selectedItem.getUnitPrice();
                String description = selectedItem.getDescription();


                txtQuantity.setText(quantity+"");
                txtDescription.setText(description);
                txtUnitPrice.setText(unitPrice+"");
                cmbCode.setValue(itemCode);
                txtQuantity.requestFocus();
                txtQuantity.selectAll();


            }
        });

        txtOrderId.setDisable(true);
        lblDate.setDisable(true);
        cmbCustomerId.setDisable(true);
        txtName.setDisable(true);
        cmbCode.setDisable(true);
        txtDescription.setDisable(true);
        txtUnitPrice.setDisable(true);
        txtQuantity.setDisable(true);

        btnAdd.setDisable(true);
        tblList_Id.setDisable(true);
        btnPlaceOrder.setDisable(true);
        txtTotal.setDisable(true);
    }
    public void btnNewOrder_OnAction(ActionEvent actionEvent) {

        int maxId =0;
        try {
            String lastEstimationId = frOrderBO.getLastFrOrderId();

            if(lastEstimationId==null){
                maxId=0;
            }else {
                maxId = parseInt(lastEstimationId.replace("OI", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "OI00" + maxId;
            } else if (maxId < 100) {
                id = "OI0" + maxId;
            } else {
                id = "OI" + maxId;
            }
            txtOrderId.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }

        txtOrderId.setDisable(false);
        lblDate.setDisable(false);
        cmbCustomerId.setDisable(false);
        txtName.setDisable(false);
        cmbCode.setDisable(false);
        txtDescription.setDisable(false);
        txtUnitPrice.setDisable(false);
        txtQuantity.setDisable(false);

        btnAdd.setDisable(false);
        tblList_Id.setDisable(true);
        btnPlaceOrder.setDisable(true);
        txtTotal.setDisable(false);
        tblList_Id.getItems().clear();
        cmbCode.getSelectionModel().clearSelection();
        txtName.clear();
        txtTotal.clear();

        txtTotal.setText("0.00");
    }
    public void cmbCode_OnAction(ActionEvent actionEvent) {
    }
    public void txtDescription_OnAction(ActionEvent actionEvent) {
    }
    public void txtId_OnAction(ActionEvent actionEvent) {
    }
    public void txtQuantity_OnAction(ActionEvent actionEvent) {
    }
    public void txtUnitPrice_OnAction(ActionEvent actionEvent) {
    }
    public void cmbCustomerId_OnAction(ActionEvent actionEvent) {
    }
    public void txtName_OnAction(ActionEvent actionEvent) {
    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {

        if(txtName.getText().equals("")
                ||txtDescription.getText().equals("")
                ||txtQuantity.getText().equals("")
                ||txtUnitPrice.getText().equals("")
                ||cmbCustomerId.getValue().equals(null)
                ||cmbCode.getValue().equals(null)){

            new Alert(Alert.AlertType.CONFIRMATION,"Check whether if all fields have been completed..").show();
            return;
        }
        JFXButton btnDelete= new JFXButton("Delete");
        List<FrOrderTM> holdItems = new ArrayList<>();
        if(btnAdd.getText().equals("Add")) {

           /* List<FrOrderTM> holdItems = new ArrayList<>();*/
            ObservableList<FrOrderTM> items = tblList_Id.getItems();
            for (FrOrderTM item : items) {
                holdItems.add(new FrOrderTM(
                        item.getItemCode(),
                        item.getDescription(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getTotal(),
                        item.getDelete()
                ));
            }

            items.clear();

            try {

                    String selectedItem = cmbCode.getSelectionModel().getSelectedItem();
                    btnDelete.setBackground(new Background(new BackgroundFill(Color.SKYBLUE, null, null)));
                    btnDelete.setId(selectedItem);

                    ItemDTO item = itemBO.findItem(selectedItem);
                    double unitPrice = item.getUnitPrice();
                    int quantity = parseInt(txtQuantity.getText());
                    int qtyOnHand = item.getQtyOnHand();


                    double total = 0;

                    total = unitPrice * quantity;
                    fullTotal += total;


                    if (quantity <= 0 || quantity > qtyOnHand) {
                        new Alert(Alert.AlertType.ERROR, "Invalid Quantity..", ButtonType.OK).show();
                        txtQuantity.requestFocus();
                        txtQuantity.selectAll();
                        return;
                    }
                if(alreadyAdd == false) {
                    holdItems.add(new FrOrderTM(
                            selectedItem,
                            txtDescription.getText(),
                            quantity,
                            unitPrice,
                            unitPrice * Integer.parseInt(txtQuantity.getText()),
                            btnDelete
                    ));
                }
                    for (FrOrderTM holdItem : holdItems) {
                        items.add(new FrOrderTM(
                                holdItem.getItemCode(),
                                holdItem.getDescription(),
                                holdItem.getQuantity(),
                                holdItem.getUnitPrice(),
                                holdItem.getTotal(),
                                holdItem.getDelete()
                        ));
                    }

                btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        FrOrderTM selectedItem1 = tblList_Id.getSelectionModel().getSelectedItem();
                        if (selectedItem1 == null) {
                            new Alert(Alert.AlertType.CONFIRMATION, "First select the table item row...").show();
                            return;
                        }
                        try {
                            int deleteQty = 0;

                            String itemCode1 = selectedItem1.getItemCode();
                            for (FrOrderTM orderedItem : items) {
                                if (orderedItem.getItemCode().equals(itemCode1)) {
                                    deleteQty = orderedItem.getQuantity();
                                    System.out.println(deleteQty);
                                    break;
                                }
                            }

                            ItemDTO item1 = itemBO.findItem(itemCode1);
                            double unitPrice1 = item1.getUnitPrice();
                            double totDelete = unitPrice1 * deleteQty;
                            fullTotal -= totDelete;
                            txtTotal.setText(fullTotal + "0");
                            System.out.println(fullTotal);
                            System.out.println(unitPrice1);
                            System.out.println(totDelete);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        String itemCode = selectedItem1.getItemCode();
                        for (int i = 0; i < items.size(); i++) {
                            if (itemCode.equals(items.get(i).getItemCode())) {
                                items.remove(i);
                            }
                        }
                        holdItems.clear();
                        for (FrOrderTM item : items) {
                            holdItems.add(new FrOrderTM(
                                    item.getItemCode(),
                                    item.getDescription(),
                                    item.getQuantity(),
                                    item.getUnitPrice(),
                                    item.getTotal(),
                                    item.getDelete()
                            ));
                        }

                        items.clear();
                        for (FrOrderTM holdItem : holdItems) {
                            items.add(new FrOrderTM(
                                    holdItem.getItemCode(),
                                    holdItem.getDescription(),
                                    holdItem.getQuantity(),
                                    holdItem.getUnitPrice(),
                                    holdItem.getTotal(),
                                    holdItem.getDelete()
                            ));
                        }

                        btnAdd.setText("Add");
                    }

                });


            } catch (Exception e) {
                e.printStackTrace();
            }

        }else {
            alreadyAdd=true;
            FrOrderTM selectedItem = tblList_Id.getSelectionModel().getSelectedItem();
            String itemCode = selectedItem.getItemCode();

            ObservableList<FrOrderTM> items = tblList_Id.getItems();
            List<FrOrderTM> temp = new ArrayList<>();

            for (FrOrderTM item : items) {
                temp.add(new FrOrderTM(
                        item.getItemCode(),
                        item.getDescription(),
                        item.getQuantity(),
                        item.getUnitPrice(),
                        item.getTotal(),
                        item.getDelete()
                ));
            }

            items.clear();

            for (int i = 0; i <temp.size() ; i++) {
                if(temp.get(i).getItemCode().equals(itemCode)){
                    temp.remove(i);
                }
            }

            btnDelete.setBackground(new Background(new BackgroundFill(Color.SKYBLUE,null,null)));
            btnDelete.setId(itemCode);

            int quantity1 = parseInt(txtQuantity.getText());
            ItemDTO item = null;
            try {
                item = itemBO.findItem(itemCode);
            } catch (Exception e) {
                e.printStackTrace();
            }
            int qtyOnHand = item.getQtyOnHand();


            if(quantity1 <= 0 || quantity1 > qtyOnHand){
                new Alert(Alert.AlertType.ERROR, "Invalid Quantity..", ButtonType.OK).show();
                txtQuantity.requestFocus();
                txtQuantity.selectAll();
                return;
            }

            double unitPrice1 = Double.parseDouble(txtUnitPrice.getText());
            double total = quantity1*unitPrice1;

            temp.add(new FrOrderTM(
                    cmbCode.getValue(),
                    txtDescription.getText(),
                    quantity1,
                    unitPrice1,
                    total,
                    btnDelete
            ));

            for (FrOrderTM frOrderTM : temp) {
                items.add(new FrOrderTM(
                     frOrderTM.getItemCode(),
                     frOrderTM.getDescription(),
                     frOrderTM.getQuantity(),
                     frOrderTM.getUnitPrice(),
                     frOrderTM.getTotal(),
                     frOrderTM.getDelete()
                ));
            }

            btnDelete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    FrOrderTM selectedItem1 = tblList_Id.getSelectionModel().getSelectedItem();
                    if (selectedItem1 == null) {
                        new Alert(Alert.AlertType.CONFIRMATION, "First select the table item row...").show();
                        return;
                    }
                    try {
                        int deleteQty = 0;

                        String itemCode1 = selectedItem1.getItemCode();
                        for (FrOrderTM orderedItem : items) {
                            if (orderedItem.getItemCode().equals(itemCode1)) {
                                deleteQty = orderedItem.getQuantity();
                                System.out.println(deleteQty);
                                break;
                            }
                        }

                        ItemDTO item1 = itemBO.findItem(itemCode1);
                        double unitPrice1 = item1.getUnitPrice();
                        double totDelete = unitPrice1 * deleteQty;
                        fullTotal -= totDelete;
                        txtTotal.setText(fullTotal + "0");
                        System.out.println(fullTotal);
                        System.out.println(unitPrice1);
                        System.out.println(totDelete);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
                    }
                    String itemCode = selectedItem1.getItemCode();
                    for (int i = 0; i < items.size(); i++) {
                        if (itemCode.equals(items.get(i).getItemCode())) {
                            items.remove(i);
                        }
                    }
                    holdItems.clear();
                    for (FrOrderTM item : items) {
                        holdItems.add(new FrOrderTM(
                                item.getItemCode(),
                                item.getDescription(),
                                item.getQuantity(),
                                item.getUnitPrice(),
                                item.getTotal(),
                                item.getDelete()
                        ));
                    }

                    items.clear();
                    for (FrOrderTM holdItem : holdItems) {
                        items.add(new FrOrderTM(
                                holdItem.getItemCode(),
                                holdItem.getDescription(),
                                holdItem.getQuantity(),
                                holdItem.getUnitPrice(),
                                holdItem.getTotal(),
                                holdItem.getDelete()
                        ));
                    }

                    btnAdd.setText("Add");
                }

            });

            btnAdd.setText("Add");
        }
        btnPlaceOrder.setDisable(false);
        tblList_Id.setDisable(false);
        txtOrderId.setDisable(true);
        txtName.setDisable(true);
        cmbCustomerId.setDisable(true);
        cmbCode.getSelectionModel().clearSelection();
        txtQuantity.clear();
        txtUnitPrice.clear();
        txtDescription.clear();

        txtTotal.setText(fullTotal+"0");
        btnAdd.setText("Add");

    }
    public void txtTotal_OnAction(ActionEvent actionEvent) {
    }
    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) throws Exception {
        try {
            String customerId = cmbCustomerId.getSelectionModel().getSelectedItem();

            Date now = new Date();
            java.sql.Date sqlDate = new java.sql.Date(now.getTime());


            FrOrderDTO frOrderDTO = new FrOrderDTO(
                txtOrderId.getText(),
                sqlDate,
                customerId
            );
            List<OrderDetailDTO> orderDetailDTOS = new ArrayList<>();
            ObservableList<FrOrderTM> items = tblList_Id.getItems();
            for (FrOrderTM item : items) {
                orderDetailDTOS.add(new OrderDetailDTO(
                    txtOrderId.getText(),
                    item.getItemCode(),
                    item.getUnitPrice(),
                    item.getQuantity()
                ));
            }

            frOrderBO.saveFrOrder(frOrderDTO);

            for (OrderDetailDTO orderDetailDTO : orderDetailDTOS) {
                boolean b = frOrderDetailBO.saveFrOrderDetail(new OrderDetailDTO(
                        orderDetailDTO.getOrderId(),
                        orderDetailDTO.getItemCode(),
                        orderDetailDTO.getUnitPrice(),
                        orderDetailDTO.getQuantity()
                ));

                if(b){
                    new Alert(Alert.AlertType.CONFIRMATION,"Successfully Placed..").show();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }
/*
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ijse/dep/akashStainlessSteel/report/ItemReport.jasper"));
        Map<String,Object> params = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);*/

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
