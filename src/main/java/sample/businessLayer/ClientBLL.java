package sample.businessLayer;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.dataAccessLayer.ClientDAO;
import sample.model.Client;
import sample.presentation.ErrorWindow;

import java.util.List;
import java.util.regex.Pattern;

public class ClientBLL {
    private ClientDAO clientDAO=new ClientDAO();
    public Client findClientById(int id) {
        return clientDAO.findById(id);
    }
    public int insertClient(Client client){
        if(validateEmail(client.getEmail())==0)
             return clientDAO.insertElement(client);
        else
        {
            new ErrorWindow("Invalid email!");
            return -1;
        }
    }
    public void deleteClient(Client client){
        clientDAO.deleteElement(client.getID());
    }
    public  int  updateClient(Client client){
        if(validateEmail(client.getEmail())==0){
            clientDAO.updateElement(client);
            return client.getID();
        }else{
            new ErrorWindow("Invalid email!");
            return -1;
        }

    }
    public Client findClientByName(String name) {
        return clientDAO.findByName(name);
    }
    public void displayTable(TableView<Client> tableView, List<Client> list, ObservableList<Client> observableList){
        clientDAO.displayTable(tableView,list,observableList);
    }
    public List<Client> findAll(){
        return clientDAO.findAll();
    }
    private int validateEmail(String email){
        String EMAIL_PATTERN ="[\\w\\.]+(@yahoo\\.com|@gmail\\.com)$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
            if (!pattern.matcher(email).matches()) {
              return -1;
            }
        return 0;
    }
}
