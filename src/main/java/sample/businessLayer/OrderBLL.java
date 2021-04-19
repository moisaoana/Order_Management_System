package sample.businessLayer;

import sample.dataAccessLayer.OrderDAO;
import sample.model.Orders;

import java.util.List;
/**
 * This class defines operations specific to the Order object
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 */
public class OrderBLL {
    private OrderDAO orderDAO=new OrderDAO();

    /**
     * Method that generates a list of all entries from a table by calling the corresponding method from OrderDAO
     * @return a list of type Order
     */
    public List<Orders> findAll(){
        return orderDAO.findAll();
    }

    /**
     * Method that generates the id of the order to be placed based on the existing ids from the table
     * @return the id of the order
     */
    public int getNextOrderId(){
        List<Orders>list=findAll();
        if(list==null){
            return 1;
        }else{
            return list.get(list.size()-1).getOrderId()+1;
        }
    }

    /**
     * Method that inserts a new order in the table by calling the corresponding method from the OrderDAO
     * @param order the object of type Order to be inserted in the table
     * @return the id of the row in the table
     */
    public int insertOrder(Orders order){
        return orderDAO.insertElement(order);
    }
}
