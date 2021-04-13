package sample.businessLayer;

import sample.dataAccessLayer.ClientDAO;
import sample.dataAccessLayer.ProductDAO;
import sample.model.Client;
import sample.model.Product;

import java.util.NoSuchElementException;

public class ProductBLL {
    public Product findProductById(int id) {
        ProductDAO productDAO=new ProductDAO();
        return productDAO.findById(id);
    }
    public int insertProduct(Product product){
        ProductDAO productDAO=new ProductDAO();
        return productDAO.insertElement(product);
    }
    public void deleteProduct(Product product){
        ProductDAO productDAO=new ProductDAO();
        productDAO.deleteElement(product.getID());
    }
    public Product findProductByName(String name) {
        ProductDAO productDAO=new ProductDAO();
        return productDAO.findByName(name);
    }
    public  void  updateProduct(Product product){
        ProductDAO productDAO=new ProductDAO();
        productDAO.updateElement(product);
    }
}
