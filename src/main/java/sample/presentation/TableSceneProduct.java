package sample.presentation;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.businessLayer.ClientBLL;
import sample.businessLayer.ProductBLL;
import sample.model.Client;
import sample.model.Product;

import java.util.List;

public class TableSceneProduct extends Stage {
    public TableSceneProduct(){
        TableView<Product> tableView = new TableView<Product>();
        ProductBLL productBLL=new ProductBLL();
        List<Product> list=productBLL.findAll();
        productBLL.displayTable(tableView,list);
        Scene scene = new Scene(tableView);
        this.setScene(scene);
        this.show();
    }
}
