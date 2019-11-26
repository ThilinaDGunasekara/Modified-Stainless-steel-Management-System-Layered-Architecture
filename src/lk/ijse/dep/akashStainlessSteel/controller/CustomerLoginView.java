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

public class CustomerLoginView {
    public AnchorPane anpCustomer;
    public AnchorPane anpLoad;
    public ImageView imgItemNeedCustomer;
    public ImageView imgServiceNeedCustomer;
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
                case "imgItemNeedCustomer":
                    lblMenu.setText("Manage customer who require items");
                    lblDescription.setText("Click to add, edit, delete or lk.ijse.dep.akashStainlessSteel.view customer who require items");
                    break;
                case "imgServiceNeedCustomer":
                    lblMenu.setText("Manage customer who require services");
                    lblDescription.setText("Click to add, edit, delete or lk.ijse.dep.akashStainlessSteel.view customer who require services");
                    break;
            }

            Theme.mouseEnteredImage(mouseEvent);
        }
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {
        Theme.mouseExitImage(mouseEvent);
        lblMenu.setText("");
        lblDescription.setText("Please select one of above main operations to proceed");
    }

    public void ItemNeedCustomer_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/FRCustomerView.fxml");
    }

    public void ServiceNeedCustomer_OnAction(MouseEvent mouseEvent) throws IOException {
        show("/lk/ijse/dep/akashStainlessSteel/view/FNRCustomerView.fxml");
    }
}
