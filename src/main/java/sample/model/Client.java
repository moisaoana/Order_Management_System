package sample.model;
/**
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 * Represents a client.
 */
public class Client {
    private int ID;
    private String name;
    private String address;
    private String email;
    /**
     * Parameterless constructor
     */
    public Client(){

    }
    /**
     * Constructor with all attributes
     * @param ID client's id in the database
     * @param name client's full name
     * @param  address client's full address
     * @param email client's email
     */
    public Client(int ID, String name,String address,String email){
        this.ID=ID;
        this.name=name;
        this.address=address;
        this.email=email;
    }
    /** Constructor without id*/
    public Client(String name,String address,String email){
        this.name=name;
        this.address=address;
        this.email=email;
    }
    /**
     * Gets this client's id
     * @return an int, representing the client's id
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets this client's id
     * @param ID an int representing client's id
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets this client's name
     * @return a String containing the client's full name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets this client's name
     * @param name a String representing the client's full name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets this client's address
     * @return a String containing the client's address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets this client's address
     * @param address a String representing the client's address
     */

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets this client's email
     * @return a String representing the client's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets this client's email
     * @param email a String containing client's email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
