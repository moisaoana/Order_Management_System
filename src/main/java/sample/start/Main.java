package sample.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.presentation.ClientsWindow;
import sample.presentation.Controller;
import sample.presentation.ProductsWindow;
import java.io.File;
import java.net.URL;
/**
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 * The main class of the application
 */

public class Main extends Application {
    Stage window;
    @Override
    public void start(Stage primaryStage) throws Exception{
        URL urlMenu=new File("src/main/java/sample/presentation/View.fxml").toURI().toURL();
        URL urlClient=new File("src/main/java/sample/presentation/ClientsView.fxml").toURI().toURL();
        URL urlProduct=new File("src/main/java/sample/presentation/ProductsView.fxml").toURI().toURL();
        window=primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(urlMenu);
        Parent rootMenu=loader.load();
        Controller controllerMenu = loader.getController();

        loader = new FXMLLoader();
        loader.setLocation(urlClient);
        Parent rootClient=loader.load();
        ClientsWindow controllerClient = loader.getController();
        loader = new FXMLLoader();
        loader.setLocation(urlProduct);
        Parent rootProduct=loader.load();
        ProductsWindow controllerProduct = loader.getController();
        Scene menuScene = new Scene(rootMenu, 480, 500);
        Scene clientScene = new Scene(rootClient, 650, 500);
        Scene productScene=new Scene(rootProduct,800,500);

        controllerMenu.setClientScene(clientScene);
        controllerMenu.setProductScene(productScene);
        controllerMenu.setMain(this);

        controllerClient.setMenuScene(menuScene);
        controllerClient.setProductScene(productScene);
        controllerClient.setMain(this);

        controllerProduct.setMenuScene(menuScene);
        controllerProduct.setClientScene(clientScene);
        controllerProduct.setMain(this);
        window.setScene(menuScene);
        window.setTitle("Order Management");
        window.show();
    }

    /**
     * Method that sets the current scene of the application
     * @param scene the scene to be set
     */
    public void setScene(Scene scene){
        window.setScene(scene);
    }

    /**
     * Start the application
     * @param args arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}