package sample.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.start.Main;
/**
 * This UI class represents the main window of the application and acts like a menu
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 */
public class Controller {
    private Scene clientScene;
    private Main main;
    private Scene productScene;
    /**
     * This method initializes the main for this class
     * @param main an object of type Main
     */
    public void setMain(Main main){
        this.main = main;
    }

    /**
     * This method initializes the client scene
     * @param scene the client scene
     */
    public void setClientScene(Scene scene){
        this.clientScene = scene;
    }

    /**
     * This method initializes the product scene
     * @param scene the product scene
     */
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

    /**
     * This method sets the main scene of the application to the Client Scene when the user clicks the "Client" button
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickClients(ActionEvent event) {
        main.setScene(clientScene);
    }

    /**
     * This method sets the main scene of the application to the Order Scene when the user clicks the "Order" button
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickOrders(ActionEvent event) {
        new OrdersWindow();
    }

    /**
     * This method sets the main scene of the application to the Product Scene when the user clicks the "Product" button
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickProducts(ActionEvent event) {
        main.setScene(productScene);
    }

}
