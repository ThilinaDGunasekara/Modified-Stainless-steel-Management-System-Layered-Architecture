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
import lk.ijse.dep.akashStainlessSteel.business.custom.SearchOrdersBO;
import lk.ijse.dep.akashStainlessSteel.dto.SearchOrderDTO;
import lk.ijse.dep.akashStainlessSteel.tm.SearchOrderTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SearchOrderController {
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
    public JFXTextField txtSearch_id;
    public TableView<SearchOrderTM> tblList;

    SearchOrdersBO searchOrdersBO = BOFactory.getInstance().getBO(BOTypes.SEARCH_ORDER);

    public void initialize(){
        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("date"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tblList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblList.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("remainingPayment"));

        ObservableList<SearchOrderTM> items = tblList.getItems();
        items.clear();

        try {
            List<SearchOrderDTO> allOrdersInFNRC = searchOrdersBO.findAllOrdersInFNRC();
            for (SearchOrderDTO searchOrderDTO : allOrdersInFNRC) {
                items.add(new SearchOrderTM(
                        searchOrderDTO.getOrderId(),
                        searchOrderDTO.getDate(),
                        searchOrderDTO.getCustomerId(),
                        searchOrderDTO.getName(),
                        searchOrderDTO.getTotal(),
                        searchOrderDTO.getRemainingPayment()

                ));
            }

            List<SearchOrderDTO> allOrdersInFRC = searchOrdersBO.findAllOrdersInFRC();
            for (SearchOrderDTO searchOrderDTO : allOrdersInFRC) {
                items.add(new SearchOrderTM(
                      searchOrderDTO.getOrderId(),
                      searchOrderDTO.getDate(),
                      searchOrderDTO.getCustomerId(),
                      searchOrderDTO.getName(),
                      searchOrderDTO.getTotal()
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        txtSearch_id.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                String text = txtSearch_id.getText();
                String searchText = "%"+text+"%";

                try {
                    List<SearchOrderDTO> searchOrderDTOS = searchOrdersBO.searchOrder(searchText);
                    List<SearchOrderDTO> searchOrderDTOS1 = searchOrdersBO.searchOrder2(searchText);
                    ObservableList<SearchOrderTM> items1 = tblList.getItems();
                    items1.clear();

                    for (SearchOrderDTO searchOrderDTO : searchOrderDTOS) {
                        items1.add(new SearchOrderTM(
                                searchOrderDTO.getOrderId(),
                                searchOrderDTO.getDate(),
                                searchOrderDTO.getCustomerId(),
                                searchOrderDTO.getName(),
                                searchOrderDTO.getTotal(),
                                searchOrderDTO.getRemainingPayment()
                        ));
                    }
                    for (SearchOrderDTO searchOrderDTO1 : searchOrderDTOS1) {
                        items1.add(new SearchOrderTM(
                                searchOrderDTO1.getOrderId(),
                                searchOrderDTO1.getDate(),
                                searchOrderDTO1.getCustomerId(),
                                searchOrderDTO1.getName(),
                                searchOrderDTO1.getTotal()
                        ));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) {
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
