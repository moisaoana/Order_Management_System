package sample.businessLayer;

import sample.dataAccessLayer.OrderDAO;
import sample.model.Orders;

import java.util.List;

public class OrderBLL {
    private OrderDAO orderDAO=new OrderDAO();
    public List<Orders> findAll(){
        return orderDAO.findAll();
    }
    public int getNextOrderId(){
        List<Orders>list=findAll();
        if(list==null){
            return 1;
        }else{
            return list.get(list.size()-1).getOrderId()+1;
        }
    }
    public int insertOrder(Orders order){
        return orderDAO.insertElement(order);
    }
}
