package sample.businessLayer;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.dataAccessLayer.OrderDAO;
import sample.dataAccessLayer.ProductDAO;
import sample.model.Product;
import sample.presentation.ErrorWindow;
import java.util.List;
/**
 * This class defines operations specific to the Product object
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 */
public class ProductBLL {
    private ProductDAO productDAO=new ProductDAO();

    /**
     * Method that finds a product from a table based on its id by calling the corresponding method form ProductDAO
     * @param id an int representing the id of the product
     * @return an object of type Product
     */
    public Product findProductById(int id) {
        return productDAO.findById(id);
    }

    /**
     * Method that inserts a new product in the table by calling the corresponding method from ProductDAO and validating the input
     * @param product the object of type Product that we want to insert
     * @return the id of the inserted product or -1 in case an error occurs
     */
    public int insertProduct(Product product){
        if(validateNumbers(product.getPrice(),product.getQuantity())==-1) {
            new ErrorWindow("Invalid price!");
            return -1;
        }else if(validateNumbers(product.getPrice(),product.getQuantity())==-2){
            new ErrorWindow("Invalid quantity!");
            return -1;
        }else {
            return productDAO.insertElement(product);
        }
    }

    /**
     * Method that deletes a product from a table by calling the corresponding method form ProductDAO
     * @param product the object of type Product to be deleted
     */
    public void deleteProduct(Product product){
        OrderDAO orderDAO=new OrderDAO();
        orderDAO.deleteElement(product.getID(),"productId");
        productDAO.deleteElement(product.getID(),"id");
    }

    /**
     * Method that finds a product from a table based on its name by calling the corresponding method form ProductDAO
     * @param name a String representing the name of the product
     * @return the found object of type Product
     */
    public Product findProductByName(String name) {
        return productDAO.findByName(name);
    }

    /**
     * Method that updates an existing product in the table by calling the corresponding method from ProductDAO and validating the input
     * @param product the object of type Product to be updated
     * @return the id of the updated object or -1 in case an error occurs
     */
    public  int  updateProduct(Product product){
        if(validateNumbers(product.getPrice(),product.getQuantity())==-1) {
            new ErrorWindow("Invalid price!");
            return -1;
        }else if(validateNumbers(product.getPrice(),product.getQuantity())==-2){
            new ErrorWindow("Invalid quantity!");
            return -1;
        }else {
             productDAO.updateElement(product);
             return 0;
        }

    }

    /**
     * Method for displaying a table with all its entries from the database by calling the corresponding method from ProductDAO
     * @param tableView the table to be displayed
     * @param list the list of all objects to be displayed in the table
     * @param observableList the observable list of the table
     */
    public void displayTable(TableView<Product> tableView, List<Product> list, ObservableList<Product> observableList){
        productDAO.displayTable(tableView,list,observableList);
    }

    /**
     *  Method that returns a list of all products from the table by calling the corresponding method from ProductDAO
     * @return a list of type Product
     */
    public List<Product> findAll(){
        return productDAO.findAll();
    }
    private int validateNumbers(double price, int quantity){
        if(price<=0){
            return -1;
        }else{
            if(quantity<0){
                return -2;
            }else{
                return 0;
            }
        }

    }
}
