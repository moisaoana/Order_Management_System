package sample.model;
/**
 * @author Moisa Oana Miruna
 * @version 1.0
 * @since 22.04.2021
 * Represents a product.
 */
public class Product {
    private int ID;
    private String name;
    private double price;
    private String brand;
    private String type;
    private int quantity;
    /**
     * Parameterless constructor
     */
    public Product(){

    }
    /**
     * Constructor with all parameters
     * @param ID the unique id of the product in the database
     * @param name the name of the product
     * @param price the price of the product
     * @param brand the brand of the product
     * @param type the type of the product
     * @param quantity the quantity of the product
     */
    public Product(int ID,String name,double price,String brand,String type,int quantity){
        this.ID=ID;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.brand=brand;
        this.type=type;
    }

    /**
     * Constructor without the id
     * @param name the name of the product
     * @param price the price of the product
     * @param brand the brand of the product
     * @param type the type of the product
     * @param quantity the quantity of the product
     */
    public Product(String name,double price,String brand,String type,int quantity){
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.brand=brand;
        this.type=type;
    }

    /**
     * Gets the unique id of the product
     * @return an int representing the id of the product
     */
    public int getID() {
        return ID;
    }

    /**
     * Sets the unique id of the product
     * @param ID an int representing the id of the product
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Gets the name of the product
     * @return a String representing the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product
     * @param name a String representing the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the product
     * @return a double representing the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product
     * @param price a double representing the price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Gets the quantity available for the product
     * @return an int representing the quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity available for the product
     * @param quantity an int representing the quantity of the product
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the type of the product
     * @return a String representing the type of the product
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the product
     * @param type a String representing the type of the product
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the brand of the product (the producer)
     * @return a String representing the brand of the product
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Sets the brand of the product
     * @param brand a String representing the brand of the product
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }
}
