package sample.start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.businessLayer.ClientBLL;
import sample.businessLayer.ProductBLL;
import sample.dataAccessLayer.ClientDAO;
import sample.model.Client;
import sample.model.Product;
import sample.presentation.ClientsWindow;
import sample.presentation.Controller;

import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Random;

public class Main extends Application {
    Stage window;
    Scene scene1,scene2;
    @Override
    public void start(Stage primaryStage) throws Exception{
        /*
        URL url=new File("src/main/java/sample/presentation/View.fxml").toURI().toURL();
        Parent root=FXMLLoader.load(url);
        primaryStage.setTitle("Order Management");
        primaryStage.setScene(new Scene(root, 480, 500));
        primaryStage.show();

        ClientBLL clientBLL=new ClientBLL();
        Client client=clientBLL.findClientById(2);
        System.out.println(client.getName());
        ProductBLL productBLL=new ProductBLL();
        Product product=productBLL.findProductById(1);
        System.out.println(product.getName());
        ClientDAO clientDAO=new ClientDAO();
        System.out.println(clientDAO.createUpdateQuery("id"));
        Client client1=new Client(1,"Maria","Cimbrului","anaaa@gmail.com");
        clientBLL.updateClient(client1);

         */
        URL url=new File("src/main/java/sample/presentation/View.fxml").toURI().toURL();
        URL url2=new File("src/main/java/sample/presentation/ClientsView.fxml").toURI().toURL();
        window=primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        Parent root1=loader.load();
        Controller controller1 = loader.getController();
        loader = new FXMLLoader();
        loader.setLocation(url2);
        Parent root2=loader.load();
        ClientsWindow controller2 = loader.getController();
        Scene scene1 = new Scene(root1, 480, 500);
        Scene scene2 = new Scene(root2, 550, 500);
        controller1.setScene2(scene2);
        controller1.setMain(this);
        controller2.setScene1(scene1);
        controller2.setMain(this);
        window.setScene(scene1);
        window.setTitle("Order Management");
        window.show();
    }
    public void setScene(Scene scene){
        window.setScene(scene);
    }

    public static void main(String[] args) {
        launch(args);
    }
}