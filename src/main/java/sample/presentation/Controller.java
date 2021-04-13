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
    private Scene scene2;
    private Main main;

    public void setMain(Main main){
        this.main = main;
    }
    public void setScene2(Scene scene2){
        this.scene2 = scene2;
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
        main.setScene(scene2);
    }

    @FXML
    void clickOrders(ActionEvent event) {

    }

    @FXML
    void clickProducts(ActionEvent event) {

    }

}
