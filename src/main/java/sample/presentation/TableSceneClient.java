package sample.presentation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import sample.businessLayer.ClientBLL;
import sample.model.Client;
import java.util.List;
/**
 * This UI class opens a window that contains a TableView of all the clients from the table in the database
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 */
public class TableSceneClient extends Stage {
    /**
     * The constructor that initializes the window
     */
    public TableSceneClient() {
        TableView<Client> tableView = new TableView<>();
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ObservableList<Client> observableList= FXCollections.observableArrayList();
        ClientBLL clientBLL=new ClientBLL();
       List<Client> list=clientBLL.findAll();
       clientBLL.displayTable(tableView,list,observableList);
        Scene scene = new Scene(tableView);
        this.setHeight(400);
        this.setWidth(1000);
        this.setScene(scene);
        this.setTitle("Clients");
        this.show();
    }
}
