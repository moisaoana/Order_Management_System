package sample.presentation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import sample.businessLayer.ClientBLL;
import sample.model.Client;
import sample.start.Main;

public class ClientsWindow extends Stage{
    private Scene scene1;
    private Main main;

    public void setMain(Main main){this.main = main;}
    public void setScene1(Scene scene1){this.scene1 = scene1;}
    @FXML
    private Label titleLabel;

    @FXML
    private Label addLabel;

    @FXML
    private TextField addNameTextField;

    @FXML
    private TextField addAddressTextField;

    @FXML
    private TextField addEmailTextField;

    @FXML
    private Button addButton;

    @FXML
    void clickAddButton(ActionEvent event) {
        ClientBLL clientBLL=new ClientBLL();
        Client client=new Client(addNameTextField.getText(),addAddressTextField.getText(),addEmailTextField.getText());
        clientBLL.insertClient(client);
        addNameTextField.clear();
        addAddressTextField.clear();
        addEmailTextField.clear();
    }


}

