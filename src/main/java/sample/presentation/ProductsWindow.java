package sample.presentation;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.businessLayer.ProductBLL;
import sample.model.Product;
import sample.start.Main;
/**
 * This UI class opens a window with all operations that are possible to be performed on the Product Table from the database
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 */
public class ProductsWindow {
    private Scene clientScene;
    private Main main;
    private Scene menuScene;
    /**
     * This method initializes the main for this class
     * @param main an object of type Main
     */
    public void setMain(Main main){
        this.main = main;
    }
    /**
     * This method initializes the client scene
     * @param scene the client scene
     */
    public void setClientScene(Scene scene){
        this.clientScene = scene;
    }
    /**
     * This method initializes the menu scene
     * @param scene the menu scene
     */
    public void setMenuScene(Scene scene){
        this.menuScene = scene;
    }
    @FXML
    private Label titleLabel;

    @FXML
    private Label addLabel;

    @FXML
    private TextField addNameTextField;

    @FXML
    private TextField addBrandTextField;

    @FXML
    private TextField addPriceTextField;

    @FXML
    private TextField addQuantityTextField;

    @FXML
    private Button addButton;

    @FXML
    private TextField addTypeTextField;

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
    private TextField updateBrandTextField;

    @FXML
    private TextField updateTypeTextField;

    @FXML
    private TextField updatePriceTextField;

    @FXML
    private TextField updateQuantityTextField;

    @FXML
    private Button showClientButton;

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
    private TextField findBrandTextField;

    @FXML
    private TextField findTypeTextField;

    @FXML
    private TextField findPriceTextField;

    @FXML
    private TextField findQuantityTextField;

    @FXML
    private Label viewLabel;

    @FXML
    private Button viewButton;

    @FXML
    private Button backButton;
    @FXML
    private Label productNotFoundLabelUpdate;

