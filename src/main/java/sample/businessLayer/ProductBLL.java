package sample.businessLayer;

import javafx.scene.control.TableView;
import sample.dataAccessLayer.ClientDAO;
import sample.dataAccessLayer.ProductDAO;
import sample.model.Client;
import sample.model.Product;

import java.util.List;
import java.util.NoSuchElementException;

public class ProductBLL {
    private ProductDAO productDAO=new ProductDAO();
    public Product findProductById(int id) {
        return productDAO.findById(id);
    }
    public int insertProduct(Product product){
        return productDAO.insertElement(product);
    }
    public void deleteProduct(Product product){
        productDAO.deleteElement(product.getID());
    }
    public Product findProductByName(String name) {
        return productDAO.findByName(name);
    }
    public  void  updateProduct(Product product){
        productDAO.updateElement(product);
    }
    public void displayTable(TableView<Product> tableView, List<Product> list){
        productDAO.displayTable(tableView,list);
    }
    public List<Product> findAll(){
        return productDAO.findAll();
    }
}
