package sample.businessLayer;

import javafx.scene.control.TableView;
import sample.dataAccessLayer.ClientDAO;
import sample.model.Client;

import java.util.List;
import java.util.NoSuchElementException;

public class ClientBLL {
    private ClientDAO clientDAO=new ClientDAO();
    public Client findClientById(int id) {
        return clientDAO.findById(id);
    }
    public int insertClient(Client client){
        return clientDAO.insertElement(client);
    }
    public void deleteClient(Client client){
        clientDAO.deleteElement(client.getID());
    }
    public  void  updateClient(Client client){
        clientDAO.updateElement(client);
    }
    public Client findClientByName(String name) {
        return clientDAO.findByName(name);
    }
    public void displayTable(TableView<Client> tableView, List<Client> list){
        clientDAO.displayTable(tableView,list);
    }
    public List<Client> findAll(){
        return clientDAO.findAll();
    }
}
