package sample.presentation;

import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.businessLayer.ClientBLL;
import sample.dataAccessLayer.AbstractDAO;
import sample.dataAccessLayer.ProductDAO;
import sample.model.Client;
import sample.model.Product;

import java.util.List;

public class TableSceneClient extends Stage {
    public TableSceneClient() {
        TableView<Client> tableView = new TableView<Client>();
        ClientBLL clientBLL=new ClientBLL();
       List<Client> list=clientBLL.findAll();
       clientBLL.displayTable(tableView,list);
        Scene scene = new Scene(tableView);
        this.setHeight(400);
        this.setWidth(530);
        this.setScene(scene);
        this.show();
    }
}
