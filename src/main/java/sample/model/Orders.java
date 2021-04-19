package sample.model;
/**
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 * Represents an order of a product made by a client.
 */
public class Orders {
    private int id;
    private int orderId;
    private int clientId;
    private int productId;
    private int quantity;
    /**
     * Parameterless constructor
     */
    public Orders(){

    }
    /**
     * Constructor with all attributes
     * @param id the unique id in the database
     * @param orderId the id of the order
     * @param clientId the id of the client who places the order
     * @param productId the id of the product ordered by the client
     * @param quantity the quantity of the ordered product
     */
    public Orders(int id, int orderId, int clientId, int productId, int quantity){
        this.id=id;
        this.orderId=orderId;
        this.clientId=clientId;
        this.productId=productId;
        this.quantity=quantity;
    }
    /** Constructor without id*/
    public Orders(int orderId, int clientId, int productId, int quantity){
        this.orderId=orderId;
        this.clientId=clientId;
        this.productId=productId;
        this.quantity=quantity;
    }

    /**
     *Gets the unique id of the row in the database
     * @return an int representing the unique id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the unique id of the row in the database
     * @param id an int representing the unique id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the id of the order
     * @return the id of the order
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets the id of the order
     * @param orderId the id of the order
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the client's id
     * @return an int representing the client's id
     */
    public int getClientId() {
        return clientId;
    }

    /**
     * Sets the client's id
     * @param clientId an int representing the client's id
     */
    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    /**
     * Gets the id of the product ordered
     * @return an int representing the id of the product
     */
    public int getProductId() {
        return productId;
    }

    /**
     * Sets the id of the product ordered
     * @param productId an int representing the id of the product
     */
    public void setProductId(int productId) {
        this.productId = productId;
    }

    /**
     * Gets the quantity of the ordered product
     * @return an int representing the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the ordered product
     * @param quantity n int representing the quantity of the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
