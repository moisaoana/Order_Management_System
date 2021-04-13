package sample.businessLayer;

import sample.dataAccessLayer.ClientDAO;
import sample.dataAccessLayer.ProductDAO;
import sample.model.Client;
import sample.model.Product;

import java.util.NoSuchElementException;

public class ProductBLL {
    public Product findProductById(int id) {
        ProductDAO productDAO=new ProductDAO();
       Product product=productDAO.findById(id);
        if (product == null) {
            throw new NoSuchElementException("The product with id =" + id + " was not found!");
        }
        return product;
    }
    public int insertProduct(Product product){
        ProductDAO productDAO=new ProductDAO();
        return productDAO.insertElement(product);
    }
    public void deleteProduct(Product product){
        ProductDAO productDAO=new ProductDAO();
        productDAO.deleteElement(product.getID());
    }
}
