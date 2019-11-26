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

public class OrderMainController {
    public AnchorPane anpLoad;
    public ImageView imgItemOrder;
    public ImageView imgServicesOrder;
    public Label lbl;
    public Label lblMenu;
    public Label lblDescription;

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
        if (mouseEvent.getSource() instanceof ImageView){
            ImageView icon = (ImageView) mouseEvent.getSource();

            switch(icon.getId()){
                case "imgItemOrder":
                    lblMenu.setText("Place order the items the customer wants");
                    lblDescription.setText("Click to place order the items the customer wants");
                    break;
                case "imgServicesOrder":
                    lblMenu.setText("Place order the job the customer wants");
                    lblDescription.setText("Click to place order the job the customer wants");
                    break;
            }

            Theme.mouseEnteredImage(mouseEvent);
        }
        Theme.mouseEnteredImage(mouseEvent);
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        Theme.mouseExitImage(mouseEvent);
        lblMenu.setText("");
        lblDescription.setText("Please select one of above main operations to proceed");
    }

    public void ItemOrder_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/PlaceOrderItemView.fxml");
    }

    public void ServiceOrder_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/PlaceOrderServiceView.fxml");
    }
}
