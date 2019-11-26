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
import lk.ijse.dep.akashStainlessSteel.business.custom.ItemBO;
import lk.ijse.dep.akashStainlessSteel.business.exception.AlreadyExistsInOrderException;
import lk.ijse.dep.akashStainlessSteel.db.DBConnection;
import lk.ijse.dep.akashStainlessSteel.dto.ItemDTO;
import lk.ijse.dep.akashStainlessSteel.tm.ItemTM;
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

import static java.lang.Integer.parseInt;

public class ItemController {
    public ImageView picNewCus_id;
    public JFXButton btnItemReport;
    public JFXTextField txtCode;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public TableView<ItemTM> tblList;
    public JFXButton btnDelete_Id;
    public JFXButton btnSave;
    public ImageView imgCustomer;
    public ImageView imgPlaceOrder;
    public ImageView imgItem;
    public ImageView imgSearch;
    public ImageView imgWorker;
    public ImageView imageHome;
    public AnchorPane anpLoad;
    public ImageView imgJob;

    ItemBO itemBO = BOFactory.getInstance().getBO(BOTypes.ITEM);

    public void initialize() {
        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));

        txtCode.setDisable(true);
        txtDescription.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtUnitPrice.setDisable(true);
        btnSave.setDisable(true);
        btnDelete_Id.setDisable(true);
        btnItemReport.setDisable(false);

        try {
            List<ItemDTO> allItems = itemBO.findAllItems();
            ObservableList<ItemTM> items = tblList.getItems();
            items.clear();
            for (ItemDTO allItem : allItems) {
                items.add(new ItemTM(
                        allItem.getItemCode(),
                        allItem.getDescription(),
                        allItem.getUnitPrice(),
                        allItem.getQtyOnHand()
                ));
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something went wrong, Please contact Mr.Thilina").show();
        }

        tblList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItemTM>() {
            @Override
            public void changed(ObservableValue<? extends ItemTM> observable, ItemTM oldValue, ItemTM newValue) {
                ItemTM selectedItem = tblList.getSelectionModel().getSelectedItem();
                if(selectedItem==null){
                    btnSave.setText("Save");
                    btnDelete_Id.setDisable(true);
                    btnItemReport.setDisable(true);
                    return;
                }

                btnSave.setText("Update");
                btnSave.setDisable(false);
                btnDelete_Id.setDisable(false);
                btnItemReport.setDisable(false);

                txtCode.setDisable(true);
                txtUnitPrice.setDisable(false);
                txtDescription.setDisable(false);
                txtQtyOnHand.setDisable(false);

                txtCode.setText(selectedItem.getItemCode());
                txtDescription.setText(selectedItem.getDescription());
                txtQtyOnHand.setText(selectedItem.getQtyOnHand()+"");
                txtUnitPrice.setText(selectedItem.getUnitPrice()+"");
            }
        });

        disableTrue();
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        ItemTM selectedItem = tblList.getSelectionModel().getSelectedItem();
        String itemCode = selectedItem.getItemCode();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure whether you want to delete this customer?",
                ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if(buttonType.get()==ButtonType.YES){
            try {
                itemBO.deleteItem(itemCode);
                initialize();
            }catch (AlreadyExistsInOrderException e){
                new Alert(Alert.AlertType.CONFIRMATION,e.getMessage()).show();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }
        }
        reset();
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        ObservableList<ItemTM> items = tblList.getItems();

        if(btnSave.getText().equals("Save")) {
            ItemDTO itemDTO = new ItemDTO(
                    txtCode.getText(),
                    txtDescription.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText())
            );

            ItemTM itemTM = new ItemTM(
                    txtCode.getText(),
                    txtDescription.getText(),
                    Double.parseDouble(txtUnitPrice.getText()),
                    Integer.parseInt(txtQtyOnHand.getText()));

            try {
                itemBO.saveItem(itemDTO);
                items.add(itemTM);
                initialize();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, Please contact Mr.Thilina").show();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }
        }else{

            try {
                 itemBO.updateItem(new ItemDTO(
                        txtCode.getText(),
                        txtDescription.getText(),
                        Double.parseDouble(txtUnitPrice.getText()),
                        Integer.parseInt(txtQtyOnHand.getText())
                ));
                 btnSave.setText("Save");
                 initialize();
                 reset();
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, Please contact Mr.Thilina").show();
                Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
            }
        }
    }
    public void btnItemReport_OnAction(ActionEvent actionEvent) throws Exception {

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(this.getClass().getResourceAsStream("/lk/ijse/dep/akashStainlessSteel/report/ItemReport.jasper"));
        Map<String,Object> params = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, DBConnection.getInstance().getConnection());
        JasperViewer.viewReport(jasperPrint,false);
    }
    public void picNewCus_OnClick(MouseEvent mouseEvent) {
        disableFalse();
        txtCode.setDisable(true);
        btnSave.setDisable(false);
        txtCode.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtQtyOnHand.clear();
        int maxId =0;
        try {
            String lastItemCode = itemBO.getLastItemCode();

            if(lastItemCode==null){
                maxId=0;

            }else {
                maxId = parseInt(lastItemCode.replace("IC", ""));
            }

            maxId = maxId + 1;
            String id = "";
            if (maxId < 10) {
                id = "IC00" + maxId;
            } else if (maxId < 100) {
                id = "IC0" + maxId;
            } else {
                id = "E" + maxId;
            }
            txtCode.setText(id);

        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR,"Something went wrong, Please contact Mr.Thilina").show();
            Logger.getLogger("lk.ijse.dep.akashStainlessSteel.controller").log(Level.SEVERE, null,e);
        }
    }
    public void reset(){
        txtCode.clear();
        txtDescription.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
    }

    public void disableTrue(){
        txtUnitPrice.setDisable(true);
        txtQtyOnHand.setDisable(true);
        txtDescription.setDisable(true);
        txtCode.setDisable(true);
        txtUnitPrice.setDisable(true);
        btnSave.setDisable(true);
        btnDelete_Id.setDisable(true);
        btnItemReport.setDisable(false);
    }

    public void disableFalse(){
        txtUnitPrice.setDisable(false);
        txtQtyOnHand.setDisable(false);
        txtDescription.setDisable(false);
        txtCode.setDisable(false);
        txtUnitPrice.setDisable(false);
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
    public void manageCustomer_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/CustomerLoginView.fxml");
    }
}
