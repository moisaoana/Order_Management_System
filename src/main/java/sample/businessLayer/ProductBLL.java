package sample.businessLayer;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.dataAccessLayer.ProductDAO;
import sample.model.Product;
import sample.presentation.ErrorWindow;

import java.util.List;
public class ProductBLL {
    private ProductDAO productDAO=new ProductDAO();
    public Product findProductById(int id) {
        return productDAO.findById(id);
    }
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
    public void deleteProduct(Product product){
        productDAO.deleteElement(product.getID());
    }
    public Product findProductByName(String name) {
        return productDAO.findByName(name);
    }
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
    public void displayTable(TableView<Product> tableView, List<Product> list, ObservableList<Product> observableList){
        productDAO.displayTable(tableView,list,observableList);
    }
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
