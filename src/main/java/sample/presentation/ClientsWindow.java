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
    private Scene menuScene;
    private Main main;
    private Scene productScene;
    public void setMain(Main main){this.main = main;}
    public void setMenuScene(Scene scene1){this.menuScene = scene1;}
    public void setProductScene(Scene scene){this.productScene = scene;}
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
    private Label deleteLabel;

    @FXML
    private TextField deleteNameTextField;

    @FXML
    private Button deleteButton;

    @FXML
    private Label updateLabel;

    @FXML
    private TextField updateIdTextField;

    @FXML
    private TextField updateNameTextField;

    @FXML
    private TextField updateAddressTextField;

    @FXML
    private TextField updateEmailTextField;

    @FXML
    private Button updateButton;

    @FXML
    private Label findLabel;

    @FXML
    private TextField findIdTextField;

    @FXML
    private Button findButton;

    @FXML
    private TextField findNameTextField;

    @FXML
    private TextField findAddressTextField;

    @FXML
    private TextField findEmailTextField;


    @FXML
    private Label clientNotFoundLabel;

    @FXML
    private Label clientNotFoundLabelUpdate;

    @FXML
    private Button showClientButton;

    @FXML
    private Label clientNotFoundLabelDelete;
    @FXML
    private Label viewLabel;

    @FXML
    private Button viewButton;

    @FXML
    private Button backButton;

    @FXML
    void clickAddButton(ActionEvent event) { //validare email+ nume unic
        ClientBLL clientBLL=new ClientBLL();
        Client client=new Client(addNameTextField.getText(),addAddressTextField.getText(),addEmailTextField.getText());
        clientBLL.insertClient(client);
        addNameTextField.clear();
        addAddressTextField.clear();
        addEmailTextField.clear();
    }
    @FXML
    void clickDeleteButton(ActionEvent event) {
        clientNotFoundLabelDelete.setVisible(false);
        if(deleteNameTextField.getText().isEmpty()){
            new EmptyTextFieldsErrorWindow();
        }else{
            String name=deleteNameTextField.getText();
            ClientBLL clientBLL=new ClientBLL();
            Client client=clientBLL.findClientByName(name);
            if(client==null)
            {
                clientNotFoundLabelDelete.setVisible(true);
            }else{
                clientBLL.deleteClient(client);
            }
            deleteNameTextField.clear();
        }
    }

    @FXML
    void clickFindButton(ActionEvent event) { //done
        clientNotFoundLabel.setVisible(false);
        findNameTextField.clear();
        findAddressTextField.clear();
        findEmailTextField.clear();
        if(!findIdTextField.getText().isEmpty()) {
            ClientBLL clientBLL = new ClientBLL();
            int id = Integer.parseInt(findIdTextField.getText());
            Client foundClient = clientBLL.findClientById(id);
            if (foundClient != null) {
                findNameTextField.setText(foundClient.getName());
                findAddressTextField.setText(foundClient.getAddress());
                findEmailTextField.setText(foundClient.getEmail());
            } else {
                clientNotFoundLabel.setVisible(true);
            }

        }else{
            new EmptyTextFieldsErrorWindow();
        }

    }
    @FXML
    void clickShowClient(ActionEvent event) { //done
        clientNotFoundLabelUpdate.setVisible(false);
       updateNameTextField.clear();
       updateAddressTextField.clear();
       updateEmailTextField.clear();
        if(!updateIdTextField.getText().isEmpty()) {
            ClientBLL clientBLL = new ClientBLL();
            int id = Integer.parseInt(updateIdTextField.getText());
            Client foundClient = clientBLL.findClientById(id);
            if (foundClient != null) {
                updateNameTextField.setText(foundClient.getName());
                updateAddressTextField.setText(foundClient.getAddress());
                updateEmailTextField.setText(foundClient.getEmail());
            } else {
                clientNotFoundLabelUpdate.setVisible(true);
            }
        }else{
            new EmptyTextFieldsErrorWindow();
        }
    }

    @FXML
    void clickUpdateButton(ActionEvent event) { //verificare email + nume unic
        clientNotFoundLabelUpdate.setVisible(false);
        if(updateIdTextField.getText().isEmpty() || updateNameTextField.getText().isEmpty() || updateAddressTextField.getText().isEmpty() || updateEmailTextField.getText().isEmpty()){
            new EmptyTextFieldsErrorWindow();
        }else{
            ClientBLL clientBLL=new ClientBLL();
            int id=Integer.parseInt(updateIdTextField.getText());
            if(clientBLL.findClientById(id)==null)
            {
                clientNotFoundLabelUpdate.setVisible(true);
            }else{
                String name=updateNameTextField.getText();
                String address=updateAddressTextField.getText();
                String email=updateEmailTextField.getText();
                Client client=new Client(id,name,address,email);
                clientBLL.updateClient(client);
                updateIdTextField.clear();
                updateNameTextField.clear();
                updateAddressTextField.clear();
                updateEmailTextField.clear();
            }
        }
    }
    @FXML
    void clickBackButton(ActionEvent event) {
        main.setScene(menuScene);
    }

    @FXML
    void clickViewButton(ActionEvent event) {

    }

}

