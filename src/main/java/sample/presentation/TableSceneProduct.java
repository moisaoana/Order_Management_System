package sample.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.businessLayer.ProductBLL;
import sample.model.Product;
import java.util.List;
/**
 * This UI class opens a window that contains a TableView of all the products from the table in the database
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 */
public class TableSceneProduct extends Stage {
    /**
     * The constructor that initializes the window
     */
    public TableSceneProduct(){
        TableView<Product> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<Product> observableList= FXCollections.observableArrayList();
        ProductBLL productBLL=new ProductBLL();
        List<Product> list=productBLL.findAll();
        productBLL.displayTable(tableView,list,observableList);
        Scene scene = new Scene(tableView);
        this.setHeight(400);
        this.setWidth(1000);
        this.setScene(scene);
        this.setTitle("Products");
        this.show();
    }
}
