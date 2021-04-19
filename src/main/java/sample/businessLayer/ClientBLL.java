package sample.businessLayer;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import sample.dataAccessLayer.ClientDAO;
import sample.model.Client;
import sample.presentation.ErrorWindow;
import java.util.List;
import java.util.regex.Pattern;
/**
 * This class defines operations specific to the Client object
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 */
public class ClientBLL {
    private ClientDAO clientDAO=new ClientDAO();
    /**
     * Method that finds a client from a table based on its id by calling the corresponding method form ClientDAO
     * @param id an int representing the id of the client
     * @return an object of type Client
     */
    public Client findClientById(int id) {
        return clientDAO.findById(id);
    }

    /**
     * Method that inserts a new client in the table by calling the corresponding method from ClientDAO and validating the input
     * @param client an object of type Client to be inserted
     * @return the id of the inserted client or -1 in case an error occurs
     */
    public int insertClient(Client client){
        if(validateEmail(client.getEmail())==0)
             return clientDAO.insertElement(client);
        else
        {
            new ErrorWindow("Invalid email!");
            return -1;
        }
    }

    /**
     * Method that deletes an existing client from the table by calling the corresponding method from ClientDAO
     * @param client the object of type Client to be deleted
     */
    public void deleteClient(Client client){
        clientDAO.deleteElement(client.getID());
    }

    /**
     *Method that updates an existing client from the table by calling the corresponding method from ClientDAO and validating the data
     * @param client the object of type Client to be updated
     * @return the id of the updated client or -1 in case an error occurs
     */
    public  int  updateClient(Client client){
        if(validateEmail(client.getEmail())==0){
            clientDAO.updateElement(client);
            return client.getID();
        }else{
            new ErrorWindow("Invalid email!");
            return -1;
        }

    }

    /**
     * Method that finds an existing client from the table based on its name by calling the corresponding method from ClientDAO
     * @param name a String representing the name of the client
     * @return an object of type Client
     */
    public Client findClientByName(String name) {
        return clientDAO.findByName(name);
    }

    /**
     * Method for displaying a table with all its entries from the database by calling the corresponding method from ClientDAO
     * @param tableView the table to be displayed
     * @param list the list of all objects to be displayed in the table
     * @param observableList the observable list of the table
     */
    public void displayTable(TableView<Client> tableView, List<Client> list, ObservableList<Client> observableList){
        clientDAO.displayTable(tableView,list,observableList);
    }

    /**
     * Method that returns a list of all clients from the table by calling the corresponding method from ClientDAO
     * @return a list of type Client
     */
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
