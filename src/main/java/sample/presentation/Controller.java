package sample.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.start.Main;

import java.io.File;
import java.net.URL;

public class Controller {
    private Scene clientScene;
    private Main main;
    private Scene productScene;
    public void setMain(Main main){
        this.main = main;
    }
    public void setClientScene(Scene scene){
        this.clientScene = scene;
    }
    public void setProductScene(Scene scene){
        this.productScene = scene;
    }
    @FXML
    private Label titleLabel;

    @FXML
    private Button buttonClients;

    @FXML
    private Button buttonProducts;

    @FXML
    private Button buttonOrders;

    @FXML
    void clickClients(ActionEvent event) {
        main.setScene(clientScene);
    }

    @FXML
    void clickOrders(ActionEvent event) {

    }

    @FXML
    void clickProducts(ActionEvent event) {
        main.setScene(productScene);
    }

}
