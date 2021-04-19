package sample.presentation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.businessLayer.ClientBLL;
import sample.model.Client;
import sample.start.Main;
/**
 * This UI class opens a window with all operations that are possible to be performed on the Client Table from the database
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 */
public class ClientsWindow extends Stage{
    private Scene menuScene;
    private Main main;
    private Scene productScene;
    /**
     * This method initializes the main for this class
     * @param main an object of type Main
     */
    public void setMain(Main main){this.main = main;}

    /**
     * This method initializes the menu scene
     * @param scene1 the menu scene
     */
    public void setMenuScene(Scene scene1){this.menuScene = scene1;}
    /**
     * This method initializes the product scene
     * @param scene the product scene
     */
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
    /**
     * This method adds a new client to the table in the database when the user clicks the "Add client" button and enters all the required information
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickAddButton(ActionEvent event) {
        if(addNameTextField.getText().isEmpty() ||addAddressTextField.getText().isEmpty() || addEmailTextField.getText().isEmpty()){
            new ErrorWindow("Please fill all the required text fields!");
        }else{
        ClientBLL clientBLL=new ClientBLL();
        Client client=new Client(addNameTextField.getText(),addAddressTextField.getText(),addEmailTextField.getText());
        if(clientBLL.insertClient(client)!=-1){
        addNameTextField.clear();
        addAddressTextField.clear();
        addEmailTextField.clear();
        }
        }
    }
    /**
     * This method deletes a  client from the table in the database when the user clicks the "Delete client" button and enters the client's name
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickDeleteButton(ActionEvent event) {
        clientNotFoundLabelDelete.setVisible(false);
        if(deleteNameTextField.getText().isEmpty()){
            new ErrorWindow("Please fill all the required text fields!");
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

    /**
     * This method finds an existing client from the table in the database when the user clicks the "Find client" button and enters the client's id. This method also displays the client's information in the corresponding text fields
     * @param event an object of type ActionEvent
     */
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
            new ErrorWindow("Please fill all the required text fields!");
        }

    }
    /**
     * This method finds an existing client from the table in the database when the user clicks the "Show client" button and enters the client's id. This method also displays the client's information in the corresponding text fields
     * @param event an object of type ActionEvent
     */
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
            new ErrorWindow("Please fill all the required text fields!");
        }
    }
    /**
     * This method updates  an existing client from the table in the database when the user clicks the "Update client" button and enters the required information
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickUpdateButton(ActionEvent event) {
        clientNotFoundLabelUpdate.setVisible(false);
        if(updateIdTextField.getText().isEmpty() || updateNameTextField.getText().isEmpty() || updateAddressTextField.getText().isEmpty() || updateEmailTextField.getText().isEmpty()){
            new ErrorWindow("Please fill all the required text fields!");
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
                if(clientBLL.updateClient(client)!=-1) {
                    updateIdTextField.clear();
                    updateNameTextField.clear();
                    updateAddressTextField.clear();
                    updateEmailTextField.clear();
                }
            }
        }
    }

    /**
     * This method changes the main scene to the menu scene when the user clicks on the "Back" button
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickBackButton(ActionEvent event) {
        clientNotFoundLabelUpdate.setVisible(false);
        clientNotFoundLabel.setVisible(false);
        clientNotFoundLabelDelete.setVisible(false);
        findIdTextField.clear();
        clearAllFields();
        main.setScene(menuScene);
    }
    private void clearAllFields(){
        addNameTextField.clear();
        addAddressTextField.clear();
        addEmailTextField.clear();
        deleteNameTextField.clear();
        updateIdTextField.clear();
        updateNameTextField.clear();
        updateEmailTextField.clear();
        updateAddressTextField.clear();
        findNameTextField.clear();
        findIdTextField.clear();
        findEmailTextField.clear();
        findAddressTextField.clear();
    }
    /**
     * This method opens a new TableSceneClient window that displays the contents of the Client table
     * @param event  an object of type ActionEvent
     */
    @FXML
    void clickViewButton(ActionEvent event) {
        new TableSceneClient();
    }

}

