package sample.presentation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.businessLayer.ClientBLL;
import sample.businessLayer.OrderBLL;
import sample.businessLayer.ProductBLL;
import sample.model.Client;
import sample.model.Orders;
import sample.model.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * This UI class opens a window where the user can choose products and place an order
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 */
public class OrdersWindow extends Stage {
    TableView<Product> tableViewProduct= new TableView<>();
    TableView<Client> tableViewClient= new TableView<>();
    TextField chosenClientTextField=new TextField();
    TableView<Product>chosenProductsTable=new TableView<>();
    TextField totalTextField=new TextField();
    ObservableList<Product> chosenProductsObservableList = FXCollections.observableArrayList();
    ObservableList<Product> productsObservableList = FXCollections.observableArrayList();
    ObservableList<Client> clientsObservableList = FXCollections.observableArrayList();
    List<Client> selectedCustomer=new ArrayList<>();
    boolean firstClient=true;
    double sum=0;
    /**
     * The constructor that initializes the window
     */
    public OrdersWindow() {
        //Labels
        Text titleClient=new Text("--------------Choose client--------------");
        styleText(titleClient,20);
        Text titleProduct=new Text("----------------------Choose products---------------------");
        styleText(titleProduct,20);
        Text titleCart=new Text("---------My cart---------");
        styleText(titleCart,20);
        //Choose Client
        Button rechooseClient=new Button("Refresh client");
        styleButton(rechooseClient);
        Text clientLabel=new Text("Chosen client: ");
        styleText(clientLabel,15);
        chosenClientTextField.setEditable(false);
        HBox hBox=new HBox(clientLabel,chosenClientTextField,rechooseClient);
        hBox.setSpacing(5);
        //Total
        Text totalLabel=new Text("Total: ");
        styleText(totalLabel,15);
        totalTextField.setEditable(false);
        HBox hBoxTotal=new HBox(totalLabel,totalTextField);
        hBoxTotal.setSpacing(50);
        //Back Button
        Button backButton=new Button("Back");
        styleButton(backButton);
        backButton.setOnAction((ActionEvent event)->{
            for(Product product: chosenProductsObservableList){
                product.setQuantity(product.getQuantity() + 1);
                ProductBLL productBLL = new ProductBLL();
                productBLL.updateProduct(product);
            }
            chosenProductsObservableList.clear();
            totalTextField.clear();
            chosenClientTextField.clear();
            selectedCustomer.clear();
            this.close();
        });
        //Order Button
        Button orderButton=new Button("Order");
        styleButton(orderButton);
        orderButton.setOnAction((ActionEvent event) -> placeOrder());
        HBox hBoxButtons=new HBox(orderButton,backButton);
        hBoxButtons.setSpacing(100);
        //gridpane
        GridPane gridPane=new GridPane();
        gridPane.setStyle("-fx-background-color: aliceblue");
        gridPane.setAlignment(Pos.CENTER);
        gridPane.add(titleClient,0,0);
        gridPane.add(titleProduct,1,0);
        gridPane.add(titleCart,2,0);
        gridPane.add(tableViewClient,0,1);
        gridPane.add(tableViewProduct,1,1);
        gridPane.add(hBox,0,2);
        gridPane.add(hBoxTotal,0,3);
        gridPane.add(hBoxButtons,1,2);
        gridPane.setHgap(5);
        gridPane.setVgap(10);
        displayTables();
        addButtons(tableViewClient,selectedCustomer);
        rechooseClient.setOnAction((ActionEvent event) -> {
          firstClient=true;
          selectedCustomer.clear();
          chosenClientTextField.clear();
        });
        gridPane.add(chosenProductsTable,2,1);
        addButtonsProductAdd(tableViewProduct);
        this.setHeight(600);
        this.setWidth(1200);
        Scene scene = new Scene(gridPane);
        this.setTitle("Place an order!");
        this.setScene(scene);
        this.show();
    }
   private static void styleText(Text text, int font) {
        text.setFont(Font.font("Berlin Sans FB", FontWeight.BOLD, font));
        text.setFill(Color.CORNFLOWERBLUE);
    }
    private static void styleButton(Button button){
        button.setStyle("-fx-text-fill: #ffffff;-fx-background-color: orangered");
    }
    private void displayTables(){
        ProductBLL productBLL = new ProductBLL();
        List<Product> list = productBLL.findAll();
        productBLL.displayTable(tableViewProduct, list,productsObservableList);
        tableViewProduct.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        ClientBLL clientBLL=new ClientBLL();
        List<Client>clients=clientBLL.findAll();
        clientBLL.displayTable(tableViewClient,clients,clientsObservableList);
        chosenProductsTable.setPlaceholder(new Label("No products in your cart!"));
        TableColumn<Product,String> column = new TableColumn<>("Product");
        column.setCellValueFactory(new PropertyValueFactory<>("name"));
        chosenProductsTable.getColumns().add(column);
        column.setStyle("-fx-background-color: aliceblue;");
        TableColumn<Product,Double> column2 = new TableColumn<>("Price");
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));
        chosenProductsTable.getColumns().add(column2);
        column2.setStyle("-fx-background-color: aliceblue;");
        chosenProductsTable.setItems(chosenProductsObservableList);
        addButtonsProductRemove(chosenProductsTable);
    }

    /**
     * This method creates a new object of type Order and inserts it in the orders table in the database
     * @param orderBLL an object of type OrderBll for performing the insert operation in the table
     * @param product an object od type Product that represents the ordered product
     * @param client an object of type Client that places the order
     * @param quantity an int representing the quantity of the product ordered
     * @param orderId an int representing the id of the order
     */
    public void writeOrder(OrderBLL orderBLL,Product product, Client client, int quantity,int orderId){
        Orders order=new Orders(orderId,client.getID(),product.getID(),quantity);
        orderBLL.insertOrder(order);
    }

    /**
     * This method writes the bill of the order into a text file
     */
    public void placeOrder(){
        if(chosenClientTextField.getText().isEmpty() || chosenProductsObservableList.isEmpty()){
            new ErrorWindow("Please choose a client and at least 1 product!");
        }else{
            try {
                FileWriter fileWriter1 = new FileWriter("bill.txt",false);
                fileWriter1.write("Order bill\n");
                fileWriter1.close();
                FileWriter fileWriter = new FileWriter("bill.txt",true);
                fileWriter.write("Client: "+chosenClientTextField.getText()+"\nProducts:\n");
                OrderBLL orderBLL=new OrderBLL();
                int orderId=orderBLL.getNextOrderId();
                for (int i = 0; i < chosenProductsObservableList.size(); i++) {
                    Product product1 = chosenProductsObservableList.get(i);
                    int q=1;
                    fileWriter.write(product1.getName());
                    for (int j = i+1; j < chosenProductsObservableList.size(); j++) {
                        Product product2 = chosenProductsObservableList.get(j);
                        if (product1.getID() == product2.getID()){
                            q++;
                            chosenProductsObservableList.remove(j);
                            j--;
                        }
                    }
                    fileWriter.write(" x "+q+", "+product1.getPrice()*q+" RON\n");
                    writeOrder(orderBLL,product1,selectedCustomer.get(0),q,orderId);
                }
                fileWriter.write("Total: "+totalTextField.getText()+" RON");
                fileWriter.close();
            }catch (IOException e) {
                e.printStackTrace();
            }
            chosenProductsObservableList.clear();
            chosenClientTextField.clear();
            selectedCustomer.clear();
            this.close();
        }
    }

    /**
     * This method adds a new column to the table of clients. The new column contains buttons for selecting a client from the table.
     * @param tableView the tableView we want to add the new column to
     * @param listOfSelectedObjects the list of selected customers updated in this method
     */
    public void  addButtons(TableView<Client> tableView,List<Client>listOfSelectedObjects)
    {
        TableColumn<Client, Void> buttons = new TableColumn<>("Add");
        buttons.setStyle("-fx-background-color: aliceblue;");
        Callback<TableColumn<Client, Void>, TableCell<Client, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Client, Void> call(final TableColumn<Client, Void> param) {
                return new TableCell<>() {
                    private final Button newButton = new Button("+");{
                        styleButton(newButton);
                        newButton.setOnAction((ActionEvent event) -> {
                            Client data = getTableView().getItems().get(getIndex());
                            listOfSelectedObjects.add(data);
                            if (firstClient) {
                                chosenClientTextField.setText(data.getName());
                                firstClient = false;
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(newButton);
                        }
                    }
                };
            }
        };
        buttons.setCellFactory(cellFactory);
        tableView.getColumns().add(buttons);
    }

    /**
     * This method adds a new column to the table of products. The new column contains buttons for selecting products from the table.
     * @param tableView the tableview we want to add the new column to
     */
    public void  addButtonsProductAdd(TableView<Product> tableView)
    {
        TableColumn<Product, Void> buttons = new TableColumn<>("Add");
        buttons.setStyle("-fx-background-color: aliceblue;");
        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                return new TableCell<>() {
                    private final Button newButton = new Button("+");
                    {
                        styleButton(newButton);
                        newButton.setOnAction((ActionEvent event) -> {
                            Product product = getTableView().getItems().get(getIndex());
                            if (product.getQuantity() != 0) {
                                chosenProductsObservableList.add(product);
                                sum += product.getPrice();
                                totalTextField.setText(Double.toString(sum));
                                product.setQuantity(product.getQuantity() - 1);
                                ProductBLL productBLL = new ProductBLL();
                                productBLL.updateProduct(product);
                                tableViewProduct.refresh();
                            } else {
                                new ErrorWindow("This product is not in stock!");
                            }
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(newButton);
                        }
                    }
                };
            }
        };
        buttons.setCellFactory(cellFactory);
        tableView.getColumns().add(buttons);
    }

    /**
     * This method adds a new column to a tableView. The new column contains buttons for removing the corresponding rows from the table.
     * @param tableView the tableView we want to add the new column to
     */
    public void  addButtonsProductRemove(TableView<Product> tableView)
    {
        TableColumn<Product, Void> buttons = new TableColumn<>("Remove");
        buttons.setStyle("-fx-background-color: aliceblue;");
        Callback<TableColumn<Product, Void>, TableCell<Product, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Product, Void> call(final TableColumn<Product, Void> param) {
                return new TableCell<>() {
                    private final Button newButton = new Button("-");
                    {
                        styleButton(newButton);
                        newButton.setOnAction((ActionEvent event) -> {
                            Product product = getTableView().getItems().get(getIndex());
                            chosenProductsObservableList.remove(product);
                            sum -= product.getPrice();
                            totalTextField.setText(Double.toString(sum));
                            product.setQuantity(product.getQuantity() + 1);
                            ProductBLL productBLL = new ProductBLL();
                            productBLL.updateProduct(product);
                            tableViewProduct.refresh();
                        });
                    }
                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(newButton);
                        }
                    }
                };
            }
        };
        buttons.setCellFactory(cellFactory);
        tableView.getColumns().add(buttons);
    }
}
