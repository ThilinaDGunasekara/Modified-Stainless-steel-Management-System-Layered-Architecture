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
import javafx.scene.control.Label;
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
import lk.ijse.dep.akashStainlessSteel.business.custom.FrCustomerBO;
import lk.ijse.dep.akashStainlessSteel.business.custom.SearchCustomerBO;
import lk.ijse.dep.akashStainlessSteel.dto.FnrCustomerDTO;
import lk.ijse.dep.akashStainlessSteel.dto.FrCustomerDTO;
import lk.ijse.dep.akashStainlessSteel.dto.SearchCustomerDTO;
import lk.ijse.dep.akashStainlessSteel.tm.FrCustomerTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SearchCustomerController {
    public AnchorPane anpCustomer;
    public AnchorPane anpLoad;
    public Label lblDate;
    public ImageView imgCustomer;
    public ImageView imgPlaceOrder;
    public ImageView imgItem;
    public ImageView imgSearch;
    public ImageView imgWorker;
    public ImageView imageHome;
    public ImageView imgJob;
    public JFXButton btnPlaceOrder;
    public TableView<FrCustomerTM> tblList;
    public JFXButton btnSearchCustomerReport;
    public JFXTextField txtSearch;

    SearchCustomerBO searchCustomerBO = BOFactory.getInstance().getBO(BOTypes.SEARCH_CUSTOMER);
    FrCustomerBO frCustomerBO = BOFactory.getInstance().getBO(BOTypes.FR_CUSTOMER);
    FnrCustomerBO fnrCustomerBO = BOFactory.getInstance().getBO(BOTypes.FNR_CUSTOMER);

    public void initialize(){
        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNo"));

        ObservableList<FrCustomerTM> items = tblList.getItems();
        try {
            items.clear();
            List<FrCustomerDTO> allFrCustomers = frCustomerBO.findAllFrCustomers();
            for (FrCustomerDTO allFrCustomer : allFrCustomers) {
                items.add(new FrCustomerTM(
                      allFrCustomer.getCustomerId(),
                      allFrCustomer.getName(),
                      allFrCustomer.getAddress(),
                      allFrCustomer.getContactNo()
                ));
            }
            List<FnrCustomerDTO> allFnrCustomers = fnrCustomerBO.findAllFnrCustomers();
            for (FnrCustomerDTO allFnrCustomer : allFnrCustomers) {
                items.add(new FrCustomerTM(
                        allFnrCustomer.getCustomerId(),
                        allFnrCustomer.getName(),
                        allFnrCustomer.getAddress(),
                        allFnrCustomer.getContactNo()
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        txtSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String searchText = txtSearch.getText();
                String Text = "%"+searchText+"%";
                try {
                    List<SearchCustomerDTO> searchCustomerDTOS = searchCustomerBO.searchCustomer(Text);
                    List<SearchCustomerDTO> searchCustomerDTOS1 = searchCustomerBO.searchCustomerFnr(Text);

                    ObservableList<FrCustomerTM> items1 = tblList.getItems();
                    items1.clear();
                    for (SearchCustomerDTO searchCustomerDTO : searchCustomerDTOS) {
                        items1.add(new FrCustomerTM(
                                searchCustomerDTO.getCustomerId(),
                                searchCustomerDTO.getName(),
                                searchCustomerDTO.getAddress(),
                                searchCustomerDTO.getContactNo()
                        ));
                    }
                    for (SearchCustomerDTO searchCustomerDTO1 : searchCustomerDTOS1) {
                        items1.add(new FrCustomerTM(
                                searchCustomerDTO1.getCustomerId(),
                                searchCustomerDTO1.getName(),
                                searchCustomerDTO1.getAddress(),
                                searchCustomerDTO1.getContactNo()
                        ));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void btnSearchCustomerReport_OnAction(ActionEvent actionEvent) {

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
