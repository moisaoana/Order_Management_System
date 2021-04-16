package sample.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.businessLayer.ClientBLL;
import sample.model.Client;


import java.util.List;

public class TableSceneClient extends Stage {
    public TableSceneClient() {
        TableView<Client> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<Client> observableList= FXCollections.observableArrayList();
        ClientBLL clientBLL=new ClientBLL();
       List<Client> list=clientBLL.findAll();
       clientBLL.displayTable(tableView,list,observableList);
        Scene scene = new Scene(tableView);
        this.setHeight(400);
        this.setWidth(530);
        this.setScene(scene);
        this.show();
    }
}
