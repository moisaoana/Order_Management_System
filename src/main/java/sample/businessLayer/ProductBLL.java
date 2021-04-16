package sample.businessLayer;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.dataAccessLayer.ProductDAO;
import sample.model.Product;
import java.util.List;
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
    public void displayTable(TableView<Product> tableView, List<Product> list, ObservableList<Product> observableList){
        productDAO.displayTable(tableView,list,observableList);
    }
    public List<Product> findAll(){
        return productDAO.findAll();
    }
}
