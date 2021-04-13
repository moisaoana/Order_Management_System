package sample.businessLayer;

import sample.dataAccessLayer.ClientDAO;
import sample.model.Client;

import java.util.NoSuchElementException;

public class ClientBLL {
    public Client findClientById(int id) {
        ClientDAO clientDAO=new ClientDAO();
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchElementException("The client with id =" + id + " was not found!");
        }
        return client;
    }
    public int insertClient(Client client){
        ClientDAO clientDAO=new ClientDAO();
        return clientDAO.insertElement(client);
    }
    public void deleteClient(Client client){
        ClientDAO clientDAO=new ClientDAO();
        clientDAO.deleteElement(client.getName());
    }
    public  void  updateClient(Client client){
        ClientDAO clientDAO=new ClientDAO();
        clientDAO.updateElement(client);
    }
}