    @FXML
    private Label productNotFoundLabel;
    @FXML
    private Label productNotFoundLabelDelete;
    /**
     * This method adds a new product to the table in the database when the user clicks the "Add product" button and enters all the required information
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickAddButton(ActionEvent event) {
        if(addNameTextField.getText().isEmpty() || addPriceTextField.getText().isEmpty() || addQuantityTextField.getText().isEmpty() || addBrandTextField.getText().isEmpty() || addTypeTextField.getText().isEmpty()){
            new ErrorWindow("Please fill all the required text fields!");
        }else {
            ProductBLL productBLL = new ProductBLL();
            Product product = new Product(addNameTextField.getText(), Double.parseDouble(addPriceTextField.getText()), addBrandTextField.getText(), addTypeTextField.getText(),Integer.parseInt(addQuantityTextField.getText()));
            if(productBLL.insertProduct(product)!=-1) {
                addNameTextField.clear();
                clearAllFieldsAdd();
            }
        }
    }
    /**
     * This method changes the main scene to the menu scene when the user clicks on the "Back" button
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickBackButton(ActionEvent event) {
        productNotFoundLabel.setVisible(false);
        productNotFoundLabelDelete.setVisible(false);
        findIdTextField.clear();
        clearAllFieldsFind();
        main.setScene(menuScene);
    }
    /**
     * This method deletes a product from the table in the database when the user clicks the "Delete product" button and enters the product's name
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickDeleteButton(ActionEvent event) {
        productNotFoundLabelDelete.setVisible(false);
        if(deleteNameTextField.getText().isEmpty()){
            new ErrorWindow("Please fill all the required text fields!");
        }else{
            String name=deleteNameTextField.getText();
            ProductBLL productBLL=new ProductBLL();
            Product product=productBLL.findProductByName(name);
            if(product==null)
            {
                productNotFoundLabelDelete.setVisible(true);
            }else{
                productBLL.deleteProduct(product);
            }
            deleteNameTextField.clear();
        }
    }
    /**
     * This method finds an existing product from the table in the database when the user clicks the "Find product" button and enters the product's id. This method also displays the product's information in the corresponding text fields
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickFindButton(ActionEvent event) {
        clearAllFieldsFind();
        displayProduct(productNotFoundLabel,findIdTextField,findNameTextField,findPriceTextField,findQuantityTextField,findBrandTextField,findTypeTextField);
    }
    private void clearAllFieldsFind(){
        findNameTextField.clear();
        findBrandTextField.clear();
        findTypeTextField.clear();
        findPriceTextField.clear();
        findQuantityTextField.clear();
    }
    private void clearAllFieldsAdd(){
        addNameTextField.clear();
        addBrandTextField.clear();
        addTypeTextField.clear();
        addPriceTextField.clear();
        addQuantityTextField.clear();
    }
    private void clearAllFieldsUpdate(){
        updateNameTextField.clear();
        updateBrandTextField.clear();
        updateTypeTextField.clear();
        updatePriceTextField.clear();
        updateQuantityTextField.clear();
    }
    /**
     * This method finds an existing product from the table in the database when the user clicks the "Show product" button and enters the product's id. This method also displays the product's information in the corresponding text fields
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickShowClient(ActionEvent event) {
        clearAllFieldsUpdate();
        displayProduct(productNotFoundLabelUpdate,updateIdTextField,updateNameTextField,updatePriceTextField,updateQuantityTextField,updateBrandTextField,updateTypeTextField);
    }
    /**
     * This method updates  an existing product from the table in the database when the user clicks the "Update product" button and enters the required information
     * @param event an object of type ActionEvent
     */
    @FXML
    void clickUpdateButton(ActionEvent event) {
        productNotFoundLabelUpdate.setVisible(false);
        if(updateIdTextField.getText().isEmpty() || updateNameTextField.getText().isEmpty() || updatePriceTextField.getText().isEmpty() || updateQuantityTextField.getText().isEmpty() || updateBrandTextField.getText().isEmpty() || updateTypeTextField.getText().isEmpty()){
            new ErrorWindow("Please fill all the required text fields!");
        }else{
           ProductBLL productBLL=new ProductBLL();
            int id=Integer.parseInt(updateIdTextField.getText());
            if(productBLL.findProductById(id)==null)
            {
                productNotFoundLabelUpdate.setVisible(true);
            }else{
                String name=updateNameTextField.getText();
                String brand=updateBrandTextField.getText();
                String type=updateTypeTextField.getText();
                double price=Double.parseDouble(updatePriceTextField.getText());
                int quantity=Integer.parseInt(updateQuantityTextField.getText());
                Product product=new Product(id,name,price,brand,type,quantity);
                if(productBLL.updateProduct(product)!=-1) {
                    clearAllFieldsUpdate();
                    updateIdTextField.clear();
                }
            }
        }
    }
    /**
     * This method opens a new TableSceneProduct window that displays the contents of the Product table
     * @param event  an object of type ActionEvent
     */
    @FXML
    void clickViewButton(ActionEvent event) {
       new TableSceneProduct();
    }
    private void displayProduct(Label productNotFoundLabel, TextField findIdTextField, TextField findNameTextField,TextField findPriceTextField,TextField findQuantityTextField,TextField findBrandTextField, TextField findTypeTextField){
        productNotFoundLabel.setVisible(false);
        if(!findIdTextField.getText().isEmpty()) {
            ProductBLL productBLLL = new ProductBLL();
            int id = Integer.parseInt(findIdTextField.getText());
            Product foundProduct = productBLLL.findProductById(id);
            if (foundProduct != null) {
                findNameTextField.setText(foundProduct.getName());
                findBrandTextField.setText(foundProduct.getBrand());
                findTypeTextField.setText(foundProduct.getType());
                findPriceTextField.setText(Double.toString(foundProduct.getPrice()));
                findQuantityTextField.setText(Integer.toString(foundProduct.getQuantity()));

            } else {
                productNotFoundLabel.setVisible(true);
            }

        }else{
            new ErrorWindow("Please fill all the required text fields!");
        }
    }
}
