package sample.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.businessLayer.ProductBLL;
import sample.model.Product;

import java.util.List;

public class TableSceneProduct extends Stage {
    public TableSceneProduct(){
        TableView<Product> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<Product> observableList= FXCollections.observableArrayList();
        ProductBLL productBLL=new ProductBLL();
        List<Product> list=productBLL.findAll();
        productBLL.displayTable(tableView,list,observableList);
        Scene scene = new Scene(tableView);
        this.setScene(scene);
        this.show();
    }
}
