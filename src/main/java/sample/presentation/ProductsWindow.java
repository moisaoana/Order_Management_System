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

public class ProductsWindow {
    private Scene clientScene;
    private Main main;
    private Scene menuScene;
    public void setMain(Main main){
        this.main = main;
    }
    public void setClientScene(Scene scene){
        this.clientScene = scene;
    }
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

    @FXML
    void clickAddButton(ActionEvent event) {
        if(addNameTextField.getText().isEmpty() || addPriceTextField.getText().isEmpty() || addQuantityTextField.getText().isEmpty() || addBrandTextField.getText().isEmpty() || addTypeTextField.getText().isEmpty()){
            new ErrorWindow("Please fill all the required text fields!");
        }else {
            ProductBLL productBLL = new ProductBLL();
            Product product = new Product(addNameTextField.getText(), Double.parseDouble(addPriceTextField.getText()), addBrandTextField.getText(), addTypeTextField.getText(),Integer.parseInt(addQuantityTextField.getText()));
            productBLL.insertProduct(product);
            addNameTextField.clear();
            clearAllFieldsAdd();
        }
    }

    @FXML
    void clickBackButton(ActionEvent event) {
        productNotFoundLabel.setVisible(false);
        productNotFoundLabelDelete.setVisible(false);
        findIdTextField.clear();
        clearAllFieldsFind();
        main.setScene(menuScene);
    }

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

    @FXML
    void clickShowClient(ActionEvent event) {
        clearAllFieldsUpdate();
        displayProduct(productNotFoundLabelUpdate,updateIdTextField,updateNameTextField,updatePriceTextField,updateQuantityTextField,updateBrandTextField,updateTypeTextField);
    }

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
                productBLL.updateProduct(product);
                clearAllFieldsUpdate();
                updateIdTextField.clear();
            }
        }
    }

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
