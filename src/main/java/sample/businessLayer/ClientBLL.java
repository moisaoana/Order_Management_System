package sample.businessLayer;

import sample.dataAccessLayer.ClientDAO;
import sample.model.Client;

import java.util.NoSuchElementException;

public class ClientBLL {
    public Client findClientById(int id) {
        ClientDAO clientDAO=new ClientDAO();
        return clientDAO.findById(id);
    }
    public int insertClient(Client client){
        ClientDAO clientDAO=new ClientDAO();
        return clientDAO.insertElement(client);
    }
    public void deleteClient(Client client){
        ClientDAO clientDAO=new ClientDAO();
        clientDAO.deleteElement(client.getID());
    }
    public  void  updateClient(Client client){
        ClientDAO clientDAO=new ClientDAO();
        clientDAO.updateElement(client);
    }
    public Client findClientByName(String name) {
        ClientDAO clientDAO=new ClientDAO();
        return clientDAO.findByName(name);
    }
}
