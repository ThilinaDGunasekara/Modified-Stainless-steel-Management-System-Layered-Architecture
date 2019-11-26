package lk.ijse.dep.akashStainlessSteel.controller;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class SearchDashController {

    public AnchorPane anpLoad;
    public ImageView imgCustomer;
    public ImageView imgOrder;
    public ImageView imgItem;
    public Label lbl;
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgWorker;
    public ImageView imgJob;

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

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        Theme.mouseExitImage(mouseEvent);
        lblMenu.setText("");
        lblDescription.setText("Please select one of above main operations to proceed");
    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch (icon.getId()) {
                case "imgCustomer":
                    lblMenu.setText("Search Customers");
                    lblDescription.setText("Click to search customers");
                    break;
                case "imgItem":
                    lblMenu.setText("Search Items");
                    lblDescription.setText("Click to search items");
                    break;
                case "imgWorker":
                    lblMenu.setText("Search Workers");
                    lblDescription.setText("Click to search workers");
                    break;
                case "imgJob":
                    lblMenu.setText("Search Job");
                    lblDescription.setText("Click to search jobs");
                    break;
                case "imgOrder":
                    lblMenu.setText("Search Orders");
                    lblDescription.setText("Click to search order");
                    break;
            }
        }
        Theme.mouseEnteredImage(mouseEvent);
    }

    public void manageCustomer_OnAction(MouseEvent mouseEvent) {

    }

    public void picManageItems_OnClick(MouseEvent mouseEvent) {
    }

    public void picWorker_OnClick(MouseEvent mouseEvent) {
    }

    public void picJob_OnClick(MouseEvent mouseEvent) {
    }

    public void searchCustomer_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/SearchCustomerView.fxml");
    }

    public void searchOrder_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/SearchOrderView.fxml");
    }

    public void searchItems_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/SearchItemView.fxml");
    }

    public void searchWorker_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/SearchWorkerView.fxml");
    }

    public void searchJob_OnClick(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/SearchJobView.fxml");
    }
}
