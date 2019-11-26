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
import lk.ijse.dep.akashStainlessSteel.business.custom.SearchWorkerBO;
import lk.ijse.dep.akashStainlessSteel.business.custom.WorkerBO;
import lk.ijse.dep.akashStainlessSteel.dto.WorkerDTO;
import lk.ijse.dep.akashStainlessSteel.tm.WorkerTM;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchWorkerController {
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
    public TableView<WorkerTM> tblList;

    WorkerBO workerBO = BOFactory.getInstance().getBO(BOTypes.WORKER);
    SearchWorkerBO searchWorkerBO = BOFactory.getInstance().getBO(BOTypes.SEARCH_WORKERS);

    public void initialize() {
        tblList.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("workerId"));
        tblList.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblList.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblList.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("contactNo"));
        tblList.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("salary"));

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


        txtSearch_id.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ObservableList<WorkerTM> items = tblList.getItems();
                items.clear();

                try {
                    List<WorkerDTO> workerDTOS = searchWorkerBO.searchWorker("%"+txtSearch_id.getText()+"%");
                    for (WorkerDTO workerDTO : workerDTOS) {
                        items.add(new WorkerTM(
                               workerDTO.getWorkerId(),
                               workerDTO.getName(),
                               workerDTO.getAddress(),
                               workerDTO.getContactNo(),
                               workerDTO.getSalary()
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
